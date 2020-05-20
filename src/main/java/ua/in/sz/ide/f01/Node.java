package ua.in.sz.ide.f01;

import org.apache.commons.lang3.builder.ToStringBuilder;
import java.io.Serializable;

public class Node implements Serializable {

    private Node parent;
    private String nodeName;

    public Node() {
        parent = null;
    }

    public Node(final String nodeName, final Node parent) {
        this(nodeName);
        this.parent = parent;
    }

    public Node(final String nodeName) {
        this.nodeName = nodeName;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
//                .append("type", type)
                .append("parent", parent)
                .append("nodeName", nodeName)
                .toString();
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
