package net.seesharpsoft.intellij.plugins.csv.settings;

import com.intellij.openapi.application.Application;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.components.PersistentStateComponent;
import com.intellij.openapi.components.ServiceManager;
import com.intellij.openapi.components.State;
import com.intellij.openapi.components.Storage;
import net.seesharpsoft.intellij.plugins.csv.CsvStorageHelper;
import org.jetbrains.annotations.NotNull;

@State(
        name = "CsvEditorSettings",
        storages = {@Storage(CsvStorageHelper.CSV_STATE_STORAGE_FILE)}
)
public class CsvEditorSettings implements PersistentStateComponent<CsvEditorSettings.OptionSet> {
    public enum EditorPrio {
        TEXT_FIRST,
        TABLE_FIRST,
        TEXT_ONLY
    }

    private OptionSet myOptions = new OptionSet();

    public static final class OptionSet {
        public EditorPrio EDITOR_PRIO = EditorPrio.TEXT_FIRST;
    }

    public static CsvEditorSettings getInstance() {
        Application application = ApplicationManager.getApplication();
        return application.isDisposed() ? new CsvEditorSettings() : ServiceManager.getService(CsvEditorSettings.class);
    }

    public void setEditorPrio(EditorPrio editorPrio) {
        getState().EDITOR_PRIO = editorPrio;
    }

    public EditorPrio getEditorPrio() {
        return getState().EDITOR_PRIO;
    }

    @Override
    public OptionSet getState() {
        return this.myOptions;
    }

    @Override
    public void loadState(@NotNull OptionSet state) {
        this.myOptions = state;
    }
}
