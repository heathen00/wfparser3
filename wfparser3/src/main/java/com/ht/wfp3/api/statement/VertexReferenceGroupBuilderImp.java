package com.ht.wfp3.api.statement;

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
    GeoVertexReference geometricVertexReference = (geometricVertexReferenceNumber != null
        ? statementFactory.createGeoVertexReference(geometricVertexReferenceNumber)
        : statementFactory.createGeoVertexReference(VertexReferenceImp.INDEX_NOT_SET_VALUE));
    TexVertexReference textureVertexReference = (textureVertexReferenceNumber != null
        ? statementFactory.createTexVertexReference(textureVertexReferenceNumber)
        : statementFactory.createTexVertexReference(VertexReferenceImp.INDEX_NOT_SET_VALUE));
    NormalVertexReference normalVertexReference = (normalVertexReferenceNumber != null
        ? statementFactory.createNormalVertexReference(normalVertexReferenceNumber)
        : statementFactory.createNormalVertexReference(VertexReferenceImp.INDEX_NOT_SET_VALUE));
    return new VertexReferenceGroupImp(geometricVertexReference, textureVertexReference,
        normalVertexReference);
  }
}
