package com.ht.wfp3.api.statement;

import java.nio.file.Path;

class ShadowObjectImp extends StatementImp implements ShadowObject {
  private static final String KEYWORD = "shadow_obj";

  private final Path shadowObjectFileName;

  ShadowObjectImp(Path shadowObjectFileName) {
    super(KEYWORD);
    this.shadowObjectFileName = shadowObjectFileName;
  }

  ShadowObjectImp(ShadowObject shadow_obj) {
    this(shadow_obj.getShadowObjectFileName());
  }

  @Override
  public Path getShadowObjectFileName() {
    return shadowObjectFileName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result =
        prime * result + ((shadowObjectFileName == null) ? 0 : shadowObjectFileName.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj)
      return true;
    if (!super.equals(obj))
      return false;
    if (getClass() != obj.getClass())
      return false;
    ShadowObjectImp other = (ShadowObjectImp) obj;
    if (shadowObjectFileName == null) {
      if (other.shadowObjectFileName != null)
        return false;
    } else if (!shadowObjectFileName.equals(other.shadowObjectFileName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ShadowObjectImp [shadowObjectFileName=" + shadowObjectFileName + ", super.toString()="
        + super.toString() + "]";
  }
}
