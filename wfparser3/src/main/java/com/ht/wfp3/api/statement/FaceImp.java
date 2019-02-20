package com.ht.wfp3.api.statement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class FaceImp implements Face {
  private StatementFactory statementFactory;
  private List<VertexReferenceGroup> vertexReferenceGroupList;

  FaceImp() {
    statementFactory = StatementFactory.createStatementFactory();
    vertexReferenceGroupList = new ArrayList<>();
  }

  FaceImp(Face face) {
    this();
    for (VertexReferenceGroup vertexReferenceGroup : face.getReferenceNumbers()) {
      appendReferenceNumbers(vertexReferenceGroup);
    }
  }

  @Override
  public boolean canComment() {
    // TODO Auto-generated method stub
    return false;
  }

  @Override
  public String getKeyword() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void setComment(Comment comment) {
    // TODO Auto-generated method stub

  }

  @Override
  public String getComment() {
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public void appendReferenceNumbers(VertexReferenceGroup referenceNumbers) {
    vertexReferenceGroupList.add(statementFactory.copyVertexReferenceGroup(referenceNumbers));
  }

  @Override
  public List<VertexReferenceGroup> getReferenceNumbers() {
    return Collections.unmodifiableList(vertexReferenceGroupList);
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
    FaceImp other = (FaceImp) obj;
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
    return "FaceImp [vertexReferenceGroupList=" + vertexReferenceGroupList + "]";
  }
}
