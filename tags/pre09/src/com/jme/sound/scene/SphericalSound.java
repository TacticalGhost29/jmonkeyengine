/*
 * Copyright (c) 2003, jMonkeyEngine - Mojo Monkey Coding
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the Mojo Monkey Coding, jME, jMonkey Engine, nor the
 * names of its contributors may be used to endorse or promote products derived
 * from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 */

/*
 * Created on 25 janv. 2004
 *
 */
package com.jme.sound.scene;

import java.net.URL;

import com.jme.sound.ISoundRenderer;
import com.jme.sound.ISource;
import com.jme.sound.SoundAPIController;
import com.jme.sound.scene.SoundSpatial;


/**
 * @author Arman Ozcelik
 *
 */
public class SphericalSound extends SoundSpatial {


	private ISource source;
	private int cullMode;

	public SphericalSound(String file){
		source=SoundAPIController.getSoundSystem().loadSource(file);
		cullMode=SoundSpatial.CULL_DISTANCE;
	}

	public SphericalSound(URL url){
		source=SoundAPIController.getSoundSystem().loadSource(url);
		cullMode=SoundSpatial.CULL_DISTANCE;
	}


	/* (non-Javadoc)
	 * @see com.jme.sound.scene.SoundSpatial#draw(com.jme.sound.scene.SoundRenderer)
	 */
	public void draw(ISoundRenderer r) {
		r.draw(this);
	}



	/**
	 * @return
	 */
	public ISource getSource() {
		return source;
	}

	/**
	 * @param source
	 */
	public void setSource(ISource source) {
		this.source= source;
	}

	/**
	 * @return
	 */
	public int getCullMode() {
		return cullMode;
	}

	/**
	 * @param i
	 */
	public void setCullMode(int i) {
		cullMode= i;
	}

}