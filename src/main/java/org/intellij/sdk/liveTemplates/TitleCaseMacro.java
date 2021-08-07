// Copyright 2000-2020 JetBrains s.r.o. and other contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.

package org.intellij.sdk.liveTemplates;

import com.intellij.codeInsight.template.*;
import com.intellij.codeInsight.template.macro.MacroBase;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.text.StringUtil;
import org.jetbrains.annotations.NotNull;

public class TitleCaseMacro extends MacroBase {
  private static final Logger log = Logger.getInstance(TitleCaseMacro.class);

  public TitleCaseMacro() {
    super("titleCase", "titleCase(String)");
  }

  /**
   * Strictly to uphold contract for constructors in base class.
   */
  private TitleCaseMacro(String name, String description) {
    super(name, description);
  }

  @Override
  protected Result calculateResult(@NotNull Expression[] params, ExpressionContext context, boolean quick) {
    if (context != null) {
      String property = context.getProperty(ExpressionContext.SELECTION);
      log.info(String.format("The selected property is [%s]", property));
    } else {
      log.info("The context is null");
    }

    String text = getTextResult(params, context, true);

    if (text == null) {
      return null;
    }

    if (text.length() > 0) {
      text = StringUtil.toTitleCase(text);
    }
    log.info(String.format("The result text is [%s]", text));

    return new TextResult(text);
  }

  @Override
  public boolean isAcceptableInContext(TemplateContextType context) {
    return (context instanceof MarkdownContext);
  }

}
