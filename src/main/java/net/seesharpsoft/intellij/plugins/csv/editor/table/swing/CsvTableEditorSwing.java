package net.seesharpsoft.intellij.plugins.csv.editor.table.swing;

import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorLocation;
import com.intellij.openapi.fileEditor.FileEditorState;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Key;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.ui.table.JBTable;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.beans.PropertyChangeListener;

public class CsvTableEditorSwing implements FileEditor {
    public static final String EDITOR_NAME = "Table Editor";

    private JPanel panelMain;
    private JScrollPane tableScrollPane;
    private JBTable tblEditor;

    private JTable rowHeadersTable;

    public CsvTableEditorSwing(@NotNull Project projectArg, @NotNull VirtualFile fileArg) {
        initializedUIComponents();
    }

    @Override
    public @Nls(capitalization = Nls.Capitalization.Title) @NotNull String getName() {
        return EDITOR_NAME;
    }

    @Override
    public @NotNull JComponent getComponent() {
        return panelMain;
    }

    @Override
    public @Nullable JComponent getPreferredFocusedComponent() {
        return tblEditor;
    }

    @Override
    public void setState(@NotNull FileEditorState state) {

    }

    @Override
    public boolean isModified() {
        return false;
    }

    @Override
    public boolean isValid() {
        return true;
    }

    @Override
    public @Nullable FileEditorLocation getCurrentLocation() {
        return null;
    }

    @Override
    public void addPropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public void removePropertyChangeListener(@NotNull PropertyChangeListener listener) {

    }

    @Override
    public <T> @Nullable T getUserData(@NotNull Key<T> key) {
        return null;
    }

    @Override
    public <T> void putUserData(@NotNull Key<T> key, @Nullable T value) {

    }

    @Override
    public void dispose() {

    }

    private void createUIComponents() {
        tblEditor = new JBTable(new DefaultTableModel(0, 0));
    }

    private void initializedUIComponents() {
        rowHeadersTable = TableRowUtilities.addNumberColumn(tblEditor, 10);
    }
}
