package com.nickmlanglois.wfp3.api.statement;

public interface VertexReferenceGroupBuilder {

  VertexReferenceGroupBuilder clear();

  VertexReferenceGroupBuilder geoVertexRef(Integer geometricVertexReferenceNumber);

  VertexReferenceGroupBuilder texVertexRef(Integer textureVertexReferenceNumber);

  VertexReferenceGroupBuilder normalVertexRef(Integer normalVertexReferenceNumber);

  VertexReferenceGroup build();
}
