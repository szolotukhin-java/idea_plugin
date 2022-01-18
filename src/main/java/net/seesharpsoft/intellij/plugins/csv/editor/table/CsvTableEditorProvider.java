package net.seesharpsoft.intellij.plugins.csv.editor.table;

import com.intellij.lang.Language;
import com.intellij.lang.LanguageUtil;
import com.intellij.openapi.fileEditor.AsyncFileEditorProvider;
import com.intellij.openapi.fileEditor.FileEditor;
import com.intellij.openapi.fileEditor.FileEditorPolicy;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import net.seesharpsoft.intellij.plugins.csv.CsvLanguage;
import net.seesharpsoft.intellij.plugins.csv.editor.table.swing.CsvTableEditorSwing;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class CsvTableEditorProvider implements AsyncFileEditorProvider, DumbAware {
    public static final String EDITOR_TYPE_ID = "sz-csv-text-editor";

    @Override
    public @NotNull @NonNls String getEditorTypeId() {
        return EDITOR_TYPE_ID;
    }

    @Override
    public boolean accept(@NotNull Project project, @NotNull VirtualFile file) {
        return isCsvFile(project, file);
    }

    @Override
    public @NotNull FileEditorPolicy getPolicy() {
        return FileEditorPolicy.HIDE_DEFAULT_EDITOR;
    }

    @Override
    public @NotNull FileEditor createEditor(@NotNull Project project, @NotNull VirtualFile file) {
        return createEditorAsync(project, file).build();
    }

    @Override
    public @NotNull Builder createEditorAsync(@NotNull Project project, @NotNull VirtualFile file) {
        return new Builder() {
            @Override
            public FileEditor build() {
                return new CsvTableEditorSwing(project, file);
            }
        };
    }

    // ================================================================================================================
    // private file
    // ================================================================================================================

    public static boolean isCsvFile(Project project, VirtualFile file) {
        if (project == null || file == null) {
            return false;
        }
        final Language language = LanguageUtil.getLanguageForPsi(project, file);
        return language != null && language.isKindOf(CsvLanguage.INSTANCE);
    }
}
