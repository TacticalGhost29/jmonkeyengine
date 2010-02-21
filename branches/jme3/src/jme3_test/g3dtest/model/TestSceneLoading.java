package g3dtest.model;

import com.g3d.app.SimpleApplication;
import com.g3d.asset.TextureKey;
import com.g3d.material.Material;
import com.g3d.renderer.queue.RenderQueue.Bucket;
import com.g3d.scene.Geometry;
import com.g3d.scene.Spatial;
import com.g3d.scene.shape.Sphere;
import com.g3d.texture.Texture;
//
//public class TestSceneLoading extends SimpleApplication {
//
//    private Sphere sphereMesh = new Sphere(32, 32, 10, false, true);
//    private Geometry sphere = new Geometry("Sky", sphereMesh);
//
//    public static void main(String[] args){
//        TestSceneLoading app = new TestSceneLoading();
//        app.start();
//    }
//
//    @Override
//    public void simpleUpdate(float tpf){
//        sphere.setLocalTranslation(cam.getLocation());
//    }
//
//    public void simpleInitApp() {
//        this.flyCam.setMoveSpeed(10);
//
//        // load sky
//        sphere.updateModelBound();
//        sphere.setQueueBucket(Bucket.Sky);
//        Material sky = new Material(manager, "sky.j3md");
//        TextureKey key = new TextureKey("sky3.dds", false);
//        key.setGenerateMips(true);
//        key.setAsCube(true);
//        Texture tex = manager.loadTexture(key);
//        sky.setTexture("m_Texture", tex);
//        sphere.setMaterial(sky);
//        rootNode.attachChild(sphere);
//
//        // create the geometry and attach it
//        manager.registerLocator("wildhouse.j3p",
//                                "com.g3d.asset.pack.J3PFileLocator",
//                                "scene", "meshxml",
//                                "material", "jpg", "png");
//
//        Spatial scene = manager.loadModel("main.scene");
//        rootNode.attachChild(scene);
//    }
//}
