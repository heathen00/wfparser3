package com.ht.wfp3.api.statement;

import com.ht.wfp3.api.statement.VertexReference.Type;

class VertexReferenceGroupBuilderImp implements VertexReferenceGroupBuilder {
  private final StatementFactory statementFactory;
  private Integer geometricVertexReferenceNumber;
  private Integer textureVertexReferenceNumber;
  private Integer normalVertexReferenceNumber;

  VertexReferenceGroupBuilderImp() {
    super();
    statementFactory = StatementFactory.createStatementFactory();
    clear();
  }

  @Override
  public VertexReferenceGroupBuilder clear() {
    geometricVertexReferenceNumber = null;
    textureVertexReferenceNumber = null;
    normalVertexReferenceNumber = null;
    return this;
  }

  @Override
  public VertexReferenceGroupBuilder geoVertexRef(Integer geometricVertexReferenceNumber) {
    this.geometricVertexReferenceNumber = geometricVertexReferenceNumber;
    return this;
  }

  @Override
  public VertexReferenceGroupBuilder texVertexRef(Integer textureVertexReferenceNumber) {
    this.textureVertexReferenceNumber = textureVertexReferenceNumber;
    return this;
  }

  @Override
  public VertexReferenceGroupBuilder normalVertexRef(Integer normalVertexReferenceNumber) {
    this.normalVertexReferenceNumber = normalVertexReferenceNumber;
    return this;
  }

  @Override
  public VertexReferenceGroup build() {
    VertexReference geometricVertexReference = (geometricVertexReferenceNumber != null
        ? statementFactory.createVertexReference(Type.GEOMETRIC, geometricVertexReferenceNumber)
        : statementFactory.createVertexReference(Type.GEOMETRIC,
            VertexReferenceImp.INDEX_NOT_SET_VALUE));
    VertexReference textureVertexReference = (textureVertexReferenceNumber != null
        ? statementFactory.createVertexReference(Type.TEXTURE, textureVertexReferenceNumber)
        : statementFactory.createVertexReference(Type.TEXTURE,
            VertexReferenceImp.INDEX_NOT_SET_VALUE));
    VertexReference normalVertexReference = (normalVertexReferenceNumber != null
        ? statementFactory.createVertexReference(Type.NORMAL, normalVertexReferenceNumber)
        : statementFactory.createVertexReference(Type.NORMAL,
            VertexReferenceImp.INDEX_NOT_SET_VALUE));
    return new VertexReferenceGroupImp(geometricVertexReference, textureVertexReference,
        normalVertexReference);
  }

}
