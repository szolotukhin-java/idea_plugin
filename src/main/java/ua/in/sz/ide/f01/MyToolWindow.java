package ua.in.sz.ide.f01;

import com.intellij.openapi.wm.ToolWindow;
import com.intellij.ui.treeStructure.SimpleTree;

import javax.swing.*;

public class MyToolWindow {

    private JPanel myToolWindowContent;
    private SimpleTree simpleTree1;

    public MyToolWindow(ToolWindow toolWindow) {

    }

    public JPanel getContent() {
        return myToolWindowContent;
    }
}
