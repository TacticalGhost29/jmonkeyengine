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
package com.jme.input.action;

import com.jme.input.Mouse;
import com.jme.input.RelativeMouse;
import com.jme.math.Vector3f;
import com.jme.renderer.Camera;
import com.jme.system.JmeException;

/**
 * <code>MouseLook</code>
 * @author Mark Powell
 * @version 
 */
public class MouseLook implements MouseInputAction {
    public static final int MOUSE_BUFFER = 1;
    private RelativeMouse mouse;
    private KeyLookDownAction lookDown;
    private KeyLookUpAction lookUp;
    private KeyRotateLeftAction rotateLeft;
    private KeyRotateRightAction rotateRight;

    private Vector3f lockAxis;

    private float speed;
    private Camera camera;

    public MouseLook(Mouse mouse, Camera camera, float speed) {
        if(mouse instanceof RelativeMouse) {
            this.mouse = (RelativeMouse)mouse;
        } else {
            throw new JmeException("MouseLook must take a RelativeMouse.");
        }
        this.speed = speed;
        this.camera = camera;

        lookDown = new KeyLookDownAction(camera, speed);
        lookUp = new KeyLookUpAction(camera, speed);
        rotateLeft = new KeyRotateLeftAction(camera, speed);
        rotateRight = new KeyRotateRightAction(camera, speed);
    }

    public void setLockAxis(Vector3f lockAxis) {
        this.lockAxis = lockAxis;
        rotateLeft.setLockAxis(lockAxis);
        rotateRight.setLockAxis(lockAxis);
    }

    public void setSpeed(float speed) {
        this.speed = speed;
        lookDown.setSpeed(speed);
        lookUp.setSpeed(speed);
        rotateRight.setSpeed(speed);
        rotateLeft.setSpeed(speed);

    }

    public float getSpeed() {
        return speed;
    }
    /* (non-Javadoc)
     * @see com.jme.input.action.MouseInputAction#performAction(float)
     */
    public void performAction(float time) {
        if (mouse.getLocalTranslation().x > 0) {
            rotateRight.performAction(
                time * (mouse.getLocalTranslation().x / MOUSE_BUFFER));
        } else if (mouse.getLocalTranslation().x < 0) {
            rotateLeft.performAction(
                time * (mouse.getLocalTranslation().x / MOUSE_BUFFER) * -1);
        }
        if (mouse.getLocalTranslation().y > 0) {
            lookUp.performAction(
                time * (mouse.getLocalTranslation().y / MOUSE_BUFFER));
        } else if (mouse.getLocalTranslation().y < 0) {
            lookDown.performAction(
                time * (mouse.getLocalTranslation().y / MOUSE_BUFFER) * -1);
        }

    }
    /* (non-Javadoc)
     * @see com.jme.input.action.MouseInputAction#setMouse(com.jme.input.Mouse)
     */
    public void setMouse(Mouse mouse) {
        if(mouse instanceof RelativeMouse) {
            this.mouse = (RelativeMouse)mouse;
        } else {
            throw new JmeException("MouseLook must take a RelativeMouse.");
        }
    }

}
