package com.baeldung.intellij.stackoverflowplugin;

import com.intellij.ide.BrowserUtil;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import org.jetbrains.annotations.NotNull;

import java.util.concurrent.TimeUnit;

public class AskQuestionAction extends AnAction {
    @Override
    public void actionPerformed(AnActionEvent e) {
//        BrowserUtil.browse("https://stackoverflow.com/questions/ask");

        Project project = e.getProject();
        ProgressManager.getInstance().run(new Task.Backgroundable(project, "Title") {
            public void run(@NotNull ProgressIndicator progressIndicator) {

                for (double i = 0.0; i <= 1.0; i += 0.1) {
                    try {
                        progressIndicator.setFraction(i);
                        progressIndicator.setText(String.format("%.2f to finish", i));

                        TimeUnit.SECONDS.sleep(10);
                    } catch (Exception e) {

                    }
                }
            }
        });
    }
}