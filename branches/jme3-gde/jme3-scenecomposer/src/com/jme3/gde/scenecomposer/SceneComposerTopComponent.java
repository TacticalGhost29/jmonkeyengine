/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.gde.scenecomposer;

import com.jme3.asset.AssetManager;
import com.jme3.gde.core.assets.ProjectAssetManager;
import com.jme3.gde.core.scene.PreviewRequest;
import com.jme3.gde.core.scene.SceneApplication;
import com.jme3.gde.core.scene.SceneListener;
import com.jme3.gde.core.scene.SceneRequest;
import com.jme3.gde.core.scene.controller.SceneToolController;
import com.jme3.gde.core.sceneexplorer.nodes.JmeNode;
import com.jme3.gde.core.sceneexplorer.nodes.JmeSpatial;
import com.jme3.gde.core.sceneexplorer.nodes.NodeUtility;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.io.IOException;
import java.util.Collection;
import java.util.logging.Logger;
import javax.swing.border.TitledBorder;
import org.openide.util.Lookup.Result;
import org.openide.util.LookupEvent;
import org.openide.util.NbBundle;
import org.openide.windows.TopComponent;
import org.openide.windows.WindowManager;
import org.openide.util.ImageUtilities;
import org.netbeans.api.settings.ConvertAsProperties;
import org.openide.DialogDisplayer;
import org.openide.NotifyDescriptor;
import org.openide.NotifyDescriptor.Confirmation;
import org.openide.cookies.SaveCookie;
import org.openide.filesystems.FileObject;
import org.openide.util.HelpCtx;
import org.openide.util.LookupListener;
import org.openide.util.Utilities;

/**
 * TODO: some threading stuff
 */
@ConvertAsProperties(dtd = "-//com.jme3.gde.scenecomposer//SceneComposer//EN",
autostore = false)
public final class SceneComposerTopComponent extends TopComponent implements SceneListener, LookupListener {

    private static SceneComposerTopComponent instance;
    /** path to the icon used by the component and its open action */
    static final String ICON_PATH = "com/jme3/gde/scenecomposer/jme-logo24.png";
    private static final String PREFERRED_ID = "SceneComposerTopComponent";
    private final Result<JmeSpatial> result;
    ComposerCameraController camController;
    SceneToolController toolController;
    SceneEditorController editorController;
    private SaveCookie saveCookie = new SaveCookieImpl();
    private SceneRequest currentRequest;

    public SceneComposerTopComponent() {
        initComponents();
        setName(NbBundle.getMessage(SceneComposerTopComponent.class, "CTL_SceneComposerTopComponent"));
        setToolTipText(NbBundle.getMessage(SceneComposerTopComponent.class, "HINT_SceneComposerTopComponent"));
        setIcon(ImageUtilities.loadImage(ICON_PATH, true));
        result = Utilities.actionsGlobalContext().lookupResult(JmeSpatial.class);
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        sceneInfoPanel = new javax.swing.JPanel();
        sceneInfoLabel1 = new javax.swing.JLabel();
        sceneInfoLabel2 = new javax.swing.JLabel();
        palettePanel = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jList1 = new javax.swing.JList();
        jToolBar1 = new javax.swing.JToolBar();
        addObjectButton = new javax.swing.JButton();
        addCursorButton = new javax.swing.JButton();
        moveToCursorButton = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JToolBar.Separator();
        jLabel3 = new javax.swing.JLabel();
        showSelectionToggleButton = new javax.swing.JToggleButton();
        showGridToggleButton = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JToolBar.Separator();
        jLabel1 = new javax.swing.JLabel();
        resetCursorButton = new javax.swing.JButton();
        cursorToSelectionButton = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        jLabel2 = new javax.swing.JLabel();
        camToCursorSelectionButton = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        jPanel3 = new javax.swing.JPanel();
        toolsPanel = new javax.swing.JPanel();
        createTangentsButton = new javax.swing.JButton();
        createPhysicsMeshButton = new javax.swing.JButton();

        sceneInfoPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.sceneInfoPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(sceneInfoLabel1, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.sceneInfoLabel1.text")); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(sceneInfoLabel2, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.sceneInfoLabel2.text")); // NOI18N

        org.jdesktop.layout.GroupLayout sceneInfoPanelLayout = new org.jdesktop.layout.GroupLayout(sceneInfoPanel);
        sceneInfoPanel.setLayout(sceneInfoPanelLayout);
        sceneInfoPanelLayout.setHorizontalGroup(
            sceneInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(sceneInfoLabel1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
            .add(sceneInfoLabel2, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 227, Short.MAX_VALUE)
        );
        sceneInfoPanelLayout.setVerticalGroup(
            sceneInfoPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(sceneInfoPanelLayout.createSequentialGroup()
                .add(sceneInfoLabel1)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sceneInfoLabel2)
                .addContainerGap(112, Short.MAX_VALUE))
        );

        palettePanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.palettePanel.border.title"))); // NOI18N

        jScrollPane1.setViewportView(jList1);

        org.jdesktop.layout.GroupLayout palettePanelLayout = new org.jdesktop.layout.GroupLayout(palettePanel);
        palettePanel.setLayout(palettePanelLayout);
        palettePanelLayout.setHorizontalGroup(
            palettePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 211, Short.MAX_VALUE)
        );
        palettePanelLayout.setVerticalGroup(
            palettePanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(org.jdesktop.layout.GroupLayout.TRAILING, jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 152, Short.MAX_VALUE)
        );

        jToolBar1.setFloatable(false);
        jToolBar1.setRollover(true);

        addObjectButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jme3/gde/scenecomposer/add.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addObjectButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.addObjectButton.text")); // NOI18N
        addObjectButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.addObjectButton.toolTipText")); // NOI18N
        addObjectButton.setEnabled(false);
        addObjectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addObjectButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addObjectButton);

        addCursorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jme3/gde/scenecomposer/add.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(addCursorButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.addCursorButton.text")); // NOI18N
        addCursorButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.addCursorButton.toolTipText")); // NOI18N
        addCursorButton.setEnabled(false);
        addCursorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addCursorButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(addCursorButton);

        moveToCursorButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jme3/gde/scenecomposer/move.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(moveToCursorButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.moveToCursorButton.text")); // NOI18N
        moveToCursorButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.moveToCursorButton.toolTipText")); // NOI18N
        moveToCursorButton.setFocusable(false);
        moveToCursorButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        moveToCursorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        moveToCursorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveToCursorButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(moveToCursorButton);
        jToolBar1.add(jSeparator4);

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jme3/gde/scenecomposer/box_wire.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel3, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.jLabel3.text")); // NOI18N
        jToolBar1.add(jLabel3);

        org.openide.awt.Mnemonics.setLocalizedText(showSelectionToggleButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.showSelectionToggleButton.text")); // NOI18N
        showSelectionToggleButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.showSelectionToggleButton.toolTipText")); // NOI18N
        showSelectionToggleButton.setFocusable(false);
        showSelectionToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showSelectionToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showSelectionToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showSelectionToggleButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(showSelectionToggleButton);

        org.openide.awt.Mnemonics.setLocalizedText(showGridToggleButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.showGridToggleButton.text")); // NOI18N
        showGridToggleButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.showGridToggleButton.toolTipText")); // NOI18N
        showGridToggleButton.setFocusable(false);
        showGridToggleButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        showGridToggleButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        showGridToggleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                showGridToggleButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(showGridToggleButton);
        jToolBar1.add(jSeparator3);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jme3/gde/scenecomposer/cursor.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel1, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.jLabel1.text")); // NOI18N
        jToolBar1.add(jLabel1);

        org.openide.awt.Mnemonics.setLocalizedText(resetCursorButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.resetCursorButton.text")); // NOI18N
        resetCursorButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.resetCursorButton.toolTipText")); // NOI18N
        resetCursorButton.setFocusable(false);
        resetCursorButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        resetCursorButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        resetCursorButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                resetCursorButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(resetCursorButton);

        org.openide.awt.Mnemonics.setLocalizedText(cursorToSelectionButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.cursorToSelectionButton.text")); // NOI18N
        cursorToSelectionButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.cursorToSelectionButton.toolTipText")); // NOI18N
        cursorToSelectionButton.setFocusable(false);
        cursorToSelectionButton.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cursorToSelectionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cursorToSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cursorToSelectionButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(cursorToSelectionButton);
        jToolBar1.add(jSeparator2);

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/jme3/gde/scenecomposer/camera.gif"))); // NOI18N
        org.openide.awt.Mnemonics.setLocalizedText(jLabel2, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.jLabel2.text")); // NOI18N
        jToolBar1.add(jLabel2);

        org.openide.awt.Mnemonics.setLocalizedText(camToCursorSelectionButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.camToCursorSelectionButton.text")); // NOI18N
        camToCursorSelectionButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.camToCursorSelectionButton.toolTipText")); // NOI18N
        camToCursorSelectionButton.setFocusable(false);
        camToCursorSelectionButton.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        camToCursorSelectionButton.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        camToCursorSelectionButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                camToCursorSelectionButtonActionPerformed(evt);
            }
        });
        jToolBar1.add(camToCursorSelectionButton);
        jToolBar1.add(jSeparator1);

        org.jdesktop.layout.GroupLayout jPanel3Layout = new org.jdesktop.layout.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 317, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(0, 21, Short.MAX_VALUE)
        );

        jToolBar1.add(jPanel3);

        toolsPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.toolsPanel.border.title"))); // NOI18N

        org.openide.awt.Mnemonics.setLocalizedText(createTangentsButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.createTangentsButton.text")); // NOI18N
        createTangentsButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.createTangentsButton.toolTipText")); // NOI18N
        createTangentsButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createTangentsButtonActionPerformed(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(createPhysicsMeshButton, org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.createPhysicsMeshButton.text")); // NOI18N
        createPhysicsMeshButton.setToolTipText(org.openide.util.NbBundle.getMessage(SceneComposerTopComponent.class, "SceneComposerTopComponent.createPhysicsMeshButton.toolTipText")); // NOI18N
        createPhysicsMeshButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createPhysicsMeshButtonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout toolsPanelLayout = new org.jdesktop.layout.GroupLayout(toolsPanel);
        toolsPanel.setLayout(toolsPanelLayout);
        toolsPanelLayout.setHorizontalGroup(
            toolsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(toolsPanelLayout.createSequentialGroup()
                .add(toolsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(createTangentsButton)
                    .add(createPhysicsMeshButton))
                .addContainerGap(78, Short.MAX_VALUE))
        );
        toolsPanelLayout.setVerticalGroup(
            toolsPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(toolsPanelLayout.createSequentialGroup()
                .add(createTangentsButton)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(createPhysicsMeshButton)
                .addContainerGap(90, Short.MAX_VALUE))
        );

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(palettePanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(toolsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(sceneInfoPanel, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
            .add(jToolBar1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 823, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(jToolBar1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 25, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(toolsPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(palettePanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(org.jdesktop.layout.GroupLayout.TRAILING, sceneInfoPanel, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void addObjectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addObjectButtonActionPerformed
        if (jList1.getSelectedValue() != null && editorController != null) {
            editorController.addSpatial(jList1.getSelectedValue().toString());
        }

    }//GEN-LAST:event_addObjectButtonActionPerformed

    private void addCursorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addCursorButtonActionPerformed
        if (jList1.getSelectedValue() != null && editorController != null) {
            editorController.addSpatial(jList1.getSelectedValue().toString(), toolController.getCursorLocation());
        }

    }//GEN-LAST:event_addCursorButtonActionPerformed

    private void showSelectionToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showSelectionToggleButtonActionPerformed
        if (toolController != null) {
            toolController.setShowSelection(showSelectionToggleButton.isSelected());
        }
    }//GEN-LAST:event_showSelectionToggleButtonActionPerformed

    private void showGridToggleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_showGridToggleButtonActionPerformed
        if (toolController != null) {
            toolController.setShowGrid(showGridToggleButton.isSelected());
        }
    }//GEN-LAST:event_showGridToggleButtonActionPerformed

    private void moveToCursorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToCursorButtonActionPerformed
        if (editorController != null) {
            editorController.moveSelectedSpatial(toolController.getCursorLocation());
        }
    }//GEN-LAST:event_moveToCursorButtonActionPerformed

    private void resetCursorButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_resetCursorButtonActionPerformed
        if (toolController != null) {
            toolController.setCursorLocation(Vector3f.ZERO);
        }
    }//GEN-LAST:event_resetCursorButtonActionPerformed

    private void camToCursorSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_camToCursorSelectionButtonActionPerformed
        camController.setCamFocus(toolController.getCursorLocation());
    }//GEN-LAST:event_camToCursorSelectionButtonActionPerformed

    private void cursorToSelectionButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cursorToSelectionButtonActionPerformed
        if (toolController != null) {
            toolController.snapCursorToSelection();
        }
    }//GEN-LAST:event_cursorToSelectionButtonActionPerformed

    private void createTangentsButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createTangentsButtonActionPerformed
        if (editorController != null) {
            editorController.createTangentsForSelectedSpatial();
        }
    }//GEN-LAST:event_createTangentsButtonActionPerformed

    private void createPhysicsMeshButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createPhysicsMeshButtonActionPerformed
        if (editorController != null) {
            editorController.createPhysicsMeshForSelectedSpatial();
        }
    }//GEN-LAST:event_createPhysicsMeshButtonActionPerformed
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addCursorButton;
    private javax.swing.JButton addObjectButton;
    private javax.swing.JButton camToCursorSelectionButton;
    private javax.swing.JButton createPhysicsMeshButton;
    private javax.swing.JButton createTangentsButton;
    private javax.swing.JButton cursorToSelectionButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JList jList1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JToolBar.Separator jSeparator3;
    private javax.swing.JToolBar.Separator jSeparator4;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JButton moveToCursorButton;
    private javax.swing.JPanel palettePanel;
    private javax.swing.JButton resetCursorButton;
    private javax.swing.JLabel sceneInfoLabel1;
    private javax.swing.JLabel sceneInfoLabel2;
    private javax.swing.JPanel sceneInfoPanel;
    private javax.swing.JToggleButton showGridToggleButton;
    private javax.swing.JToggleButton showSelectionToggleButton;
    private javax.swing.JPanel toolsPanel;
    // End of variables declaration//GEN-END:variables

    /**
     * Gets default instance. Do not use directly: reserved for *.settings files only,
     * i.e. deserialization routines; otherwise you could get a non-deserialized instance.
     * To obtain the singleton instance, use {@link #findInstance}.
     */
    public static synchronized SceneComposerTopComponent getDefault() {
        if (instance == null) {
            instance = new SceneComposerTopComponent();
        }
        return instance;
    }

    /**
     * Obtain the SceneComposerTopComponent instance. Never call {@link #getDefault} directly!
     */
    public static synchronized SceneComposerTopComponent findInstance() {
        TopComponent win = WindowManager.getDefault().findTopComponent(PREFERRED_ID);
        if (win == null) {
            Logger.getLogger(SceneComposerTopComponent.class.getName()).warning(
                    "Cannot find " + PREFERRED_ID + " component. It will not be located properly in the window system.");
            return getDefault();
        }
        if (win instanceof SceneComposerTopComponent) {
            return (SceneComposerTopComponent) win;
        }
        Logger.getLogger(SceneComposerTopComponent.class.getName()).warning(
                "There seem to be multiple components with the '" + PREFERRED_ID
                + "' ID. That is a potential source of errors and unexpected behavior.");
        return getDefault();
    }

    @Override
    public int getPersistenceType() {
        return TopComponent.PERSISTENCE_ALWAYS;
    }

    @Override
    public HelpCtx getHelpCtx() {
        HelpCtx ctx = new HelpCtx("com.jme3.gde.scenecomposer.usage");
        //this call is for single components:
        //HelpCtx.setHelpIDString(this, "com.jme3.gde.core.sceneviewer");
        return ctx;
    }

    @Override
    public void componentOpened() {
        super.componentOpened();
    }

    @Override
    public void componentClosed() {
        super.componentClosed();
        if (currentRequest != null) {
            SceneApplication.getApplication().closeScene(currentRequest);
        }
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

    private void setSelectedObjectText(final String text) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                if (text != null) {
                    //XXX: wtf? why do i have to repaint?
                    ((TitledBorder) palettePanel.getBorder()).setTitle("Palette:" + text);
                    palettePanel.repaint();
                    addObjectButton.setEnabled(true);
                    addCursorButton.setEnabled(true);
                } else {
                    ((TitledBorder) palettePanel.getBorder()).setTitle("no spatial selected");
                    palettePanel.repaint();
                    addObjectButton.setEnabled(false);
                    addCursorButton.setEnabled(false);
                }
            }
        });
    }

    private void setSelectionData(final Boolean node) {
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                if (node == null) {
                    jList1.setListData(new String[]{});
                } else if (node) {
                    jList1.setListData(new String[]{"Node", "Particle Emitter", "Audio Node", "Picture", "Point Light", "Directional Light"});
                } else {
                    jList1.setListData(new String[]{"Point Light", "Directional Light"});
                }
            }
        });
    }

    /**
     * method to set the state of the ui items
     */
    private void setSceneInfo(final JmeNode jmeNode, final boolean active) {
        final SceneComposerTopComponent inst = this;
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {
                if (jmeNode != null) {
                    ((TitledBorder) sceneInfoPanel.getBorder()).setTitle(jmeNode.getName());
                    selectSpatial(jmeNode);
                } else {
                    ((TitledBorder) sceneInfoPanel.getBorder()).setTitle("");
                }
                //XXX: wtf? why do i have to repaint?
                sceneInfoPanel.repaint();

                if (!active) {
                    result.removeLookupListener(inst);
                    addObjectButton.setEnabled(false);
                    addCursorButton.setEnabled(false);
                    showSelectionToggleButton.setSelected(false);
                    showGridToggleButton.setSelected(false);
                    sceneInfoLabel1.setText("");
                    sceneInfoLabel2.setText("");
                    sceneInfoLabel1.setToolTipText("");
                    sceneInfoLabel2.setToolTipText("");
                    close();
                } else {
                    showSelectionToggleButton.setSelected(false);
                    showGridToggleButton.setSelected(false);
                    //TODO: threading
//                    sceneInfoLabel1.setText("Name: " + currentFileObject.getNameExt());
//                    sceneInfoLabel2.setText("Size: " + currentFileObject.getSize() / 1024 + " kB");
//                    sceneInfoLabel1.setToolTipText("Name: " + currentFileObject.getNameExt());
//                    sceneInfoLabel2.setToolTipText("Size: " + currentFileObject.getSize() / 1024 + " kB");
                    open();
                    requestActive();
                }
            }
        });
    }

    public void openScene(Spatial spat, FileObject file, ProjectAssetManager manager) {
        SceneApplication.getApplication().addSceneListener(this);
        result.addLookupListener(this);
        //TODO: handle request change
        Node node;
        if (spat instanceof Node) {
            node = (Node) spat;
        } else {
            node = new Node();
            node.attachChild(spat);
        }
        JmeNode jmeNode = NodeUtility.createNode(node, saveCookie);
        SceneRequest request = new SceneRequest(this, jmeNode, manager);
        if (editorController != null) {
            editorController.cleanup();
        }
        editorController = new SceneEditorController(jmeNode, file);
        this.currentRequest = request;
        request.setWindowTitle("SceneViewer - " + request.getRootNode().getName() + " (SceneComposer)");
        request.setToolNode(new Node("SceneComposerToolNode"));
        SceneApplication.getApplication().requestScene(request);
    }

    void addModel(AssetManager manager, String assetName) {
        if (editorController != null) {
            editorController.addModel(manager, assetName);
        }
    }

    void linkModel(AssetManager manager, String assetName) {
        if (editorController != null) {
            editorController.linkModel(manager, assetName);
        }
    }

    public void doMoveCursor(Vector3f vector) {
        if (toolController != null) {
            toolController.doSetCursorLocation(vector);
        }
    }

    /**
     * listener for node selection changes
     */
    public void resultChanged(LookupEvent ev) {
        if (currentRequest == null || !currentRequest.isDisplayed()) {
            return;
        }
        Collection<JmeSpatial> items = (Collection<JmeSpatial>) result.allInstances();
        for (JmeSpatial spatial : items) {
            selectSpatial(spatial);
            return;
        }
    }

    private void selectSpatial(JmeSpatial spatial) {
        if (spatial == null) {
            setSelectedObjectText(null);
            setSelectionData(null);
            if (editorController != null) {
                editorController.setSelectedSpat(spatial);
            }
            setActivatedNodes(new org.openide.nodes.Node[]{});
            return;
        } else {
            if (toolController != null) {
                toolController.updateSelection(spatial.getLookup().lookup(Spatial.class));
            }
        }
        if (editorController == null) {
            return;
        }
        editorController.setSelectedSpat(spatial);
        if (spatial.getLookup().lookup(Node.class) != null) {
            setSelectionData(true);
            setSelectedObjectText(spatial.getLookup().lookup(Node.class).getName());
        } else if (spatial.getLookup().lookup(Spatial.class) != null) {
            setSelectionData(false);
            setSelectedObjectText(spatial.getLookup().lookup(Spatial.class).getName());
        } else {
            setSelectedObjectText(null);
            setSelectionData(false);
        }
        SceneApplication.getApplication().setSelectedNode(spatial);
        setActivatedNodes(new org.openide.nodes.Node[]{spatial});
    }

    private void cleanupControllers() {
        if (camController != null) {
            camController.disable();
            camController = null;
        }
        if (toolController != null) {
            toolController.cleanup();
            toolController = null;
        }
        if (editorController != null) {
            editorController.cleanup();
            editorController = null;
        }
    }

    /*
     * SceneListener
     */
    public void sceneRequested(SceneRequest request) {
        if (request.equals(currentRequest)) {
            setSceneInfo(currentRequest.getRootNode(), true);
            if (camController != null) {
                camController.disable();
            }
            if (toolController != null) {
                toolController.cleanup();
            }
            toolController = new SceneToolController(currentRequest.getToolNode(), currentRequest.getManager().getManager());
            camController = new ComposerCameraController(SceneApplication.getApplication().getCamera(), request.getRootNode());
            camController.setMaster(this);
            camController.enable();
        }/* else {
        SceneApplication.getApplication().removeSceneListener(this);
        currentRequest = null;
        setSceneInfo(null, false);
        cleanupControllers();
        }*/
    }

    public boolean sceneClose(SceneRequest request) {
        if (request.equals(currentRequest)) {
            if (checkSaved()) {
                SceneApplication.getApplication().removeSceneListener(this);
                currentRequest = null;
                setSceneInfo(null, false);
                cleanupControllers();
            } else {
                return false;
            }
        }
        return true;
    }

    private boolean checkSaved() {
        if (editorController != null && editorController.isNeedSave()) {
            Confirmation msg = new NotifyDescriptor.Confirmation(
                    "Your Scene is not saved, do you want to save?",
                    NotifyDescriptor.YES_NO_OPTION,
                    NotifyDescriptor.WARNING_MESSAGE);
            Object result = DialogDisplayer.getDefault().notify(msg);
            if (NotifyDescriptor.CANCEL_OPTION.equals(result)) {
                return false;
            } else if (NotifyDescriptor.YES_OPTION.equals(result)) {
                editorController.saveScene();
                return true;
            } else if (NotifyDescriptor.NO_OPTION.equals(result)) {
                return true;
            }
        }
        return true;
    }

    public void previewRequested(PreviewRequest request) {
    }

    public class SaveCookieImpl implements SaveCookie {

        public void save() throws IOException {
            editorController.saveScene();
//            setSceneInfo(currentRequest.getRootNode(), true);
        }
    }
}
