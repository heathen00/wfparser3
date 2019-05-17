package com.ht.wfp3.api.statement;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class MapLibImp extends StatementImp implements MapLib {
  private static final String KEYWORD = "maplib";

  private final List<Path> mapLibFileNameList;

  MapLibImp(List<Path> mapLibFileNameList) {
    super(KEYWORD);
    if (null == mapLibFileNameList) {
      throw new NullPointerException("mapLibFileNameList constructor parameter cannot be null");
    }
    if (MINIMUM_FILE_NAMES.compareTo(mapLibFileNameList.size()) > 0) {
      throw new IllegalArgumentException(
          "mapLibFileNameList constructor parameter must contain at least one map lib file name");
    }
    if (mapLibFileNameList.contains(null)) {
      throw new IllegalArgumentException(
          "mapLibFileNameList constructor parameter cannot contain null members");
    }
    this.mapLibFileNameList = new ArrayList<>(mapLibFileNameList);
  }

  MapLibImp(MapLib maplib) {
    this(maplib.getMapLibFileNameList());
  }

  @Override
  public List<Path> getMapLibFileNameList() {
    return Collections.unmodifiableList(mapLibFileNameList);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((mapLibFileNameList == null) ? 0 : mapLibFileNameList.hashCode());
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
    MapLibImp other = (MapLibImp) obj;
    if (mapLibFileNameList == null) {
      if (other.mapLibFileNameList != null)
        return false;
    } else if (!mapLibFileNameList.equals(other.mapLibFileNameList))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "MapLibImp [mapLibFileNameList=" + mapLibFileNameList + ", super.toString()="
        + super.toString() + "]";
  }
}
