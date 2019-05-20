package com.ht.wfp3.api.statement;

class LevelOfDetailImp extends StatementImp implements LevelOfDetail {
  private static final String KEYWORD = "lod";

  private final Integer levelOfDetail;

  LevelOfDetailImp(Integer levelOfDetail) {
    super(KEYWORD);
    if (null == levelOfDetail) {
      throw new NullPointerException("levelOfDetail constructor parameter cannot be null");
    }
    if (OFF.compareTo(levelOfDetail) > 0) {
      throw new IllegalArgumentException(
          "levelOfDetail constructor parameter cannot be less than " + OFF);
    }
    if (MAX.compareTo(levelOfDetail) < 0) {
      throw new IllegalArgumentException(
          "levelOfDetail constructor parameter cannt be greater than " + MAX);
    }
    this.levelOfDetail = levelOfDetail;
  }

  LevelOfDetailImp(LevelOfDetail lod) {
    this(lod.getLevelOfDetail());
  }

  @Override
  public Integer getLevelOfDetail() {
    return levelOfDetail;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((levelOfDetail == null) ? 0 : levelOfDetail.hashCode());
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
    LevelOfDetailImp other = (LevelOfDetailImp) obj;
    if (levelOfDetail == null) {
      if (other.levelOfDetail != null)
        return false;
    } else if (!levelOfDetail.equals(other.levelOfDetail))
      return false;
    return true;
  }

  @Override
  public int compareTo(Statement o) {
    int compareTo = super.compareTo(o);
    if (0 == compareTo) {
      LevelOfDetail levelOfDetailParameter = (LevelOfDetail) o;
      compareTo = levelOfDetail.compareTo(levelOfDetailParameter.getLevelOfDetail());
    }
    return compareTo;
  }

  @Override
  public String toString() {
    return "LevelOfDetailImp [levelOfDetail=" + levelOfDetail + ", super.toString()="
        + super.toString() + "]";
  }
}
