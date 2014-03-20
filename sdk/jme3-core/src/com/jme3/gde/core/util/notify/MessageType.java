/*
 * Copyright (c) 2003-2012 jMonkeyEngine
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
package com.jme3.gde.core.util.notify;

import com.jme3.gde.core.icons.IconList;
import java.net.URL;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import org.openide.NotifyDescriptor;

/**
 *
 * @author qbeukes.blogspot.com, used by metalklesk
 */
public enum MessageType {

    PLAIN(NotifyDescriptor.PLAIN_MESSAGE, IconList.chimpSmile),
    INFO(NotifyDescriptor.INFORMATION_MESSAGE, IconList.chimpSmile),
    QUESTION(NotifyDescriptor.QUESTION_MESSAGE, IconList.chimpConfused),
    EXCEPTION(NotifyDescriptor.ERROR_MESSAGE, IconList.chimpLobo),
    ERROR(NotifyDescriptor.ERROR_MESSAGE, IconList.chimpSad),
    WARNING(NotifyDescriptor.WARNING_MESSAGE, IconList.chimpNogood);
    private int notifyDescriptorType;
    private Icon icon;

    private MessageType(int notifyDescriptorType, ImageIcon icon) {
        this.notifyDescriptorType = notifyDescriptorType;
        this.icon = icon;
    }

    int getNotifyDescriptorType() {
        return notifyDescriptorType;
    }

    Icon getIcon() {
        return icon;
    }
}
