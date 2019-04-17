package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;
import com.ht.wfp3.api.statement.BasisMatrix.Axis;

/**
 * The OBJ element factory.
 * 
 * The purpose of this factory is to hide the details of how the elements are created.
 * 
 * @author nickl
 *
 */
public final class StatementFactory {
  private static final StatementFactory STATEMENT_FACTORY_SINGLETON = new StatementFactory();

  public static StatementFactory createStatementFactory() {
    return STATEMENT_FACTORY_SINGLETON;
  }

  public GeoVertex createGeoVertex(BigDecimal xCoord, BigDecimal yCoord, BigDecimal zCoord,
      BigDecimal wCoord) {
    return new GeoVertexImp(xCoord, yCoord, zCoord, wCoord);
  }

  public GeoVertex copyGeoVertex(GeoVertex geoVertex) {
    return new GeoVertexImp(geoVertex);
  }

  public TexVertex createTexVertex(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord) {
    return new TexVertexImp(uCoord, vCoord, wCoord);
  }

  public TexVertex copyTexVertex(TexVertex texVertex) {
    return new TexVertexImp(texVertex);
  }

  public NormalVertex createNormalVertex(BigDecimal iCoord, BigDecimal jCoord, BigDecimal kCoord) {
    return new NormalVertexImp(iCoord, jCoord, kCoord);
  }

  public NormalVertex copyNormalVertex(NormalVertex normalVertex) {
    return new NormalVertexImp(normalVertex);
  }

  public ParamVertex createParamVertex(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord) {
    return new ParamVertexImp(uCoord, vCoord, wCoord);
  }

  public ParamVertex copyParamVertex(ParamVertex paramVertex) {
    return new ParamVertexImp(paramVertex);
  }

  public VertexReferenceGroupBuilder createVertexReferenceGroupBuilder() {
    return new VertexReferenceGroupBuilderImp();
  }

  public VertexReferenceGroup copyVertexReferenceGroup(VertexReferenceGroup vertexReferenceGroup) {
    return new VertexReferenceGroupImp((VertexReferenceGroupImp) vertexReferenceGroup);
  }

  // Not published.
  VertexReferenceImp createVertexReference(VertexReferenceImp.Type type, Integer vertexIndex,
      boolean isSet) {
    return new VertexReferenceImp(type, vertexIndex, isSet);
  }

  // Not published.
  VertexReferenceImp copyVertexReference(VertexReferenceImp vertexReference) {
    return new VertexReferenceImp(vertexReference);
  }

  public Point createPoint(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new PointImp();
  }

  public Point copyPoint(Point point) {
    return new PointImp(point);
  }

  public Line createLine(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new LineImp();
  }

  public Line copyLine(Line line) {
    return new LineImp(line);
  }

  public Face createFace(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new FaceImp();
  }

  public Face copyFace(Face face) {
    return new FaceImp(face);
  }

  public CurveOrSurfaceType createCurveOrSurface(boolean isRational,
      CurveOrSurfaceType.Key typeKey) {
    return new CurveOrSurfaceTypeImp(isRational, typeKey);
  }

  public Statement copyCurveOrSurfaceType(CurveOrSurfaceType cstype) {
    return new CurveOrSurfaceTypeImp(cstype);
  }

  public Degree createDegree(Integer uAxisDegree, Integer vAxisDegree) {
    // TODO Auto-generated method stub

    return null;
  }

  public MatrixBuilder createMatrixBuilder() {
    // TODO Auto-generated method stub
    return null;
  }

  public BasisMatrix createBasisMatrix(Axis axis, Matrix matrix) {
    // TODO Auto-generated method stub

    return null;
  }

  public StepSize createStepSize(Integer stepSizeInUAxis, Integer stepSizeInVAxis) {
    // TODO Auto-generated method stub

    return null;
  }

  public Curve createCurve(BigDecimal startingParameterValue, BigDecimal endingParameterValue,
      List<VertexReferenceGroup> vertexReferenceGroupList) {
    // TODO Auto-generated method stub

    return null;
  }

  public Curve2D createCurve2D(List<VertexReferenceGroup> vertexReferenceGroupList) {
    // TODO Auto-generated method stub

    return null;
  }

  public Surface createSurface(BigDecimal startingParameterValueUAxis,
      BigDecimal endingParameterValueUAxis, BigDecimal startingParameterValueVAxis,
      BigDecimal endingParameterValueVAxis, List<VertexReferenceGroup> vertexReferenceGroupList) {
    // TODO Auto-generated method stub

    return null;
  }

  public Call createCall(Path fileName, boolean isFrameNumberRequired, List<Integer> arguments) {
    // TODO Auto-generated method stub

    return null;
  }

  public Csh createCsh(String ignoreError, String command) {
    // TODO Auto-generated method stub

    return null;
  }

  public Parm createParm(Parm.Axis axis, List<BigDecimal> parameterList) {
    // TODO Auto-generated method stub

    return null;
  }

  public Trim createTrim(List<Curve2DReference> curve2DReferenceList) {
    // TODO Auto-generated method stub

    return null;
  }

  public Curve2DReference createCurve2DReference(BigDecimal startingParameterValue,
      BigDecimal endingParameterValue, Integer curve2DIndex) {
    // TODO Auto-generated method stub

    return null;
  }

  public Hole createHole(List<Curve2DReference> curve2DReferenceList) {
    // TODO Auto-generated method stub

    return null;
  }

  public SpecialCurve createSpecialCurve(List<Curve2DReference> curve2DReferenceList) {
    // TODO Auto-generated method stub

    return null;
  }

  public SpecialPoint createSpecialPoint(List<VertexReferenceGroup> vertexReferenceGroupList) {
    // TODO Auto-generated method stub
    return null;
  }

  public End createEnd() {
    // TODO Auto-generated method stub

    return null;
  }

  public Connect createConnect(Integer firstSurfaceIndex,
      Curve2DReference curve2dReferenceForFirstSurface, Integer secondSurfaceIndex,
      Curve2DReference curve2dReferenceForSecondSurface) {
    // TODO Auto-generated method stub

    return null;
  }

  public GroupNameList createGroupName(List<String> groupNameList) {
    // TODO Auto-generated method stub

    return null;
  }

  public SmoothingGroup createSmoothingGroup(Integer groupNumberOrOff) {
    // TODO Auto-generated method stub

    return null;
  }

  public MergingGroup createMergingGroup(Integer groupNumberOrOff, BigDecimal resolution) {
    // TODO Auto-generated method stub

    return null;
  }

  public ObjectName createObjectName(String objectName) {
    // TODO Auto-generated method stub

    return null;
  }

  public Bevel createBevel(boolean isEnabled) {
    // TODO Auto-generated method stub

    return null;
  }

  public ColorInterpolation createColorInterpolation(boolean isEnabled) {
    // TODO Auto-generated method stub

    return null;
  }

  public DissolveInterpolation createDissolveInterpolation(boolean isEnabled) {
    // TODO Auto-generated method stub

    return null;
  }

  public LevelOfDetail createLevelOfDetail(Integer levelOfDetail) {
    // TODO Auto-generated method stub

    return null;
  }

  public MapLib createMapLib(List<Path> mapLibFileNameList) {
    // TODO Auto-generated method stub

    return null;
  }

  public UseMaterial createUseMaterial(String materialName) {
    // TODO Auto-generated method stub

    return null;
  }

  public UseMap createUseMap(String mapNameOrOff) {
    // TODO Auto-generated method stub

    return null;
  }

  public MaterialLib createMaterialLib(List<Path> materialLibFileNameList) {
    // TODO Auto-generated method stub

    return null;
  }

  public ShadowObject createShadowObject(Path shadowObjectFileName) {
    // TODO Auto-generated method stub

    return null;
  }

  public RayTracingObject createRayTracingObject(Path rayTracingObjectFileName) {
    // TODO Auto-generated method stub

    return null;
  }

  public CurveApproxCparmTechnique createCparmCurveApprox(BigDecimal resolution) {
    // TODO Auto-generated method stub

    return null;
  }

  public CurveApproxCspaceTechnique createCspaceCurveApprox(BigDecimal maxLength) {
    // TODO Auto-generated method stub

    return null;
  }

  public CurveApproxCurvTechnique createCurvCurveAprox(BigDecimal maxDist, BigDecimal maxAngleInDegrees) {
    // TODO Auto-generated method stub

    return null;
  }

  public SurfaceApproxCparmaTechnique createCparmaSurfaceApprox(BigDecimal resolutionForUAxis,
      BigDecimal resolutionForVAxis) {
    // TODO Auto-generated method stub

    return null;
  }

  public SurfaceApproxCparmbTechnique createCparmbSurfaceApprox(BigDecimal resolutionForUAndVAxes) {
    // TODO Auto-generated method stub

    return null;
  }

  public SurfaceApproxCspaceTechnique createCspaceSurfaceApprox(BigDecimal maxLength) {
    // TODO Auto-generated method stub

    return null;
  }

  public SurfaceApproxCurvTechnique createCurvSurfaceApprox(BigDecimal maxDist,
      BigDecimal maxAngleInDegrees) {
    // TODO Auto-generated method stub

    return null;
  }

  public Blank createBlank() {
    // TODO Auto-generated method stub

    return null;
  }

  public Unknown createUnknown(List<String> tokens) {
    // TODO Auto-generated method stub

    return null;
  }

  public boolean isSupportedStatement(Statement statement) {
    return statement instanceof StatementImp;
  }
}
