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
    return new PointImp(vertexReferenceGroupList);
  }

  public Point copyPoint(Point point) {
    return new PointImp(point);
  }

  public Line createLine(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new LineImp(vertexReferenceGroupList);
  }

  public Line copyLine(Line line) {
    return new LineImp(line);
  }

  public Face createFace(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new FaceImp(vertexReferenceGroupList);
  }

  public Face copyFace(Face face) {
    return new FaceImp(face);
  }

  public CurveOrSurfaceType createCurveOrSurface(boolean isRational,
      CurveOrSurfaceType.Key typeKey) {
    return new CurveOrSurfaceTypeImp(isRational, typeKey);
  }

  public CurveOrSurfaceType copyCurveOrSurfaceType(CurveOrSurfaceType cstype) {
    return new CurveOrSurfaceTypeImp(cstype);
  }

  public Degree createDegree(Integer uAxisDegree, Integer vAxisDegree) {
    return new DegreeImp(uAxisDegree, vAxisDegree);
  }


  public Degree copyDegree(Degree deg) {
    return new DegreeImp(deg);
  }

  public MatrixBuilder createMatrixBuilder() {
    return new MatrixBuilderImp();
  }

  public BasisMatrix createBasisMatrix(Axis axis, Matrix matrix) {
    return new BasisMatrixImp(axis, matrix);
  }

  public BasisMatrix copyBasisMatrix(BasisMatrix bmat) {
    return new BasisMatrixImp(bmat);
  }

  public StepSize createStepSize(Integer stepSizeInUAxis, Integer stepSizeInVAxis) {
    return new StepSizeImp(stepSizeInUAxis, stepSizeInVAxis);
  }

  public StepSize copyStepSize(StepSize step) {
    return new StepSizeImp(step);
  }

  public Curve createCurve(BigDecimal startingParameterValue, BigDecimal endingParameterValue,
      List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new CurveImp(startingParameterValue, endingParameterValue, vertexReferenceGroupList);
  }

  public Curve copyCurve(Curve curv) {
    return new CurveImp(curv);
  }

  public Curve2D createCurve2D(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new Curve2DImp(vertexReferenceGroupList);
  }

  public Curve2D copyCurve2D(Curve2D curv2) {
    return new Curve2DImp(curv2);
  }

  public Surface createSurface(BigDecimal startingParameterValueUAxis,
      BigDecimal endingParameterValueUAxis, BigDecimal startingParameterValueVAxis,
      BigDecimal endingParameterValueVAxis, List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new SurfaceImp(startingParameterValueUAxis, endingParameterValueUAxis,
        startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList);
  }

  public Surface copySurface(Surface surf) {
    return new SurfaceImp(surf);
  }

  public Call createCall(boolean isFrameNumberRequired, Path fileName, List<Integer> arguments) {
    return new CallImp(isFrameNumberRequired, fileName, arguments);
  }

  public Call copyCall(Call call) {
    return new CallImp(call);
  }

  public Csh createCsh(boolean shouldIgnoreError, String command) {
    return new CshImp(shouldIgnoreError, command);
  }

  public Csh copyCsh(Csh csh) {
    return new CshImp(csh);
  }

  public Parm createParm(Parm.Axis axis, List<BigDecimal> parameterList) {
    return new ParmImp(axis, parameterList);
  }

  public Parm copyParm(Parm parm) {
    return new ParmImp(parm);
  }

  public Curve2DReference createCurve2DReference(BigDecimal startingParameterValue,
      BigDecimal endingParameterValue, Integer curve2DIndex) {
    return new Curve2DReferenceImp(startingParameterValue, endingParameterValue, curve2DIndex);
  }

  public Curve2DReference copyCurve2DReference(Curve2DReference curve2DReference) {
    return new Curve2DReferenceImp(curve2DReference);
  }
  
  public Trim createTrim(List<Curve2DReference> curve2DReferenceList) {
    return new TrimImp(curve2DReferenceList);
  }
  
  public Trim copyTrim(Trim trim) {
    return new TrimImp(trim);
  }

  public Hole createHole(List<Curve2DReference> curve2DReferenceList) {
    return new HoleImp(curve2DReferenceList);
  }
  
  public Hole copyHole(Hole hole) {
    return new HoleImp(hole);
  }

  public SpecialCurve createSpecialCurve(List<Curve2DReference> curve2DReferenceList) {
    return new SpecialCurveImp(curve2DReferenceList);
  }
  
  public SpecialCurve copySpecialCurve(SpecialCurve scrv) {
    return new SpecialCurveImp(scrv);
  }

  public SpecialPoint createSpecialPoint(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new SpecialPointImp(vertexReferenceGroupList);
  }
  
  public SpecialPoint copySpecialPoint(SpecialPoint sp) {
    return new SpecialPointImp(sp);
  }

  public End createEnd() {
    return EndImp.createEndImp();
  }
  
  public End copyEnd(End end) {
    return EndImp.createEndImp();
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

  public CurveApproxCurvTechnique createCurvCurveAprox(BigDecimal maxDist,
      BigDecimal maxAngleInDegrees) {
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
