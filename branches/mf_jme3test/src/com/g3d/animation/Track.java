/*
 * Copyright (c) 2003-2009 jMonkeyEngine
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'jMonkeyEngine' nor the names of its contributors
 *   may be used to endorse or promote products derived from this software
 *   without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package com.g3d.animation;

import com.g3d.export.G3DExporter;
import com.g3d.export.G3DImporter;
import com.g3d.export.Savable;
import com.g3d.scene.Mesh;
import java.io.IOException;
import java.io.Serializable;

/**
 * A single track of mesh animation (either morph or pose based).
 * Currently morph animations are not supported (only pose).
 */
public abstract class Track implements Serializable, Savable {
    
    private static final long serialVersionUID = 1L;

    protected int targetMeshIndex;

    public Track(int targetMeshIndex){
        this.targetMeshIndex = targetMeshIndex;
    }

    public int getTargetMeshIndex(){
        return targetMeshIndex;
    }

    public abstract void setTime(float time, Mesh[] targets, float weight);

    public void write(G3DExporter ex) throws IOException{
        ex.getCapsule(this).write(targetMeshIndex, "meshIndex", 0);
    }

    public void read(G3DImporter im) throws IOException{
        targetMeshIndex = im.getCapsule(this).readInt("meshIndex", 0);
    }

    public Class getClassTag(){
        return Track.class;
    }


}