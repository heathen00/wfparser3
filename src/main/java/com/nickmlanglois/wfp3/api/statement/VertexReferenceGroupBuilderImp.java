package com.nickmlanglois.wfp3.api.statement;

class VertexReferenceGroupBuilderImp implements VertexReferenceGroupBuilder {
  private final StatementFactory statementFactory;
  private Integer geometricVertexReferenceNumber;
  private Integer textureVertexReferenceNumber;
  private Integer normalVertexReferenceNumber;
  private boolean isGeoRefSet;
  private boolean isTexRefSet;
  private boolean isNormalRefSet;

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
    isGeoRefSet = false;
    isTexRefSet = false;
    isNormalRefSet = false;
    return this;
  }

  @Override
  public VertexReferenceGroupBuilder geoVertexRef(Integer geometricVertexReferenceNumber) {
    if (null == geometricVertexReferenceNumber) {
      throw new NullPointerException("geometricVertexReferenceNumber cannot be null");
    }
    this.geometricVertexReferenceNumber = geometricVertexReferenceNumber;
    isGeoRefSet = true;
    return this;
  }

  @Override
  public VertexReferenceGroupBuilder texVertexRef(Integer textureVertexReferenceNumber) {
    if (null == textureVertexReferenceNumber) {
      throw new NullPointerException("textureVertexReferenceNumber cannot be null");
    }
    this.textureVertexReferenceNumber = textureVertexReferenceNumber;
    isTexRefSet = true;
    return this;
  }

  @Override
  public VertexReferenceGroupBuilder normalVertexRef(Integer normalVertexReferenceNumber) {
    if (null == normalVertexReferenceNumber) {
      throw new NullPointerException("normalVertexReferenceNumber cannot be null");
    }
    this.normalVertexReferenceNumber = normalVertexReferenceNumber;
    isNormalRefSet = true;
    return this;
  }

  @Override
  public VertexReferenceGroup build() {
    if (!isGeoRefSet) {
      throw new UnsupportedOperationException("geometricVertexReferenceNumber must be set");
    }
    GeoVertexReference geometricVertexReference =
        (isGeoRefSet ? statementFactory.createGeoVertexReference(geometricVertexReferenceNumber)
            : statementFactory.createGeoVertexReference(VertexReferenceImp.INDEX_NOT_SET_VALUE));
    TexVertexReference textureVertexReference =
        (isTexRefSet ? statementFactory.createTexVertexReference(textureVertexReferenceNumber)
            : statementFactory.createDisabledTexVertexReference());
    NormalVertexReference normalVertexReference =
        (isNormalRefSet ? statementFactory.createNormalVertexReference(normalVertexReferenceNumber)
            : statementFactory.createDisabledNormalVertexReference());
    return new VertexReferenceGroupImp(geometricVertexReference, textureVertexReference,
        normalVertexReference);
  }
}
