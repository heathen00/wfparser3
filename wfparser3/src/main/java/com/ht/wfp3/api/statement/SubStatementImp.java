package com.ht.wfp3.api.statement;

abstract class SubStatementImp implements SubStatement {
  private final String keyword;

  public SubStatementImp(String keyword) {
    this.keyword = keyword;
  }

  @Override
  public String getKeyword() {
    return keyword;
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((keyword == null) ? 0 : keyword.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (obj == null) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    SubStatementImp other = (SubStatementImp) obj;
    if (keyword == null) {
      if (other.keyword != null) {
        return false;
      }
    } else if (!keyword.equals(other.keyword)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "SubStatementImp [keyword=" + keyword + "]";
  }
}
