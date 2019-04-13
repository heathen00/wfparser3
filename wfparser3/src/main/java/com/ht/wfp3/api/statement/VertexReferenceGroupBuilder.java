package com.ht.wfp3.api.statement;

public interface VertexReferenceGroupBuilder {

  VertexReferenceGroupBuilder clear();

  VertexReferenceGroupBuilder geoVertexRef(Integer geometricVertexReferenceNumber);

  VertexReferenceGroupBuilder texVertexRef(Integer textureVertexReferenceNumber);

  VertexReferenceGroupBuilder normalVertexRef(Integer normalVertexReferenceNumber);

  VertexReferenceGroupBuilder paramVertexRef(Integer parameterVertexReferenceNumber);

  VertexReferenceGroup build();
}
