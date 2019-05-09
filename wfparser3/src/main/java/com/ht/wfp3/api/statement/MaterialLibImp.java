package com.ht.wfp3.api.statement;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MaterialLibImp extends StatementImp implements MaterialLib {
  private static final String KEYWORD = "mtllib";

  private final List<Path> materialLibFileNameList;

  MaterialLibImp(List<Path> materialLibFileNameList) {
    super(KEYWORD);
    if (null == materialLibFileNameList) {
      throw new NullPointerException(
          "materialLibFileNameList constructor parameter cannot be null");
    }
    if (materialLibFileNameList.isEmpty()) {
      throw new IllegalArgumentException(
          "materialLibFileNameList constructor paramter must contain at least one material lib file name");
    }
    this.materialLibFileNameList = new ArrayList<>(materialLibFileNameList);
  }

  MaterialLibImp(MaterialLib mtllib) {
    this(mtllib.getMaterialLibFileNameList());
  }

  @Override
  public List<Path> getMaterialLibFileNameList() {
    return Collections.unmodifiableList(materialLibFileNameList);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((materialLibFileNameList == null) ? 0 : materialLibFileNameList.hashCode());
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
    MaterialLibImp other = (MaterialLibImp) obj;
    if (materialLibFileNameList == null) {
      if (other.materialLibFileNameList != null)
        return false;
    } else if (!materialLibFileNameList.equals(other.materialLibFileNameList))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MaterialLibImp [materialLibFileNameList=" + materialLibFileNameList
        + ", super.toString()=" + super.toString() + "]";
  }
}
