package com.nickmlanglois.wfp3.api.statement;

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
      throw new NullPointerException("materialLibFileNameList cannot be null");
    }
    if (MINIMUM_FILE_NAMES.compareTo(materialLibFileNameList.size()) > 0) {
      throw new IllegalArgumentException(
          "materialLibFileNameList must contain at least one material lib file name");
    }
    if (materialLibFileNameList.contains(null)) {
      throw new IllegalArgumentException("materialLibFileNameList cannot contain null members");
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
  public int compareTo(MaterialLib o) {
    ListOfComparableComparator<Path> listComparator = new ListOfComparableComparator<>();
    return listComparator.compare(materialLibFileNameList, o.getMaterialLibFileNameList());
  }

  @Override
  public String toString() {
    return "MaterialLibImp [materialLibFileNameList=" + materialLibFileNameList
        + ", super.toString()=" + super.toString() + "]";
  }
}
