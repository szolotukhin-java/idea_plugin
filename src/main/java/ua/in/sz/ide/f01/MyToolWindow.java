package ua.in.sz.ide.f01;

import com.intellij.icons.AllIcons;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPopupMenu;
import com.intellij.openapi.actionSystem.ActionToolbar;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.ui.SimpleToolWindowPanel;
import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.PopupHandler;
import com.intellij.ui.treeStructure.SimpleTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import java.awt.*;

public class MyToolWindow  extends SimpleToolWindowPanel {

    private JPanel myToolWindowContent;
    private SimpleTree simpleTree1;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;
    private transient DefaultActionGroup actionGroup;
    private transient ActionToolbar toolBar;

    public MyToolWindow(ToolWindow toolWindow) {
        super(false);

        createRepositoryTree();
        createActionToolBar();

        configureActions();

        loadRepositories();
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    private void createRepositoryTree() {
        simpleTree1.setCellRenderer(new TreeCellRendererLivingDoc());
        simpleTree1.setRootVisible(true);

        rootNode = new DefaultMutableTreeNode(new Node("Root node", AllIcons.Nodes.Project));
        treeModel = new DefaultTreeModel(rootNode, true);
        simpleTree1.setModel(treeModel);
    }

    private void loadRepositories() {
        for (int i = 0; i < 10; i++) {
            Node moduleNode = new Node("child" + i, AllIcons.Nodes.Module);
            DefaultMutableTreeNode moduleTreeNode = new DefaultMutableTreeNode(moduleNode, false);
            rootNode.add(moduleTreeNode);
        }

        treeModel.reload();
    }

    private void createActionToolBar() {

        ActionManager actionManager = ActionManager.getInstance();
        actionGroup = new DefaultActionGroup(null, true);

        toolBar = actionManager.createActionToolbar("LivingDoc.RepositoryViewToolbar", actionGroup, false);
        toolBar.adjustTheSameSize(true);
        toolBar.setTargetComponent(simpleTree1);
        setToolbar(toolBar.getComponent());
    }

    private void configureActions() {
        // Context menu with the plugin actions.
        simpleTree1.addMouseListener(new PopupHandler() {

            @Override
            public void invokePopup(Component comp, int x, int y) {

                ActionPopupMenu actionPopupMenu = ActionManager.getInstance()
                        .createActionPopupMenu("LivingDoc.RepositoryViewToolbar", actionGroup);
                actionPopupMenu.getComponent().show(comp, x, y);
            }
        });
    }
}
