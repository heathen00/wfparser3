package com.ht.wfp3.api.statement;

import com.ht.wfp3.api.statement.VertexReference.Type;

import java.util.HashMap;
import java.util.Map;

class VertexReferenceGroupImp implements VertexReferenceGroup {
  private StatementFactory statementFactory;
  private Map<VertexReference.Type, VertexReference> vertexReferenceMap;
  
  VertexReferenceGroupImp() {
    statementFactory = StatementFactory.createStatementFactory();
    vertexReferenceMap = new HashMap<>();
  }
  
  VertexReferenceGroupImp(VertexReferenceGroup vertexReferenceGroup) {
    this();
    for (VertexReference vertexReference : vertexReferenceMap.values()) {
      addVertexReference(vertexReference);
    }
  }

  @Override
  public void addVertexReference(VertexReference vertexReference) {
    vertexReferenceMap.put(vertexReference.getVertex(), statementFactory.copyVertexReference(vertexReference));
  }

  @Override
  public VertexReference getVertexReference(Type vertexReferenceType) {
    return vertexReferenceMap.get(vertexReferenceType);
  }

  @Override
  public int hashCode() {
    final int prime = 31;
    int result = 1;
    result = prime * result + ((vertexReferenceMap == null) ? 0 : vertexReferenceMap.hashCode());
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
    VertexReferenceGroupImp other = (VertexReferenceGroupImp) obj;
    if (vertexReferenceMap == null) {
      if (other.vertexReferenceMap != null) {
        return false;
      }
    } else if (!vertexReferenceMap.equals(other.vertexReferenceMap)) {
      return false;
    }
    return true;
  }

  @Override
  public String toString() {
    return "VertexReferenceGroupImp [vertexReferenceMap=" + vertexReferenceMap + "]";
  }
}
