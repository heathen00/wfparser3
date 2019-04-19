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
      Curve2DReference firstSurfaceCurve2dReference, Integer secondSurfaceIndex,
      Curve2DReference secondSurfaceCurve2dReference) {
    return new ConnectImp(firstSurfaceIndex, firstSurfaceCurve2dReference, secondSurfaceIndex,
        secondSurfaceCurve2dReference);
  }

  public Connect copyConnect(Connect con) {
    return new ConnectImp(con);
  }

  public GroupNameList createGroupNameList(List<String> groupNameList) {
    return new GroupNameListImp(groupNameList);
  }

  public GroupNameList copyGroupNameList(GroupNameList g) {
    return new GroupNameListImp(g);
  }

  public SmoothingGroup createSmoothingGroup(Integer groupNumberOrOff) {
    boolean isEnabled = (groupNumberOrOff == 0 ? false : true);
    return new SmoothingGroupImp(groupNumberOrOff);
  }

  public SmoothingGroup copySmoothingGroup(SmoothingGroup s) {
    return new SmoothingGroupImp(s);
  }

  public MergingGroup createMergingGroup(Integer groupNumberOrOff, BigDecimal resolution) {
    return new MergingGroupImp(groupNumberOrOff, resolution);
  }

  public MergingGroup copyMergingGroup(MergingGroup mg) {
    return new MergingGroupImp(mg);
  }

  public ObjectName createObjectName(String objectName) {
    return new ObjectNameImp(objectName);
  }

  public ObjectName copyObjectName(ObjectName o) {
    return new ObjectNameImp(o);
  }

  public Bevel createBevel(boolean isEnabled) {
    return new BevelImp(isEnabled);
  }

  public Bevel copyBevel(Bevel bevel) {
    return new BevelImp(bevel);
  }

  public ColorInterpolation createColorInterpolation(boolean isEnabled) {
    return new ColorInterpolationImp(isEnabled);
  }

  public ColorInterpolation copyColorInterpolation(ColorInterpolation c_interp) {
    return new ColorInterpolationImp(c_interp);
  }

  public DissolveInterpolation createDissolveInterpolation(boolean isEnabled) {
    return new DissolveInterpolationImp(isEnabled);
  }

  public DissolveInterpolation copyDissolveInterpolation(DissolveInterpolation d_interp) {
    return new DissolveInterpolationImp(d_interp);
  }

  public LevelOfDetail createLevelOfDetail(Integer levelOfDetail) {
    return new LevelOfDetailImp(levelOfDetail);
  }

  public LevelOfDetail copyLevelOfDetail(LevelOfDetail lod) {
    return new LevelOfDetailImp(lod);
  }

  public MapLib createMapLib(List<Path> mapLibFileNameList) {
    return new MapLibImp(mapLibFileNameList);
  }

  public MapLib copyMapLib(MapLib maplib) {
    return new MapLibImp(maplib);
  }

  public UseMap createUseMap(String mapNameOrOff) {
    return new UseMapImp(mapNameOrOff);
  }

  public UseMap copyUseMap(UseMap usemap) {
    return new UseMapImp(usemap);
  }

  public UseMaterial createUseMaterial(String materialName) {
    return new UseMaterialImp(materialName);
  }

  public UseMaterial copyUseMaterial(UseMaterial usemtl) {
    return new UseMaterialImp(usemtl);
  }

  public MaterialLib createMaterialLib(List<Path> materialLibFileNameList) {
    return new MaterialLibImp(materialLibFileNameList);
  }

  public MaterialLib copyMaterialLib(MaterialLib mtllib) {
    return new MaterialLibImp(mtllib);
  }

  public ShadowObject createShadowObject(Path shadowObjectFileName) {
    return new ShadowObjectImp(shadowObjectFileName);
  }

  public ShadowObject copyShadowObject(ShadowObject shadow_obj) {
    return new ShadowObjectImp(shadow_obj);
  }

  public RayTracingObject createRayTracingObject(Path rayTracingObjectFileName) {
    return new RayTracingObjectImp(rayTracingObjectFileName);
  }

  public RayTracingObject copyRayTracingObject(RayTracingObject trace_obj) {
    return new RayTracingObjectImp(trace_obj);
  }

  public CurveApproxCparmTechnique createCurveApproxCparmTechnique(BigDecimal resolution) {
    return new CurveApproxCparmTechniqueImp(resolution);
  }

  public CurveApproxCparmTechnique copyCurveApproxCparmTechnique(
      CurveApproxCparmTechnique curveApproxCparmTechnique) {
    return new CurveApproxCparmTechniqueImp(curveApproxCparmTechnique);
  }

  public CurveApproxCspaceTechnique createCurveApproxCspaceTechnique(BigDecimal maxLength) {
    return new CurveApproxCspaceTechniqueImp(maxLength);
  }

  public CurveApproxCspaceTechnique copyCurveApproxCspaceTechnique(
      CurveApproxCspaceTechnique curveApproxCspaceTechnique) {
    return new CurveApproxCspaceTechniqueImp(curveApproxCspaceTechnique);
  }

  public CurveApproxCurvTechnique createCurveAproxCurvTechnique(BigDecimal maximumDististance,
      BigDecimal maximumAngleInDegrees) {
    return new CurveApproxCurvTechniqueImp(maximumDististance, maximumAngleInDegrees);
  }

  public CurveApproxCurvTechnique copyCurveApproxCurvTechnique(
      CurveApproxCurvTechnique curveApproxCurvTechnique) {
    return new CurveApproxCurvTechniqueImp(curveApproxCurvTechnique);
  }

  public SurfaceApproxCparmaTechnique createSurfaceApproxCparmaTechnique(
      BigDecimal resolutionForUAxis, BigDecimal resolutionForVAxis) {
    return new SurfaceApproxCparmaTechniqueImp(resolutionForUAxis, resolutionForVAxis);
  }

  public SurfaceApproxCparmaTechnique copySurfaceApproxCparmaTechnique(
      SurfaceApproxCparmaTechnique surfaceApproxCparmaTechnique) {
    return new SurfaceApproxCparmaTechniqueImp(surfaceApproxCparmaTechnique);
  }

  public SurfaceApproxCparmbTechnique createSurfaceApproxCparmbTechnique(
      BigDecimal resolutionForUAndVAxes) {
    return new SurfaceApproxCparmbTechniqueImp(resolutionForUAndVAxes);
  }

  public SurfaceApproxCparmbTechnique copySurfaceApproxCparmbTechnique(
      SurfaceApproxCparmbTechnique surfaceApproxCparmbTechnique) {
    return new SurfaceApproxCparmbTechniqueImp(surfaceApproxCparmbTechnique);
  }

  public SurfaceApproxCspaceTechnique createSurfaceApproxCspaceTechnique(BigDecimal maxLength) {
    return new SurfaceApproxCspaceTechniqueImp(maxLength);
  }
  
  public SurfaceApproxCspaceTechnique copySurfaceApproxCspaceTechnique(SurfaceApproxCspaceTechnique surfaceApproxCspaceTechnique) {
    return new SurfaceApproxCspaceTechniqueImp(surfaceApproxCspaceTechnique);
  }

  public SurfaceApproxCurvTechnique createSurfaceApproxCurvTechnique(BigDecimal maxDistance,
      BigDecimal maxAngleInDegrees) {
    return new SurfaceApproxCurvTechniqueImp(maxDistance, maxAngleInDegrees);
  }
  
  public SurfaceApproxCurvTechnique copySurfaceApproxCurvTechnique(SurfaceApproxCurvTechnique surfaceApproxCurvTechnique) {
    return new SurfaceApproxCurvTechniqueImp(surfaceApproxCurvTechnique);
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
