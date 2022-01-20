package net.seesharpsoft.intellij.plugins.csv.settings;

import com.intellij.application.options.editor.EditorOptionsProvider;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.util.NlsContexts;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Objects;

public class CsvEditorSettingsProvider implements EditorOptionsProvider {
    public static final String CSV_EDITOR_SETTINGS_ID = "Csv.Editor.Settings";

    private JPanel myMainPanel;
    private JComboBox cbEditorUsage;

    @Override
    public @NotNull @NonNls String getId() {
        return CSV_EDITOR_SETTINGS_ID;
    }

    @Override
    public @NlsContexts.ConfigurableName String getDisplayName() {
        return "CSV/TSV/PSV";
    }

    @Override
    public @Nullable JComponent createComponent() {
        return myMainPanel;
    }

    @Override
    public boolean isModified() {
        CsvEditorSettings csvEditorSettings = CsvEditorSettings.getInstance();
        return !Objects.equals(cbEditorUsage.getSelectedIndex(), csvEditorSettings.getEditorPrio().ordinal());
    }

    @Override
    public void reset() {
        CsvEditorSettings csvEditorSettings = CsvEditorSettings.getInstance();
        cbEditorUsage.setSelectedIndex(csvEditorSettings.getEditorPrio().ordinal());
    }

    @Override
    public void apply() throws ConfigurationException {
        CsvEditorSettings csvEditorSettings = CsvEditorSettings.getInstance();
        csvEditorSettings.setEditorPrio(CsvEditorSettings.EditorPrio.values()[cbEditorUsage.getSelectedIndex()]);
    }
}
