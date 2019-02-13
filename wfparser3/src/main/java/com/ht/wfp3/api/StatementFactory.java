package com.ht.wfp3.api;

import com.ht.wfp3.api.BasisMatrix.Axis;
import com.ht.wfp3.api.acceptance.Unknown;

import java.util.List;

/**
 * The OBJ element factory.
 * 
 * The purpose of this factory is to hide the details of how the elements are created.
 * 
 * @author nickl
 *
 */
public interface StatementFactory {
  GeoVertex createGeoVertex(String xCoord, String yCoord, String zCoord, String wCoord);

  TexVertex createTexVertex(String uCoord, String vCoord, String wCoord);

  NormalVertex createNormalVertex(String iCoord, String jCoord, String kCoord);

  ParamVertex createParamVertex(String uCoord, String vCoord, String wCoord);

  VertexReferenceGroup createVertexReferenceGroup();

  VertexReference createVertexReference(VertexReference.Type type, String vertexIndex);

  Point createPoint();

  Line createLine();

  Face createFace();

  CurveOrSurface createCurveOrSurfaceType(String rational, CurveOrSurface.Type typeKey);

  Degree createDegree(String uAxisDegree, String vAxisDegree);

  BasisMatrix createBasisMatrix(Axis axis, List<String> flattenedMatrix);

  StepSize createStepSize(String stepSizeInUAxis, String stepSizeInVAxis);

  Curve createCurve(String startingParameterValue, String endingParameterValue);

  Curve2D createCurve2D();

  Surface createSurface(String startingParameterValueUAxis, String endingParameterValueUAxis,
      String startingParameterValueVAxis, String endingParameterValueVAxis);

  Call createCall(String fileName);

  Csh createCsh(String ignoreError, String command);

  Parm createParm(String axis);

  Trim createTrim();

  Curve2DReference createCurve2DReference(String startingParameterValue,
      String endingParameterValue, String curve2DIndex);

  Hole createHole();

  SpecialCurve createSpecialCurve();

  SpecialPoint createSpecialPoint();

  End createEnd();

  Connect createConnect(String firstSurfaceIndex, Curve2DReference curve2dReferenceForFirstSurface,
      String secondSurfaceIndex, Curve2DReference curve2dReferenceForSecondSurface);

  GroupNameList createGroupName();

  SmoothingGroup createSmoothingGroup(String groupNumberOrOff);

  MergingGroup createMergingGroup(String groupNumberOrOff, String resolution);

  ObjectName createObjectName(String objectName);

  Bevel createBevel(String onOrOff);

  ColorInterpolation createColorInterpolation(String onOrOff);

  DissolveInterpolation createDissolveInterpolation(String onOrOff);

  LevelOfDetail createLevelOfDetail(String levelOfDetail);

  MapLib createMapLib();

  UseMaterial createUseMaterial(String materialName);

  UseMap createUseMap(String mapNameOrOff);

  MaterialLib createMaterialLib();

  ShadowObject createShadowObject(String shadowObjectFileName);

  RayTracingObject createRayTracingObject(String rayTracingObjectFileName);

  CparmCurveApprox createCparmCurveApprox(String resolution);

  CspaceCurveApprox createCspaceCurveApprox(String maxLength);

  CurvCurveApprox createCurvCurveAprox(String maxDist, String maxAngleInDegrees);

  CparmaSurfaceApprox createCparmaSurfaceApprox(String resolutionForUAxis,
      String resolutionForVAxis);

  CparmbSurfaceApprox createCparmbSurfaceApprox(String resolutionForUAndVAxes);

  CspaceSurfaceApprox createCspaceSurfaceApprox(String maxLength);

  CurvSurfaceApprox createCurvSurfaceApprox(String maxDist, String maxAngleInDegrees);

  Blank createBlank();

  Unknown createUnknown(List<String> tokens);

  Comment createComment(String commentString);
}
