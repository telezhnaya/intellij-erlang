// This is a generated file. Not intended for manual editing.
package org.intellij.erlang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.intellij.erlang.ErlangTypes.*;
import org.intellij.erlang.psi.*;

public class ErlangAtomAttributeImpl extends ErlangCompositeElementImpl implements ErlangAtomAttribute {

  public ErlangAtomAttributeImpl(ASTNode node) {
    super(node);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ErlangVisitor) ((ErlangVisitor)visitor).visitAtomAttribute(this);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ErlangAttrVal getAttrVal() {
    return findChildByClass(ErlangAttrVal.class);
  }

  @Override
  @Nullable
  public ErlangTypedAttrVal getTypedAttrVal() {
    return findChildByClass(ErlangTypedAttrVal.class);
  }

  @Override
  @Nullable
  public PsiElement getParLeft() {
    return findChildByType(ERL_PAR_LEFT);
  }

  @Override
  @Nullable
  public PsiElement getParRight() {
    return findChildByType(ERL_PAR_RIGHT);
  }

  @Override
  @NotNull
  public PsiElement getAtomName() {
    return findNotNullChildByType(ERL_ATOM_NAME);
  }

  @NotNull
  public String getName() {
    return ErlangPsiImplUtil.getName(this);
  }

}
