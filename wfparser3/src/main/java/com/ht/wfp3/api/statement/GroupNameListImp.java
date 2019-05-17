package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class GroupNameListImp extends StatementImp implements GroupNameList {
  private static final String KEYWORD = "g";

  private final List<String> groupNameList;

  GroupNameListImp(List<String> groupNameList) {
    super(KEYWORD);
    if (null == groupNameList) {
      throw new NullPointerException("groupNameList constructor parameter cannot be null");
    }
    if (groupNameList.contains(null)) {
      throw new IllegalArgumentException(
          "groupNameList constructor parameter cannot contain null members");
    }
    if (MINIMUM_GROUP_NAMES.compareTo(groupNameList.size()) > 0) {
      throw new IllegalArgumentException(
          "groupNameList constructor parameter must contain at least one group name");
    }
    this.groupNameList = new ArrayList<>(groupNameList);
  }

  GroupNameListImp(GroupNameList g) {
    this(g.getGroupNameList());
  }

  @Override
  public List<String> getGroupNameList() {
    return Collections.unmodifiableList(groupNameList);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result + ((groupNameList == null) ? 0 : groupNameList.hashCode());
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
    GroupNameListImp other = (GroupNameListImp) obj;
    if (groupNameList == null) {
      if (other.groupNameList != null)
        return false;
    } else if (!groupNameList.equals(other.groupNameList))
      return false;
    return true;
  }

  @Override
  public String toString() {
    return "GroupNameListImp [groupNameList=" + groupNameList + ", super.toString()="
        + super.toString() + "]";
  }
}
