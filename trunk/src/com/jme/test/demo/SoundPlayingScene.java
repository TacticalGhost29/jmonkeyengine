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
 * Created on 23 d�c. 2003
 *
 */
package com.jme.test.demo;

import com.jme.entity.Entity;
import com.jme.math.Vector3f;
import com.jme.scene.Node;
import com.jme.scene.Text;
import com.jme.sound.IEffectPlayer;
import com.jme.sound.IRenderer;
import com.jme.sound.utils.EffectRepository;
import com.jme.sound.utils.OnDemandSoundLoader;
import com.jme.util.Timer;

/**
 * @author Arman Ozcelik
 *
 */
public class SoundPlayingScene implements Scene {

	private IRenderer soundRenderer;
	private Node soundNode;
	private Text text;
	private float timeElapsed;
	private Timer timer;
	private Vector3f soundPosition= new Vector3f(0, 0, 0);

	private Entity backgroundMusic, e;

	private SceneEnabledGame game;

	private OnDemandSoundLoader soundLoader;

	private int status;

	public void init(SceneEnabledGame game) {
		this.game= game;
		timer= game.getTimer();
		text= new Text("Playing sound");
		soundRenderer= game.getSoundSystem().getRenderer();
		backgroundMusic= new Entity("BACKGROUND");
		soundRenderer.addSoundPlayer(backgroundMusic);
		soundRenderer.getSoundPlayer(backgroundMusic).setPosition(soundPosition);
		soundRenderer.getSoundPlayer(backgroundMusic).setMaxDistance(25.0f);
		soundLoader= new OnDemandSoundLoader(10);
		soundLoader.start();
		soundLoader.queueSound(backgroundMusic.getId(), "C:/eclipse/workspace/JavaMonkeyEngine/jme/data/sound/0.mp3");
		soundNode= new Node();
		soundNode.attachChild(text);
		status= Scene.LOADING_NEXT_SCENE;
		System.out.println("LOADING Sound");
		text.print("Position " + soundPosition);

	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#getStatus()
	 */
	public int getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#update()
	 */
	public boolean update() {
		if(soundNode==null){ 
			return false;
		}
		if(EffectRepository.getRepository().getSource(backgroundMusic.getId()) !=null){
			status=READY;
			
		}else{
			return false;
		}
		
		timer.update();
		if (soundRenderer.getSoundPlayer(backgroundMusic).getStatus() != IEffectPlayer.LOOPING) {
			soundRenderer.getSoundPlayer(backgroundMusic).loop(
				EffectRepository.getRepository().getSource(backgroundMusic.getId()));
				
		}
		
		return true;
	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#render()
	 */
	public boolean render() {
		game.getDisplaySystem().getRenderer().clearBuffers();
		game.getDisplaySystem().getRenderer().draw(soundNode);
		return false;
	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#cleanup()
	 */
	public void cleanup() {
		// TODO Auto-generated method stub

	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#getSceneClassName()
	 */
	public String getSceneClassName() {
		
		return "com.jme.test.demo.SoundPlayingScene";
	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#getLinkedSceneClassName()
	 */
	public String getLinkedSceneClassName() {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.jme.test.demo.Scene#setStatus(int)
	 */
	public void setStatus(int status) {
		this.status=status;
	}

}
