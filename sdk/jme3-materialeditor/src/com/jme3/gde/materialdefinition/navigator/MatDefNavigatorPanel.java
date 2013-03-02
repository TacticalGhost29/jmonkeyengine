/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.jme3.gde.materialdefinition.navigator;

import com.jme3.gde.materialdefinition.MatDefDataObject;
import java.util.Collection;
import javax.swing.JComponent;
import javax.swing.JPanel;
import org.netbeans.spi.navigator.NavigatorPanel;
import org.openide.explorer.ExplorerManager;
import org.openide.explorer.ExplorerUtils;
import org.openide.explorer.view.BeanTreeView;
import org.openide.nodes.Node;
import org.openide.util.Lookup;
import org.openide.util.LookupEvent;
import org.openide.util.LookupListener;

/**
 *
 * @author Nehon
 */
@NavigatorPanel.Registration(mimeType = "text/jme-materialdefinition", displayName = "Material Def")
public class MatDefNavigatorPanel extends JPanel implements NavigatorPanel, ExplorerManager.Provider {

    /**
     * template for finding data in given context.
     */
    private static final Lookup.Template MY_DATA = new Lookup.Template(MatDefDataObject.class);

    /**
     * current context to work on
     */
    private Lookup.Result curContext;
    private Lookup lookup;
    /**
     * listener to context changes
     */
    private LookupListener contextL;
    private final ExplorerManager mgr = new ExplorerManager();

    /**
     * Creates new form MatDefNavigatorPanel
     */
    public MatDefNavigatorPanel() {
        initComponents();
        lookup = ExplorerUtils.createLookup(mgr, getActionMap());

    }

    public String getDisplayHint() {
        return "Material definition outline view";
    }

    @Override
    public String getDisplayName() {
        return "Bla";
    }

    public JComponent getComponent() {
        return this;
    }

    public void panelActivated(Lookup context) {
        // lookup context and listen to result to get notified about context changes
        curContext = context.lookup(MY_DATA);
        //lookup = context;
        curContext.addLookupListener(getContextListener());
        // get actual data and recompute content
        Collection data = curContext.allInstances();
        setNewContent(data);

        //  ExplorerUtils.activateActions(mgr, true);

    }

    public void panelDeactivated() {
        Collection data = curContext.allInstances();
        if (!data.isEmpty()) {
            MatDefDataObject obj = (MatDefDataObject) data.iterator().next();
            obj.getLookupContents().remove(this);
        }
        curContext.removeLookupListener(getContextListener());
        curContext = null;
        mgr.setRootContext(Node.EMPTY);
        //ExplorerUtils.activateActions(mgr, false);
    }

    public Lookup getLookup() {
        // go with default activated Node strategy
        return lookup;
    }

    /**
     * *********** non - public part ***********
     */
    private void setNewContent(Collection newData) {
        if (!newData.isEmpty()) {
            MatDefDataObject data = (MatDefDataObject) newData.iterator().next();
            data.getLookupContents().add(this);
            if (data.isLoaded()) {
                updateData(data);
            } else {
                mgr.setRootContext(Node.EMPTY);
            }            
        }        
    }

    /**
     * Accessor for listener to context
     */
    private LookupListener getContextListener() {
        if (contextL == null) {
            contextL = new ContextListener();
        }
        return contextL;
    }

    public ExplorerManager getExplorerManager() {
        return mgr;
    }

    public void updateData(MatDefDataObject data) {
        if (data != null) {
            data.getEditableFile().buildOverview(mgr);
        } else {
            mgr.setRootContext(Node.EMPTY);
        }
    }

    /**
     * Listens to changes of context and triggers proper action
     */
    private class ContextListener implements LookupListener {

        public void resultChanged(LookupEvent ev) {
            Collection data = ((Lookup.Result) ev.getSource()).allInstances();
            setNewContent(data);
        }
    } // end of ContextListener

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new BeanTreeView();

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}
