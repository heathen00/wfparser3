package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class StatementsUsingVertexReferenceGroupsImp extends StatementImp implements UsesVertexReferenceGroups {
  private final StatementFactory statementFactory;
  private final List<VertexReferenceGroup> vertexReferenceGroupList;

  StatementsUsingVertexReferenceGroupsImp(String keyword, List<VertexReferenceGroup> vertexReferenceGroupList) {
    super(keyword);
    statementFactory = StatementFactory.createStatementFactory();
    this.vertexReferenceGroupList = new ArrayList<>();
    copyVertexReferenceGroupsInConstructor(vertexReferenceGroupList);
  }
  
  @Override
  public List<VertexReferenceGroup> getVertexReferenceGroupList() {
    return Collections.unmodifiableList(vertexReferenceGroupList);
  }

  private void copyVertexReferenceGroupsInConstructor(List<VertexReferenceGroup> referenceNumbers) {
    for (VertexReferenceGroup vertexReferenceGroup : referenceNumbers) {
      vertexReferenceGroupList.add(statementFactory.copyVertexReferenceGroup(vertexReferenceGroup));
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = super.hashCode();
    result = prime * result
        + ((vertexReferenceGroupList == null) ? 0 : vertexReferenceGroupList.hashCode());
    return result;
  }

  @Override
  public boolean equals(Object obj) {
    if (this == obj) {
      return true;
    }
    if (!super.equals(obj)) {
      return false;
    }
    if (getClass() != obj.getClass()) {
      return false;
    }
    StatementsUsingVertexReferenceGroupsImp other = (StatementsUsingVertexReferenceGroupsImp) obj;
    if (vertexReferenceGroupList == null) {
      if (other.vertexReferenceGroupList != null) {
        return false;
      }
    } else if (!vertexReferenceGroupList.equals(other.vertexReferenceGroupList)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "StatementsUsingVertexReferencesImp [vertexReferenceGroupList="
        + vertexReferenceGroupList + ", super.toString()=" + super.toString() + "]";
  }
}
