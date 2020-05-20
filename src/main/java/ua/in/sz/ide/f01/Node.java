package ua.in.sz.ide.f01;

import org.apache.commons.lang3.builder.ToStringBuilder;

import javax.swing.*;
import java.io.Serializable;

public class Node implements Serializable {

    private Node parent;
    private String nodeName;
    private transient Icon icon;

    public Node() {
        parent = null;
    }

    public Node(final String nodeName, Icon icon) {
        this.nodeName = nodeName;
        this.icon = icon;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
//                .append("type", type)
                .append("parent", parent)
                .append("nodeName", nodeName)
                .toString();
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(final Icon icon) {
        this.icon = icon;
    }

    public String getNodeName() {
        return nodeName;
    }

    public void setNodeName(final String nodeName) {
        this.nodeName = nodeName;
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(final Node parent) {
        this.parent = parent;
    }
}
