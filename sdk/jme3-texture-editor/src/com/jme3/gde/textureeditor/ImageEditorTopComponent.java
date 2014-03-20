/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.gde.textureeditor;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Logger;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.filesystems.FileObject;
import org.openide.util.lookup.AbstractLookup;

/**
 * Top component which displays something.
 */
@ConvertAsProperties(dtd = "-//com.jme3.gde.textureeditor//ImageEditor//EN",
autostore = false)
public final class ImageEditorTopComponent extends TopComponent {

    private static ImageEditorTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/jme3/gde/textureeditor/Computer_File_067.gif";
    private static final String PREFERRED_ID = "ImageEditorTopComponent";
    private final ImageEditorComponent EDITOR = ImageEditorComponent.create();

    public ImageEditorTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(ImageEditorTopComponent.class, "CTL_ImageEditorTopComponent"));
        setToolTipText(NbBundle.getMessage(ImageEditorTopComponent.class, "HINT_ImageEditorTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        add(EDITOR.getComponent());

        //Add the dynamic object to the TopComponent Lookup:
        associateLookup(new AbstractLookup(EDITOR.getContent()));
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setLayout(new javax.swing.BoxLayout(this, javax.swing.BoxLayout.LINE_AXIS));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized ImageEditorTopComponent getDefault() {
        if (instance == null) {
            instance = new ImageEditorTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the ImageEditorTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized ImageEditorTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(ImageEditorTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof ImageEditorTopComponent) {
            return (ImageEditorTopComponent) win;
        }
        Logger.getLogger(ImageEditorTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_NEVER;
    }

    @Override
    public void componentOpened() {
        // TODO add custom code on component opening
    }

    @Override
    public void componentClosed() {
        // TODO add custom code on component closing
    }

    void writeProperties(java.util.Properties p) {
        // better to version settings since initial version as advocated at
        // http://wiki.apidesign.org/wiki/PropertyFiles
        p.setProperty("version", "1.0");
        // TODO store your settings
    }

    Object readProperties(java.util.Properties p) {
        if (instance == null) {
            instance = this;
        }
        instance.readPropertiesImpl(p);
        return instance;
    }

    private void readPropertiesImpl(java.util.Properties p) {
        String version = p.getProperty("version");
        // TODO read your settings according to their version
    }

    @Override
    protected String preferredID() {
        return PREFERRED_ID;
    }

    public void setEditedImage(FileObject file) throws FileNotFoundException, IOException, URISyntaxException {
        if (file != null) {
            setName("PixelHead - " + file.getName());
        } else {
            setName("PixelHead - No name");
        }
        BufferedImage image = IOModule.create().load(file);
        EDITOR.setEditedImage(this, image, file);
    }

    void setEditedImage(BufferedImage image) {
        setName("PixelHead - NoName");
        EDITOR.setEditedImage(this, image, null);
    }
}
