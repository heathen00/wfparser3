package com.ht.wfp3.api.statement;

abstract class StatementImp implements Statement {
  private final String keyword;

  StatementImp(String keyword) {
    super();
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
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    StatementImp other = (StatementImp) obj;
    if (keyword == null) {
      if (other.keyword != null)
        return false;
    } else if (!keyword.equals(other.keyword))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "StatementImp [keyword=" + keyword + "]";
  }

  @Override
  public int compareTo(Statement o) {
    return keyword.compareTo(o.getKeyword());
  }
}
