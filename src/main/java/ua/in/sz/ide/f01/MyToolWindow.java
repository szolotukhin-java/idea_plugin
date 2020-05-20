package ua.in.sz.ide.f01;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.treeStructure.SimpleTree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class MyToolWindow {

    private JPanel myToolWindowContent;
    private SimpleTree simpleTree1;
    private DefaultTreeModel treeModel;
    private DefaultMutableTreeNode rootNode;

    public MyToolWindow(ToolWindow toolWindow) {
        createRepositoryTree();
        loadRepositories();
    }

    public JPanel getContent() {
        return myToolWindowContent;
    }

    private void createRepositoryTree() {
        simpleTree1.setCellRenderer(new TreeCellRendererLivingDoc());
        simpleTree1.setRootVisible(true);

        rootNode = new DefaultMutableTreeNode(new Node("Root node"));
        treeModel = new DefaultTreeModel(rootNode, true);
        simpleTree1.setModel(treeModel);
    }

    private void loadRepositories() {
        for (int i = 0; i < 10; i++) {
            Node moduleNode = new Node("child" + i);
            DefaultMutableTreeNode moduleTreeNode = new DefaultMutableTreeNode(moduleNode, false);
            rootNode.add(moduleTreeNode);
        }

        treeModel.reload();
    }
}
