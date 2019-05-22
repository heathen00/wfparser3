package com.ht.wfp3.api.statement;

class UseMapImp extends StatementImp implements UseMap {
  private static final String KEYWORD = "usemap";
  private static final String OFF = "off";

  private final boolean isEnabled;
  private final String mapName;

  UseMapImp(String mapNameOrOff) {
    super(KEYWORD);
    if (null == mapNameOrOff) {
      throw new NullPointerException("mapNameOrOff constructor parameter cannot be null");
    }
    if (!mapNameOrOff.matches("^\\w+$")) {
      throw new IllegalArgumentException(
          "mapNameOrOff constructor parameter may only contain one or more alphanumeric or underscore characters");
    }
    this.isEnabled = (mapNameOrOff.equalsIgnoreCase(OFF) ? false : true);
    this.mapName = (mapNameOrOff.equalsIgnoreCase(OFF) ? OFF : mapNameOrOff);
  }

  UseMapImp(UseMap usemap) {
    this(usemap.getMapName());
  }

  @Override
  public boolean isEnabled() {
    return isEnabled;
  }

  @Override
  public String getMapName() {
    return mapName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + (isEnabled ? 1231 : 1237);
    result = prime * result + ((mapName == null) ? 0 : mapName.hashCode());
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
    UseMapImp other = (UseMapImp) obj;
    if (isEnabled != other.isEnabled)
      return false;
    if (mapName == null) {
      if (other.mapName != null)
        return false;
    } else if (!mapName.equals(other.mapName))
      return false;
    return true;
  }

  @Override
  public int compareTo(UseMap o) {
    return mapName.compareTo(o.getMapName());
  }

  @Override
  public String toString() {
    return "UseMapImp [isEnabled=" + isEnabled + ", mapName=" + mapName + ", super.toString()="
        + super.toString() + "]";
  }
}
