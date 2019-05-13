package com.ht.wfp3.api.statement;

class ObjectNameImp extends StatementImp implements ObjectName {
  private static final String KEYWORD = "o";

  private final String objectName;

  ObjectNameImp(String objectName) {
    super(KEYWORD);
    if (null == objectName) {
      throw new NullPointerException("objectName constructor parameter cannot be null");
    }
    if (!objectName.matches("^[\\d\\w]+$")) {
      throw new IllegalArgumentException(
          "objectName constructor parameter can only contain upper/lower case characters, numbers, and underscore");
    }
    this.objectName = objectName;
  }

  ObjectNameImp(ObjectName o) {
    this(o.getObjectName());
  }

  @Override
  public String getObjectName() {
    return objectName;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((objectName == null) ? 0 : objectName.hashCode());
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
    ObjectNameImp other = (ObjectNameImp) obj;
    if (objectName == null) {
      if (other.objectName != null)
        return false;
    } else if (!objectName.equals(other.objectName))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "ObjectNameImp [objectName=" + objectName + ", super.toString()=" + super.toString()
        + "]";
  }
}
