package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

abstract class StatementsUsingVertexReferencesImp {
  private StatementFactory statementFactory;
  private List<VertexReferenceGroup> vertexReferenceGroupList;

  public StatementsUsingVertexReferencesImp() {
    statementFactory = StatementFactory.createStatementFactory();
    vertexReferenceGroupList = new ArrayList<>();
  }

  public void appendReferenceNumbers(VertexReferenceGroup referenceNumbers) {
    vertexReferenceGroupList.add(statementFactory.copyVertexReferenceGroup(referenceNumbers));
  }

  public List<VertexReferenceGroup> getReferenceNumbers() {
    return Collections.unmodifiableList(vertexReferenceGroupList);
  }

  final void copyVertexReferenceGroupsInConstructor(List<VertexReferenceGroup> referenceNumbers) {
    for (VertexReferenceGroup vertexReferenceGroup : referenceNumbers) {
      vertexReferenceGroupList.add(statementFactory.copyVertexReferenceGroup(vertexReferenceGroup));
    }
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result
        + ((vertexReferenceGroupList == null) ? 0 : vertexReferenceGroupList.hashCode());
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
    StatementsUsingVertexReferencesImp other = (StatementsUsingVertexReferencesImp) obj;
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
    return "CommonUsesVertexReferencesImp [vertexReferenceGroupList=" + vertexReferenceGroupList
        + "]";
  }
}