package ua.in.sz.ide.f01;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import java.awt.*;

public class TreeCellRendererLivingDoc extends DefaultTreeCellRenderer {

    private static final long serialVersionUID = 8133945553515022452L;

    @Override
    public Component getTreeCellRendererComponent(JTree tree, Object value, boolean selected, boolean expanded, boolean leaf, int row, boolean hasFocus) {

        Component component = super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        Object object = ((DefaultMutableTreeNode) value).getUserObject();

        if (object instanceof Node) {
            Node node = (Node) object;

            setText(node.getNodeName());
        }
        return component;
    }
}
