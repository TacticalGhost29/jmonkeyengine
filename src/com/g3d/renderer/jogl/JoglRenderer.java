package com.g3d.renderer.jogl;

import com.g3d.light.DirectionalLight;
import com.g3d.light.Light;
import com.g3d.light.LightList;
import com.g3d.light.PointLight;
import com.g3d.material.Material;
import com.g3d.material.RenderState;
import com.g3d.math.ColorRGBA;
import com.g3d.math.Matrix4f;
import com.g3d.math.Vector3f;
import com.g3d.renderer.Camera;
import com.g3d.renderer.GLObjectManager;
import com.g3d.renderer.RenderContext;
import com.g3d.renderer.Renderer;
import com.g3d.renderer.queue.RenderQueue;
import com.g3d.renderer.queue.RenderQueue.Bucket;
import com.g3d.renderer.queue.RenderQueue.ShadowMode;
import com.g3d.scene.Geometry;
import com.g3d.scene.Mesh;
import com.g3d.scene.Mesh.Mode;
import com.g3d.scene.VertexBuffer;
import com.g3d.scene.VertexBuffer.Type;
import com.g3d.shader.Shader;
import com.g3d.shader.Shader.ShaderSource;
import com.g3d.shader.Uniform;
import com.g3d.system.jogl.JoglContext;
import com.g3d.texture.FrameBuffer;
import com.g3d.texture.Texture;
import com.g3d.util.TempVars;
import java.nio.Buffer;
import java.nio.FloatBuffer;
import java.util.List;
import java.util.logging.Logger;
import javax.media.opengl.GL;

public class JoglRenderer implements Renderer {

    private static final Logger logger = Logger.getLogger(JoglRenderer.class.getName());


    protected Camera camera;
    protected Matrix4f worldMatrix = new Matrix4f();
    protected GL gl;

    private JoglContext owner;
    private RenderQueue queue;
    private RenderContext context = new RenderContext();
    private GLObjectManager objManager = new GLObjectManager();

    private Material forcedMaterial = null;

    public JoglRenderer(JoglContext owner, GL gl){
        queue = new RenderQueue(this);
        this.owner = owner;
        this.gl = gl;
    }

    public void setGL(GL gl){
        this.gl = gl;
    }

    public void initialize(){
        logger.info("Vendor: "+gl.glGetString(gl.GL_VENDOR));
        logger.info("Renderer: "+gl.glGetString(gl.GL_RENDERER));
        logger.info("Version: "+gl.glGetString(gl.GL_VERSION));
        
        applyRenderState(RenderState.DEFAULT);
    }

    public void setBackgroundColor(ColorRGBA color) {
        gl.glClearColor(color.r, color.g, color.b, color.a);
    }

    public void cleanup(){
        queue.clear();
        objManager.deleteAllObjects(this);
    }

    public void clearBuffers(boolean color, boolean depth, boolean stencil) {
        int bits = 0;
        if (color) bits = gl.GL_COLOR_BUFFER_BIT;
        if (depth) bits |= gl.GL_DEPTH_BUFFER_BIT;
        if (stencil) bits |= gl.GL_STENCIL_BUFFER_BIT;
        if (bits != 0) gl.glClear(bits);
    }

    public void applyRenderState(RenderState state){
        if (state.isWireframe() && !context.wireframe){
            gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_LINE);
            context.wireframe = true;
        }else if (!state.isWireframe() && context.wireframe){
            gl.glPolygonMode(gl.GL_FRONT_AND_BACK, gl.GL_FILL);
            context.wireframe = false;
        }
        if (state.isDepthTest() && !context.depthTestEnabled){
            gl.glEnable(gl.GL_DEPTH_TEST);
            gl.glDepthFunc(gl.GL_LEQUAL);
            context.depthTestEnabled = true;
        }else if (!state.isDepthTest() && context.depthTestEnabled){
            gl.glDisable(gl.GL_DEPTH_TEST);
            context.depthTestEnabled = false;
        }
        if (state.isAlphaTest() && !context.alphaTestEnabled){
            gl.glEnable(gl.GL_ALPHA_TEST);
            gl.glAlphaFunc(gl.GL_GREATER, state.getAlphaFallOff());
            context.alphaTestEnabled = true;
        }else if (!state.isAlphaTest() && context.alphaTestEnabled){
            gl.glDisable(gl.GL_ALPHA_TEST);
            context.alphaTestEnabled = false;
        }
        if (state.isDepthWrite() && !context.depthWriteEnabled){
            gl.glDepthMask(true);
            context.depthWriteEnabled = true;
        }else if (!state.isDepthWrite() && context.depthWriteEnabled){
            gl.glDepthMask(false);
            context.depthWriteEnabled = false;
        }
        if (state.isColorWrite() && !context.colorWriteEnabled){
            gl.glColorMask(true,true,true,true);
            context.colorWriteEnabled = true;
        }else if (!state.isColorWrite() && context.colorWriteEnabled){
            gl.glColorMask(false,false,false,false);
            context.colorWriteEnabled = false;
        }
        if (state.isPolyOffset()){
            if (!context.polyOffsetEnabled){
                gl.glEnable(gl.GL_POLYGON_OFFSET_FILL);
                gl.glPolygonOffset(state.getPolyOffsetFactor(),
                                state.getPolyOffsetUnits());
                context.polyOffsetEnabled = true;
                context.polyOffsetFactor = state.getPolyOffsetFactor();
                context.polyOffsetUnits = state.getPolyOffsetUnits();
            }else{
                if (state.getPolyOffsetFactor() != context.polyOffsetFactor
                 || state.getPolyOffsetUnits() != context.polyOffsetUnits){
                    gl.glPolygonOffset(state.getPolyOffsetFactor(),
                                    state.getPolyOffsetUnits());
                    context.polyOffsetFactor = state.getPolyOffsetFactor();
                    context.polyOffsetUnits = state.getPolyOffsetUnits();
                }
            }
        }else{
            if (context.polyOffsetEnabled){
                gl.glDisable(gl.GL_POLYGON_OFFSET_FILL);
                context.polyOffsetEnabled = false;
                context.polyOffsetFactor = 0;
                context.polyOffsetUnits = 0;
            }
        }
        if (state.getFaceCullMode() != context.cullMode){
            if (state.getFaceCullMode() == RenderState.FaceCullMode.Off)
                gl.glDisable(gl.GL_CULL_FACE);
            else
                gl.glEnable(gl.GL_CULL_FACE);

            switch (state.getFaceCullMode()){
                case Off:
                    break;
                case Back:
                    gl.glCullFace(gl.GL_BACK);
                    break;
                case Front:
                    gl.glCullFace(gl.GL_FRONT);
                    break;
                case FrontAndBack:
                    gl.glCullFace(gl.GL_FRONT_AND_BACK);
                    break;
                default:
                    throw new UnsupportedOperationException("Unrecognized face cull mode: "+
                                                            state.getFaceCullMode());
            }

            context.cullMode = state.getFaceCullMode();
        }

        if (state.getBlendMode() != context.blendMode){
            if (state.getBlendMode() == RenderState.BlendMode.Off)
                gl.glDisable(gl.GL_BLEND);
            else
                gl.glEnable(gl.GL_BLEND);

            switch (state.getBlendMode()){
                case Off:
                    break;
                case Additive:
                    gl.glBlendFunc(gl.GL_ONE, gl.GL_ONE);
                    break;
                case Alpha:
                    gl.glBlendFunc(gl.GL_SRC_ALPHA, gl.GL_ONE_MINUS_SRC_ALPHA);
                    break;
                case PremultAlpha:
                    gl.glBlendFunc(gl.GL_ONE, gl.GL_ONE_MINUS_SRC_ALPHA);
                    break;
                case Modulate:
                    gl.glBlendFunc(gl.GL_DST_COLOR, gl.GL_ZERO);
                    break;
                case ModulateX2:
                    gl.glBlendFunc(gl.GL_DST_COLOR, gl.GL_SRC_COLOR);
                    break;
                default:
                    throw new UnsupportedOperationException("Unrecognized blend mode: "+
                                                            state.getBlendMode());
            }

            context.blendMode = state.getBlendMode();
        }
    }

    public void onFrame() {
        objManager.deleteUnused(this);

        if (camera.isViewportChanged()){
            updateViewPort(camera.getWidth(), camera.getHeight());
            camera.clearViewportChanged();
        }
    }

    public void setCamera(Camera cam) {
        if (this.camera == cam)
            return;

        this.camera = cam;
        // the GL state needs an update in viewport
        updateViewPort(cam.getWidth(), cam.getHeight());

        //update view & proj matrices
    }

    public Camera getCamera() {
        return camera;
    }

    private void updateViewPort(float width, float height){
        int x = (int) (camera.getViewPortLeft() * width);
        int y = (int) (camera.getViewPortBottom() * height);
        int w = (int) ((camera.getViewPortRight() - camera.getViewPortLeft()) * width);
        int h = (int) ((camera.getViewPortTop() - camera.getViewPortBottom()) * height);
        gl.glViewport(x, y, w, h);

        //also update ortho matrix
//        orthoMatrix.loadIdentity();
//
//        float tx = -(w+x)/(w-x);
//        float ty = -(h+y)/(h-y);
//        float tz = 0;
//        orthoMatrix.setTranslation(tx, ty, tz);
//
//        float m00 = 2f / (w-x);
//        float m11 = 2f / (h-y);
//        float m22 = -1f;
//        orthoMatrix.setScale(new Vector3f(m00,m11,m22));
    }

    private FloatBuffer storeMatrix(Matrix4f matrix, FloatBuffer store){
        store.rewind();
        matrix.fillFloatBuffer(store,true);
        store.rewind();
        return store;
    }

    private void updateModelView(){
        FloatBuffer store = TempVars.get().floatBuffer16;

        //update modelview
        if (context.matrixMode != gl.GL_MODELVIEW){
            gl.glMatrixMode(gl.GL_MODELVIEW);
            context.matrixMode = gl.GL_MODELVIEW;
        }

        gl.glLoadMatrixf(storeMatrix(camera.getViewMatrix(),store));
        gl.glMultMatrixf(storeMatrix(worldMatrix,store));
    }

    private void updateProjection(){
        FloatBuffer store = TempVars.get().floatBuffer16;

        //update modelview
        if (context.matrixMode != gl.GL_PROJECTION){
            gl.glMatrixMode(gl.GL_PROJECTION);
            context.matrixMode = gl.GL_PROJECTION;
        }

        gl.glLoadMatrixf(storeMatrix(camera.getProjectionMatrix(),store));
    }

    public void setWorldMatrix(Matrix4f worldMatrix) {
        this.worldMatrix.set(worldMatrix);
        updateModelView();
    }


    public void updateWorldParameters(List<Uniform> params) {
    }

    public void updateLightListUniforms(Shader shader, Geometry geom, int numLights) {
        if (numLights == 0) {
            // turn off lighting
            gl.glDisable(gl.GL_LIGHTING);
            return;
        }

        gl.glEnable(gl.GL_LIGHTING);
        gl.glShadeModel(gl.GL_SMOOTH);

        float[] temp = new float[4];

        // reset model view to specify
        // light positions in world space
        // instead of model space
        gl.glPushMatrix();
        gl.glLoadIdentity();

        LightList list = geom.getWorldLightList();
        for (int i = 0; i < numLights; i++){
            if (list.size() <= i){
                // goes beyond the num lights we need
                // disable it
                gl.glDisable(gl.GL_LIGHT0 + i);
                break;
            }
            
            Light l = list.get(i);
            int lightId = gl.GL_LIGHT0 + i;

            ColorRGBA color = l.getColor();
            color.toArray(temp);

            gl.glEnable(lightId);
            gl.glLightfv(lightId, gl.GL_DIFFUSE,  temp, 0);
            gl.glLightfv(lightId, gl.GL_SPECULAR, temp, 0);
            
            ColorRGBA.Black.toArray(temp);
            gl.glLightfv(lightId, gl.GL_AMBIENT,  temp, 0);

            switch (l.getType()){
                case Directional:
                    DirectionalLight dl = (DirectionalLight) l;
                    dl.getDirection().toArray(temp);
                    temp[3] = 0f; // marks to GL its a directional light
                    gl.glLightfv(lightId, gl.GL_POSITION, temp, 0);
                    break;
                case Point:
                    PointLight pl = (PointLight) l;
                    pl.getPosition().toArray(temp);
                    temp[3] = 1f; // marks to GL its a point light
                    gl.glLightfv(lightId, gl.GL_POSITION, temp, 0);
                    break;
            }

        }

        // restore modelview to original value
        gl.glPopMatrix();
    }

    public void updateShaderData(Shader shader) {
        
    }

    public void setShader(Shader shader) {
        
    }

    public void deleteShader(Shader shader) {
        
    }

    public void copyFrameBuffer(FrameBuffer src, FrameBuffer dst) {
    }

    public void setFrameBuffer(FrameBuffer fb) {
    }

    public void updateFrameBuffer(FrameBuffer fb) {
    }

    public void deleteFrameBuffer(FrameBuffer fb) {
    }

    private int convertTextureType(Texture.Type type){
        switch (type){
            case OneDimensional:
                return gl.GL_TEXTURE_1D;
            case TwoDimensional:
                return gl.GL_TEXTURE_2D;
            case TwoDimensionalArray:
                return gl.GL_TEXTURE_2D_ARRAY_EXT;
            case ThreeDimensional:
                return gl.GL_TEXTURE_3D;
            case CubeMap:
                return gl.GL_TEXTURE_CUBE_MAP;
            default:
                throw new UnsupportedOperationException("Unknown texture type: "+type);
        }
    }

    private int convertMagFilter(Texture.MagFilter filter){
        switch (filter){
            case Bilinear:
                return gl.GL_LINEAR;
            case Nearest:
                return gl.GL_NEAREST;
            default:
                throw new UnsupportedOperationException("Unknown mag filter: "+filter);
        }
    }

    private int convertMinFilter(Texture.MinFilter filter){
        switch (filter){
            case Trilinear:
                return gl.GL_LINEAR_MIPMAP_LINEAR;
            case BilinearNearestMipMap:
                return gl.GL_LINEAR_MIPMAP_NEAREST;
            case NearestLinearMipMap:
                return gl.GL_NEAREST_MIPMAP_LINEAR;
            case NearestNearestMipMap:
                return gl.GL_NEAREST_MIPMAP_NEAREST;
            case BilinearNoMipMaps:
                return gl.GL_LINEAR;
            case NearestNoMipMaps:
                return gl.GL_NEAREST;
            default:
                throw new UnsupportedOperationException("Unknown min filter: "+filter);
        }
    }

    private int convertWrapMode(Texture.WrapMode mode){
        switch (mode){
            case BorderClamp:
                return gl.GL_CLAMP_TO_BORDER;
            case Clamp:
                return gl.GL_CLAMP;
            case EdgeClamp:
                return gl.GL_CLAMP_TO_EDGE;
            case Repeat:
                return gl.GL_REPEAT;
            case MirroredRepeat:
                return gl.GL_MIRRORED_REPEAT;
            default:
                throw new UnsupportedOperationException("Unknown wrap mode: "+mode);
        }
    }

    public void updateTextureData(Texture tex) {
    }

    public void setTexture(int unit, Texture tex) {
    }

    public void clearTextureUnits() {
    }

    public void deleteTexture(Texture tex) {
    }

    public void updateBufferData(VertexBuffer vb) {
    }

    public void deleteBuffer(VertexBuffer vb) {
    }

    private int convertArrayType(VertexBuffer.Type type){
        switch (type){
            case Position:
                return gl.GL_VERTEX_ARRAY;
            case Normal:
                return gl.GL_NORMAL_ARRAY;
            case TexCoord:
                return gl.GL_TEXTURE_COORD_ARRAY;
            case Color:
                return gl.GL_COLOR_ARRAY;
            default:
                return -1; // unsupported
        }
    }

    private int convertVertexFormat(VertexBuffer.Format fmt){
        switch (fmt){
            case Byte:
                return gl.GL_BYTE;
            case Double:
                return gl.GL_DOUBLE;
            case Float:
                return gl.GL_FLOAT;
            case Half:
                return gl.GL_HALF_FLOAT_ARB;
            case Int:
                return gl.GL_INT;
            case Short:
                return gl.GL_SHORT;
            case UnsignedByte:
                return gl.GL_UNSIGNED_BYTE;
            case UnsignedInt:
                return gl.GL_UNSIGNED_INT;
            case UnsignedShort:
                return gl.GL_UNSIGNED_SHORT;
            default:
                throw new UnsupportedOperationException("Unrecognized vertex format: "+fmt);
        }
    }

    public void setVertexAttrib(VertexBuffer vb) {
        int arrayType = convertArrayType(vb.getBufferType());
        if (arrayType == -1)
            return; // unsupported

        gl.glEnableClientState(arrayType);
        context.boundAttribs[vb.getBufferType().ordinal()] = vb;

        Buffer data = vb.getData();
        int comps = vb.getNumComponents();
        int type = convertVertexFormat(vb.getFormat());
        data.rewind();

        switch (vb.getBufferType()){
            case Position:
                gl.glVertexPointer(comps, type, 0, data);
                break;
            case Normal:
                gl.glNormalPointer(type, 0, data);
                break;
            case Color:
                gl.glColorPointer(comps, type, 0, data);
                break;
            case TexCoord:
                gl.glTexCoordPointer(comps, type, 0, data);
                break;
        }
    }

    public void clearVertexAttribs() {
        for (int i = 0; i < 16; i++){
            VertexBuffer vb = context.boundAttribs[i];
            if (vb != null){
                int arrayType = convertArrayType(vb.getBufferType());
                gl.glDisableClientState(arrayType);
                context.boundAttribs[vb.getBufferType().ordinal()] = null;
            }
        }
    }

    public void renderQueue() {
        queue.renderQueue(Bucket.Opaque);
    }

    public void addToQueue(Geometry geom, Bucket bucket) {
        queue.addToQueue(geom, bucket);
    }
    
    public RenderQueue getRenderQueue() {
        return queue;
    }

    public void renderMesh(Mesh mesh, int count) {
        VertexBuffer indices = null;
        for (VertexBuffer vb : mesh.getBuffers()){
            if (vb.getBufferType() == Type.Index){
                indices = vb;
            }else{
                setVertexAttrib(vb);
            }
        }
        if (indices != null){
            drawTriangleList(indices, mesh.getMode(), 1, mesh.getVertexCount());
        }else{
            gl.glDrawArrays(convertElementMode(mesh.getMode()), 0, mesh.getVertexCount());
        }
        clearVertexAttribs();
        clearTextureUnits();
    }

    private void updateDisplayList(Mesh mesh){
        if (mesh.getId() != -1){
            // delete list first
            gl.glDeleteLists(mesh.getId(), mesh.getId());
            mesh.setId(-1);
        }

        // create new display list
        // first set state to NULL
        applyRenderState(RenderState.NULL);

        // disable lighting
        updateLightListUniforms(null, null, 0);

        int id = gl.glGenLists(1);
        mesh.setId(id);
        gl.glNewList(id, gl.GL_COMPILE);
        renderMesh(mesh, 1);
        gl.glEndList();
    }

    private int convertElementMode(Mesh.Mode mode){
        switch (mode){
            case Points:
                return gl.GL_POINTS;
            case Lines:
                return gl.GL_LINES;
            case LineLoop:
                return gl.GL_LINE_LOOP;
            case LineStrip:
                return gl.GL_LINE_STRIP;
            case Triangles:
                return gl.GL_TRIANGLES;
            case TriangleFan:
                return gl.GL_TRIANGLE_FAN;
            case TriangleStrip:
                return gl.GL_TRIANGLE_STRIP;
            default:
                throw new UnsupportedOperationException("Unrecognized mesh mode: "+mode);
        }
    }

    public void drawTriangleList(VertexBuffer indexBuf, Mode mode, int count, int vertCount) {
        indexBuf.getData().rewind();
        gl.glDrawElements(convertElementMode(mode),
                          indexBuf.getData().capacity(),
                          convertVertexFormat(indexBuf.getFormat()),
                          indexBuf.getData());
    }

    public void renderGeometry(Geometry geom) {
        setWorldMatrix(geom.getWorldMatrix());
        updateProjection();

        Mesh mesh = geom.getMesh();
        boolean dynamic = false;
        for (VertexBuffer vb : mesh.getBuffers()){
            if (vb.getUsage() != VertexBuffer.Usage.Static){
                dynamic = true;
            }
        }

        if (geom.getMaterial() == null){
            logger.warning("Unable to render geometry "+geom+". No material defined!");
            return;
        }
        if (forcedMaterial != null){
            // use forced material
            forcedMaterial.apply(geom, this);
        }else{
            // use geometry's material
            geom.getMaterial().apply(geom, this);
        }

        if (!geom.getLocalScale().equals(Vector3f.UNIT_XYZ)){
            // enable normalize option
            gl.glEnable(gl.GL_NORMALIZE);
        }else{
            gl.glDisable(gl.GL_NORMALIZE);
        }

        if (!dynamic){
            // dealing with a static object, generate display list
            if (mesh.getId() == -1){
                updateDisplayList(mesh);
            }

            gl.glCallList(mesh.getId());
        }else{
            renderMesh(mesh, 1);
        }
    }

    public void updateShaderSourceData(ShaderSource source) {
        
    }

    public void deleteShaderSource(ShaderSource source) {
        
    }

    public void renderShadowQueue(ShadowMode shadBucket) {
        
    }

    public void addToShadowQueue(Geometry geom, ShadowMode shadBucket) {
        
    }

    public void setForcedMaterial(Material mat) {
        this.forcedMaterial = mat;
    }

    public void setDepthParams(float clearVal, float start, float end) {
        
    }

}
