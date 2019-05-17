package com.ht.wfp3.api.statement;

import java.math.BigDecimal;
import java.nio.file.Path;
import java.util.List;

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

  public GeoVertex createGeoVertex(BigDecimal xCoord, BigDecimal yCoord, BigDecimal zCoord) {
    return new GeoVertexImp(xCoord, yCoord, zCoord);
  }

  public GeoVertex createGeoVertex(BigDecimal xCoord, BigDecimal yCoord, BigDecimal zCoord,
      BigDecimal wCoord) {
    return new GeoVertexImp(xCoord, yCoord, zCoord, wCoord);
  }

  public GeoVertex copyGeoVertex(GeoVertex geoVertex) {
    if (null == geoVertex) {
      throw new NullPointerException("geoVertex copy constructor parameter cannot be null");
    }
    return new GeoVertexImp(geoVertex);
  }

  public TexVertex createTexVertexWithDefaultVAndWCoords(BigDecimal uCoord) {
    return new TexVertexImp(uCoord);
  }

  public TexVertex createTexVertexWithDefaultWCoord(BigDecimal uCoord, BigDecimal vCoord) {
    return new TexVertexImp(uCoord, vCoord);
  }

  public TexVertex createTexVertex(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord) {
    return new TexVertexImp(uCoord, vCoord, wCoord);
  }

  public TexVertex copyTexVertex(TexVertex texVertex) {
    if (null == texVertex) {
      throw new NullPointerException("texVertex copy constructor parameter cannot be null");
    }
    return new TexVertexImp(texVertex);
  }

  public NormalVertex createNormalVertex(BigDecimal iCoord, BigDecimal jCoord, BigDecimal kCoord) {
    return new NormalVertexImp(iCoord, jCoord, kCoord);
  }

  public NormalVertex copyNormalVertex(NormalVertex normalVertex) {
    if (null == normalVertex) {
      throw new NullPointerException("normalVertex copy constructor parameter cannot be null");
    }
    return new NormalVertexImp(normalVertex);
  }

  public ParamVertex createParamVertex(BigDecimal uCoord) {
    return new ParamVertexImp(uCoord);
  }

  public ParamVertex createParamVertex(BigDecimal uCoord, BigDecimal vCoord) {
    return new ParamVertexImp(uCoord, vCoord);
  }

  public ParamVertex createParamVertexWithDefaultWCoord(BigDecimal uCoord, BigDecimal vCoord) {
    return new ParamVertexImp(uCoord, vCoord, ParamVertex.DEFAULT_W_COORD);
  }

  public ParamVertex createParamVertex(BigDecimal uCoord, BigDecimal vCoord, BigDecimal wCoord) {
    return new ParamVertexImp(uCoord, vCoord, wCoord);
  }

  public ParamVertex copyParamVertex(ParamVertex paramVertex) {
    ParamVertex copiedParamVertex;
    if (null == paramVertex) {
      throw new NullPointerException("paramVertex copy constructor parameter cannot be null");
    }
    if (!paramVertex.isVCoordSet()) {
      copiedParamVertex = new ParamVertexImp(paramVertex.getUCoord());
    } else if (!paramVertex.isWCoordSet()) {
      copiedParamVertex = new ParamVertexImp(paramVertex.getUCoord(), paramVertex.getVCoord());
    } else {
      copiedParamVertex = new ParamVertexImp(paramVertex.getUCoord(), paramVertex.getVCoord(),
          paramVertex.getWCoord());
    }

    return copiedParamVertex;
  }

  public VertexReferenceGroupBuilder createVertexReferenceGroupBuilder() {
    return new VertexReferenceGroupBuilderImp();
  }

  public VertexReferenceGroup copyVertexReferenceGroup(VertexReferenceGroup vertexReferenceGroup) {
    if (null == vertexReferenceGroup) {
      throw new NullPointerException(
          "vertexReferenceGroup copy constructor parameter cannot be null");
    }
    return new VertexReferenceGroupImp((VertexReferenceGroupImp) vertexReferenceGroup);
  }

  public GeoVertexReference createGeoVertexReference(Integer vertexIndex) {
    if (GeoVertexReference.INDEX_NOT_SET_VALUE.equals(vertexIndex)) {
      throw new IllegalArgumentException("vertexIndex constructor parameter cannot equal "
          + GeoVertexReference.INDEX_NOT_SET_VALUE);
    }
    return new GeoVertexReferenceImp(vertexIndex);
  }

  public GeoVertexReference copyGeoVertexReference(GeoVertexReference geoVertexReference) {
    if (null == geoVertexReference) {
      throw new NullPointerException(
          "geoVertexReference copy constructor parameter cannot be null");
    }
    return new GeoVertexReferenceImp(geoVertexReference);
  }

  public TexVertexReference createTexVertexReference(Integer vertexIndex) {
    if (TexVertexReference.INDEX_NOT_SET_VALUE.equals(vertexIndex)) {
      throw new IllegalArgumentException("vertexIndex constructor parameter cannot equal "
          + TexVertexReference.INDEX_NOT_SET_VALUE);
    }
    return new TexVertexReferenceImp(vertexIndex);
  }

  TexVertexReference createDisabledTexVertexReference() {
    return new TexVertexReferenceImp(TexVertexReference.INDEX_NOT_SET_VALUE);
  }

  public TexVertexReference copyTexVertexReference(TexVertexReference texVertexReference) {
    if (null == texVertexReference) {
      throw new NullPointerException(
          "texVertexReference copy constructor parameter cannot be null");
    }
    return new TexVertexReferenceImp(texVertexReference);
  }

  public NormalVertexReference createNormalVertexReference(Integer vertexIndex) {
    if (NormalVertexReference.INDEX_NOT_SET_VALUE.equals(vertexIndex)) {
      throw new IllegalArgumentException("vertexIndex constructor parameter cannot equal "
          + NormalVertexReference.INDEX_NOT_SET_VALUE);
    }
    return new NormalVertexReferenceImp(vertexIndex);
  }

  NormalVertexReference createDisabledNormalVertexReference() {
    return new NormalVertexReferenceImp(VertexReference.INDEX_NOT_SET_VALUE);
  }

  public NormalVertexReference copyNormalVertexReference(
      NormalVertexReference normalVertexReference) {
    if (null == normalVertexReference) {
      throw new NullPointerException(
          "normalVertexReference copy constructor parameter cannot be null");
    }
    return new NormalVertexReferenceImp(normalVertexReference);
  }

  public ParamVertexReference createParamVertexReference(Integer vertexIndex) {
    if (ParamVertexReference.INDEX_NOT_SET_VALUE.equals(vertexIndex)) {
      throw new IllegalArgumentException("vertexIndex constructor parameter cannot equal "
          + ParamVertexReference.INDEX_NOT_SET_VALUE);
    }
    return new ParamVertexReferenceImp(vertexIndex);
  }

  public ParamVertexReference copyParamVertexReference(ParamVertexReference paramVertexReference) {
    if (null == paramVertexReference) {
      throw new NullPointerException(
          "paramVertexReference copy constructor parameter cannot be null");
    }
    return new ParamVertexReferenceImp(paramVertexReference);
  }

  public Point createPoint(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new PointImp(vertexReferenceGroupList);
  }

  public Point copyPoint(Point point) {
    if (null == point) {
      throw new NullPointerException("point copy constructor parameter cannot be null");
    }
    return new PointImp(point);
  }

  public Line createLine(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new LineImp(vertexReferenceGroupList);
  }

  public Line copyLine(Line line) {
    if (null == line) {
      throw new NullPointerException("line copy constructor parameter cannot be null");
    }
    return new LineImp(line);
  }

  public Face createFace(List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new FaceImp(vertexReferenceGroupList);
  }

  public Face copyFace(Face face) {
    if (null == face) {
      throw new NullPointerException("face copy constructor parameter cannot be null");
    }
    return new FaceImp(face);
  }

  public CurveOrSurfaceType createCurveOrSurface(boolean isRational,
      CurveOrSurfaceType.Key typeKey) {
    return new CurveOrSurfaceTypeImp(isRational, typeKey);
  }

  public CurveOrSurfaceType copyCurveOrSurfaceType(CurveOrSurfaceType cstype) {
    if (null == cstype) {
      throw new NullPointerException("cstype copy constructor parameter cannot be null");
    }
    return new CurveOrSurfaceTypeImp(cstype);
  }

  public Degree createDegree(Integer uAxisDegree, Integer vAxisDegree) {
    return new DegreeImp(uAxisDegree, vAxisDegree);
  }

  public Degree createDegree(Integer uAxisDegree) {
    return new DegreeImp(uAxisDegree);
  }

  public Degree copyDegree(Degree deg) {
    if (null == deg) {
      throw new NullPointerException("deg copy constructor parameter cannot be null");
    }
    return new DegreeImp(deg);
  }

  public MatrixBuilder createMatrixBuilder() {
    return new MatrixBuilderImp();
  }

  public BasisMatrix createBasisMatrix(Axis axis, Matrix matrix) {
    return new BasisMatrixImp(axis, matrix);
  }

  public BasisMatrix copyBasisMatrix(BasisMatrix bmat) {
    if (null == bmat) {
      throw new NullPointerException("bmat copy constructor parameter cannot be null");
    }
    return new BasisMatrixImp(bmat);
  }

  public StepSize createStepSize(Integer stepSizeInUAxis) {
    return new StepSizeImp(stepSizeInUAxis);
  }

  public StepSize createStepSize(Integer stepSizeInUAxis, Integer stepSizeInVAxis) {
    return new StepSizeImp(stepSizeInUAxis, stepSizeInVAxis);
  }

  public StepSize copyStepSize(StepSize step) {
    if (null == step) {
      throw new NullPointerException("step copy constructor parameter cannot be null");
    }
    StepSize copiedStepSize;
    if (step.isStepSizeInVAxisSet()) {
      copiedStepSize = new StepSizeImp(step.getStepSizeInUAxis(), step.getStepSizeInVAxis());
    } else {
      copiedStepSize = new StepSizeImp(step.getStepSizeInUAxis());
    }
    return copiedStepSize;
  }

  public Curve createCurve(BigDecimal startingParameterValue, BigDecimal endingParameterValue,
      List<GeoVertexReference> vertexReferenceGroupList) {
    return new CurveImp(startingParameterValue, endingParameterValue, vertexReferenceGroupList);
  }

  public Curve copyCurve(Curve curv) {
    if (null == curv) {
      throw new NullPointerException("curv copy constructor parameter cannot be null");
    }
    return new CurveImp(curv);
  }

  public Curve2D createCurve2D(List<ParamVertexReference> controlPointVertexReferenceList) {
    return new Curve2DImp(controlPointVertexReferenceList);
  }

  public Curve2D copyCurve2D(Curve2D curv2) {
    if (null == curv2) {
      throw new NullPointerException("curv2 copy constructor parameter cannot be null");
    }
    return new Curve2DImp(curv2);
  }

  public Surface createSurface(BigDecimal startingParameterValueUAxis,
      BigDecimal endingParameterValueUAxis, BigDecimal startingParameterValueVAxis,
      BigDecimal endingParameterValueVAxis, List<VertexReferenceGroup> vertexReferenceGroupList) {
    return new SurfaceImp(startingParameterValueUAxis, endingParameterValueUAxis,
        startingParameterValueVAxis, endingParameterValueVAxis, vertexReferenceGroupList);
  }

  public Surface copySurface(Surface surf) {
    if (null == surf) {
      throw new NullPointerException("surf copy constructor parameter cannot be null");
    }
    return new SurfaceImp(surf);
  }

  public Call createCall(boolean isFrameNumberRequired, Path fileName, List<Integer> arguments) {
    return new CallImp(isFrameNumberRequired, fileName, arguments);
  }

  public Call copyCall(Call call) {
    if (null == call) {
      throw new NullPointerException("call copy constructor parameter cannot be null");
    }
    return new CallImp(call);
  }

  public Csh createCsh(boolean shouldIgnoreError, String command) {
    return new CshImp(shouldIgnoreError, command);
  }

  public Csh copyCsh(Csh csh) {
    if (null == csh) {
      throw new NullPointerException("csh copy constructor parameter cannot be null");
    }
    return new CshImp(csh);
  }

  public Parm createParm(Axis axis, List<BigDecimal> parameterList) {
    return new ParmImp(axis, parameterList);
  }

  public Parm copyParm(Parm parm) {
    if (null == parm) {
      throw new NullPointerException("parm copy constructor parameter cannot be null");
    }
    return new ParmImp(parm);
  }

  public Curve2DReference createCurve2DReference(BigDecimal startingParameterValue,
      BigDecimal endingParameterValue, Integer curve2DIndex) {
    return new Curve2DReferenceImp(startingParameterValue, endingParameterValue, curve2DIndex);
  }

  public Curve2DReference copyCurve2DReference(Curve2DReference curve2DReference) {
    if (null == curve2DReference) {
      throw new NullPointerException("curev2DReference copy constructor parameter cannot be null");
    }
    return new Curve2DReferenceImp(curve2DReference);
  }

  public Trim createTrim(List<Curve2DReference> curve2DReferenceList) {
    return new TrimImp(curve2DReferenceList);
  }

  public Trim copyTrim(Trim trim) {
    if (null == trim) {
      throw new NullPointerException("trim copy constructor parameter cannot be null");
    }
    return new TrimImp(trim);
  }

  public Hole createHole(List<Curve2DReference> curve2DReferenceList) {
    return new HoleImp(curve2DReferenceList);
  }

  public Hole copyHole(Hole hole) {
    if (null == hole) {
      throw new NullPointerException("hole copy constructor parameter cannot be null");
    }
    return new HoleImp(hole);
  }

  public SpecialCurve createSpecialCurve(List<Curve2DReference> curve2DReferenceList) {
    return new SpecialCurveImp(curve2DReferenceList);
  }

  public SpecialCurve copySpecialCurve(SpecialCurve scrv) {
    if (null == scrv) {
      throw new NullPointerException("scrv copy constructor parameter cannot be null");
    }
    return new SpecialCurveImp(scrv);
  }

  public SpecialPoint createSpecialPoint(List<ParamVertexReference> vertexReferenceList) {
    return new SpecialPointImp(vertexReferenceList);
  }

  public SpecialPoint copySpecialPoint(SpecialPoint sp) {
    if (null == sp) {
      throw new NullPointerException("sp copy constructor parameter cannot be null");
    }
    return new SpecialPointImp(sp);
  }

  public End createEnd() {
    return EndImp.createEndImp();
  }

  public End copyEnd(End end) {
    if (null == end) {
      throw new NullPointerException("end copy constructor parameter cannot be null");
    }
    return EndImp.createEndImp();
  }

  public Connect createConnect(Integer firstSurfaceIndex,
      Curve2DReference firstSurfaceCurve2dReference, Integer secondSurfaceIndex,
      Curve2DReference secondSurfaceCurve2dReference) {
    return new ConnectImp(firstSurfaceIndex, firstSurfaceCurve2dReference, secondSurfaceIndex,
        secondSurfaceCurve2dReference);
  }

  public Connect copyConnect(Connect con) {
    if (null == con) {
      throw new NullPointerException("con copy constructor parameter cannot be null");
    }
    return new ConnectImp(con);
  }

  public GroupNameList createGroupNameList(List<String> groupNameList) {
    return new GroupNameListImp(groupNameList);
  }

  public GroupNameList copyGroupNameList(GroupNameList g) {
    if (null == g) {
      throw new NullPointerException("g copy constructor parameter cannot be null");
    }
    return new GroupNameListImp(g);
  }

  public SmoothingGroup createSmoothingGroup(Integer groupNumberOrOff) {
    return new SmoothingGroupImp(groupNumberOrOff);
  }

  public SmoothingGroup copySmoothingGroup(SmoothingGroup s) {
    if (null == s) {
      throw new NullPointerException("s copy constructor parameter cannot be null");
    }
    return new SmoothingGroupImp(s);
  }

  public MergingGroup createMergingGroup(Integer groupNumberOrOff, BigDecimal resolution) {
    return new MergingGroupImp(groupNumberOrOff, resolution);
  }

  public MergingGroup copyMergingGroup(MergingGroup mg) {
    if (null == mg) {
      throw new NullPointerException("mg copy constructor parameter cannot be null");
    }
    return new MergingGroupImp(mg);
  }

  public ObjectName createObjectName(String objectName) {
    return new ObjectNameImp(objectName);
  }

  public ObjectName copyObjectName(ObjectName o) {
    if (null == o) {
      throw new NullPointerException("o copy constructor parameter cannot be null");
    }
    return new ObjectNameImp(o);
  }

  public Bevel createBevel(boolean isEnabled) {
    return new BevelImp(isEnabled);
  }

  public Bevel copyBevel(Bevel bevel) {
    if (null == bevel) {
      throw new NullPointerException("bevel copy constructor parameter cannot be null");
    }
    return new BevelImp(bevel);
  }

  public ColorInterpolation createColorInterpolation(boolean isEnabled) {
    return new ColorInterpolationImp(isEnabled);
  }

  public ColorInterpolation copyColorInterpolation(ColorInterpolation c_interp) {
    if (null == c_interp) {
      throw new NullPointerException("c_interp copy constructor parameter cannot be null");
    }
    return new ColorInterpolationImp(c_interp);
  }

  public DissolveInterpolation createDissolveInterpolation(boolean isEnabled) {
    return new DissolveInterpolationImp(isEnabled);
  }

  public DissolveInterpolation copyDissolveInterpolation(DissolveInterpolation d_interp) {
    if (null == d_interp) {
      throw new NullPointerException("d_interp copy constructor parameter cannot be null");
    }
    return new DissolveInterpolationImp(d_interp);
  }

  public LevelOfDetail createLevelOfDetail(Integer levelOfDetail) {
    return new LevelOfDetailImp(levelOfDetail);
  }

  public LevelOfDetail copyLevelOfDetail(LevelOfDetail lod) {
    if (null == lod) {
      throw new NullPointerException("lod copy constructor parameter cannot be null");
    }
    return new LevelOfDetailImp(lod);
  }

  public MapLib createMapLib(List<Path> mapLibFileNameList) {
    return new MapLibImp(mapLibFileNameList);
  }

  public MapLib copyMapLib(MapLib maplib) {
    if (null == maplib) {
      throw new NullPointerException("maplib copy constructor parameter cannot be null");
    }
    return new MapLibImp(maplib);
  }

  public UseMap createUseMap(String mapNameOrOff) {
    return new UseMapImp(mapNameOrOff);
  }

  public UseMap copyUseMap(UseMap usemap) {
    if (null == usemap) {
      throw new NullPointerException("usemap copy constructor parameter cannot be null");
    }
    return new UseMapImp(usemap);
  }

  public UseMaterial createUseMaterial(String materialName) {
    return new UseMaterialImp(materialName);
  }

  public UseMaterial copyUseMaterial(UseMaterial usemtl) {
    if (null == usemtl) {
      throw new NullPointerException("usemtl copy constructor parameter cannot be null");
    }
    return new UseMaterialImp(usemtl);
  }

  public MaterialLib createMaterialLib(List<Path> materialLibFileNameList) {
    return new MaterialLibImp(materialLibFileNameList);
  }

  public MaterialLib copyMaterialLib(MaterialLib mtllib) {
    if (null == mtllib) {
      throw new NullPointerException("mtllib copy constructor parameter cannot be null");
    }
    return new MaterialLibImp(mtllib);
  }

  public ShadowObject createShadowObject(Path shadowObjectFileName) {
    return new ShadowObjectImp(shadowObjectFileName);
  }

  public ShadowObject copyShadowObject(ShadowObject shadow_obj) {
    if (null == shadow_obj) {
      throw new NullPointerException("shadow_obj copy constructor parameter cannot be null");
    }
    return new ShadowObjectImp(shadow_obj);
  }

  public RayTracingObject createRayTracingObject(Path rayTracingObjectFileName) {
    return new RayTracingObjectImp(rayTracingObjectFileName);
  }

  public RayTracingObject copyRayTracingObject(RayTracingObject trace_obj) {
    if (null == trace_obj) {
      throw new NullPointerException("trace_obj copy constructor parameter cannot be null");
    }
    return new RayTracingObjectImp(trace_obj);
  }

  public CurveApproxCparmTechnique createCurveApproxCparmTechnique(BigDecimal resolution) {
    return new CurveApproxCparmTechniqueImp(resolution);
  }

  public CurveApproxCparmTechnique copyCurveApproxCparmTechnique(
      CurveApproxCparmTechnique curveApproxCparmTechnique) {
    if (null == curveApproxCparmTechnique) {
      throw new NullPointerException(
          "curveApproxCparmTechnique copy constructor parameter cannot be null");
    }
    return new CurveApproxCparmTechniqueImp(curveApproxCparmTechnique);
  }

  public CurveApproxCspaceTechnique createCurveApproxCspaceTechnique(BigDecimal maxLength) {
    return new CurveApproxCspaceTechniqueImp(maxLength);
  }

  public CurveApproxCspaceTechnique copyCurveApproxCspaceTechnique(
      CurveApproxCspaceTechnique curveApproxCspaceTechnique) {
    if (null == curveApproxCspaceTechnique) {
      throw new NullPointerException(
          "curveApproxCspaceTechnique copy constructor parameter cannot be null");
    }
    return new CurveApproxCspaceTechniqueImp(curveApproxCspaceTechnique);
  }

  public CurveApproxCurvTechnique createCurveApproxCurvTechnique(BigDecimal maximumDistance,
      BigDecimal maximumAngleInDegrees) {
    return new CurveApproxCurvTechniqueImp(maximumDistance, maximumAngleInDegrees);
  }

  public CurveApproxCurvTechnique copyCurveApproxCurvTechnique(
      CurveApproxCurvTechnique curveApproxCurvTechnique) {
    if (null == curveApproxCurvTechnique) {
      throw new NullPointerException(
          "curveApproxCurvTechnique copy constructor parameter cannot be null");
    }
    return new CurveApproxCurvTechniqueImp(curveApproxCurvTechnique);
  }

  public SurfaceApproxCparmaTechnique createSurfaceApproxCparmaTechnique(
      BigDecimal resolutionForUAxis, BigDecimal resolutionForVAxis) {
    return new SurfaceApproxCparmaTechniqueImp(resolutionForUAxis, resolutionForVAxis);
  }

  public SurfaceApproxCparmaTechnique copySurfaceApproxCparmaTechnique(
      SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique) {
    if (null == surfaceApproxCparmaTechnique) {
      throw new NullPointerException(
          "surfaceApproxCparmaTechnique copy constructor parameter cannot be null");
    }
    return new SurfaceApproxCparmaTechniqueImp(surfaceApproxCparmaTechnique);
  }

  public SurfaceApproxCparmbTechnique createSurfaceApproxCparmbTechnique(
      BigDecimal resolutionForUAndVAxes) {
    return new SurfaceApproxCparmbTechniqueImp(resolutionForUAndVAxes);
  }

  public SurfaceApproxCparmbTechnique copySurfaceApproxCparmbTechnique(
      SurfaceApproxCparmbTechnique surfaceApproxCparmbTechnique) {
    if (null == surfaceApproxCparmbTechnique) {
      throw new NullPointerException(
          "surfaceApproxCparmbTechnique copy constructor parameter cannot be null");
    }
    return new SurfaceApproxCparmbTechniqueImp(surfaceApproxCparmbTechnique);
  }

  public SurfaceApproxCspaceTechnique createSurfaceApproxCspaceTechnique(BigDecimal maxLength) {
    return new SurfaceApproxCspaceTechniqueImp(maxLength);
  }

  public SurfaceApproxCspaceTechnique copySurfaceApproxCspaceTechnique(
      SurfaceApproxCspaceTechnique surfaceApproxCspaceTechnique) {
    if (null == surfaceApproxCspaceTechnique) {
      throw new NullPointerException(
          "surfaceApproxCspaceTechnique copy constructor parameter cannot be null");
    }
    return new SurfaceApproxCspaceTechniqueImp(surfaceApproxCspaceTechnique);
  }

  public SurfaceApproxCurvTechnique createSurfaceApproxCurvTechnique(BigDecimal maxDistance,
      BigDecimal maxAngleInDegrees) {
    return new SurfaceApproxCurvTechniqueImp(maxDistance, maxAngleInDegrees);
  }

  public SurfaceApproxCurvTechnique copySurfaceApproxCurvTechnique(
      SurfaceApproxCurvTechnique surfaceApproxCurvTechnique) {
    if (null == surfaceApproxCurvTechnique) {
      throw new NullPointerException(
          "surfaceApproxCurvTechnique copy constructor parameter cannot be null");
    }
    return new SurfaceApproxCurvTechniqueImp(surfaceApproxCurvTechnique);
  }

  public boolean isSupportedStatement(Statement statement) {
    return statement instanceof StatementImp;
  }
}
