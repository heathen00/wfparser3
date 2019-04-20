package com.ht.wfp3.api.statement;

class UseMaterialImp extends StatementImp implements UseMaterial {
  private static final String KEYWORD = "usemtl";

  private final String materialName;

  UseMaterialImp(String materialName) {
    super(KEYWORD);
    this.materialName = materialName;
  }

  UseMaterialImp(UseMaterial usemtl) {
    this(usemtl.getMaterialName());
  }

  @Override
  public String getMaterialName() {
    return materialName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((materialName == null) ? 0 : materialName.hashCode());
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
    UseMaterialImp other = (UseMaterialImp) obj;
    if (materialName == null) {
      if (other.materialName != null)
        return false;
    } else if (!materialName.equals(other.materialName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "UseMaterialImp [materialName=" + materialName + ", super.toString()=" + super.toString()
        + "]";
  }
}
