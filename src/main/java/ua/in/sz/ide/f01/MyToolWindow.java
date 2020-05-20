package ua.in.sz.ide.f01;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.treeStructure.SimpleTree;


import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MyToolWindow {

    private JPanel myToolWindowContent;
    private SimpleTree simpleTree1;

    public MyToolWindow(ToolWindow toolWindow) {
        createRepositoryTree();
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;

    private void createRepositoryTree() {
        simpleTree1.setCellRenderer(new TreeCellRendererLivingDoc());
        simpleTree1.setRootVisible(true);

        rootNode = new DefaultMutableTreeNode(getDefaultRootNode());
        treeModel = new DefaultTreeModel(rootNode, true);
        simpleTree1.setModel(treeModel);
    }

    private Node getDefaultRootNode() {
        return new Node("Root node");
    }
}
