/*
 * Copyright 2012 Sergey Ignatov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.intellij.erlang.quickfixes;

import com.intellij.codeInspection.ProblemDescriptor;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.intellij.erlang.psi.*;
import org.intellij.erlang.psi.impl.ErlangElementFactory;
import org.jetbrains.annotations.NotNull;

/**
 * @author ignatov
 */
public class ErlangIntroduceRecordFix extends ErlangQuickFixBase {
  @NotNull
  @Override
  public String getFamilyName() {
    return "Introduce new record";
  }

  @Override
  public void applyFix(@NotNull Project project, @NotNull ProblemDescriptor descriptor) {
    PsiElement element = descriptor.getPsiElement();

    if (element instanceof ErlangRecordRef) {
      PsiElement record = ErlangElementFactory.createRecordFromText(project, element.getText());
      PsiFile file = element.getContainingFile();
      if (file instanceof ErlangFile) {
        ErlangCompositeElement elementBefore = getAnchorElement((ErlangFile) file);

        if (elementBefore != null) {
          file.addBefore(record, elementBefore);
          file.addBefore(ErlangElementFactory.createLeafFromText(project, "."), elementBefore);
          String newLines = elementBefore instanceof ErlangRecordDefinition ? "\n" : "\n\n";
          file.addBefore(ErlangElementFactory.createLeafFromText(project, newLines), elementBefore);
        }
      }
    }
  }
}
