package com.ht.wfp3.api;

import com.ht.wfp3.api.BasisMatrix.Axis;

import java.util.List;

/**
 * The OBJ element factory.
 * 
 * The purpose of this factory is to hide the details of how the elements are created.
 * 
 * @author nickl
 *
 */
public interface Factory {

  static Document createObjDocument() {
    // TODO Auto-generated method stub
    return null;
  }

  static GeoVertex createGeoVertex(String xCoord, String yCoord, String zCoord, String wCoord) {
    // TODO Auto-generated method stub
    return null;
  }

  static TexVertex createTexVertex(String uCoord, String vCoord, String wCoord) {
    // TODO Auto-generated method stub
    return null;
  }

  static NormalVertex createNormalVertex(String iCoord, String jCoord, String kCoord) {
    // TODO Auto-generated method stub
    return null;
  }

  static ParamVertex createParamVertex(String uCoord, String vCoord, String wCoord) {
    // TODO Auto-generated method stub
    return null;
  }

  static VertexReferenceGroup createVertexReferenceGroup() {
    // TODO Auto-generated method stub
    return null;
  }

  static VertexReference createVertexReference(VertexReference.Type type, String vertexIndex) {
    // TODO Auto-generated method stub
    return null;
  }

  static Point createPoint() {
    // TODO Auto-generated method stub
    return null;
  }

  static Line createLine() {
    // TODO Auto-generated method stub
    return null;
  }

  static Face createFace() {
    // TODO Auto-generated method stub
    return null;
  }

  static CurveOrSurface createCurveOrSurfaceType(boolean isRational, CurveOrSurface.Type typeKey) {
    // TODO Auto-generated method stub
    return null;
  }

  static Degree createDegree(String uAxisDegree, String vAxisDegree) {
    // TODO Auto-generated method stub
    return null;
  }

  static BasisMatrix createBasisMatrix(Axis axis, List<String> flattenedMatrix) {
    // TODO Auto-generated method stub
    return null;
  }

  static StepSize createStepSize(String stepSizeInUAxis, String stepSizeInVAxis) {
    // TODO Auto-generated method stub
    return null;
  }

  static Curve createCurve(String startingParameterValue, String endingParameterValue) {
    // TODO Auto-generated method stub
    return null;
  }

  static Curve2D createCurve2D() {
    // TODO Auto-generated method stub
    return null;
  }

  static Surface createSurface(String startingParameterValueUAxis, String endingParameterValueUAxis,
      String startingParameterValueVAxis, String endingParameterValueVAxis) {
    // TODO Auto-generated method stub
    return null;
  }

  static Call createCall(String fileName) {
    // TODO Auto-generated method stub
    return null;
  }

  static Csh createCsh(boolean shouldIgnoreError, String command) {
    // TODO Auto-generated method stub
    return null;
  }

  static Parm createParm(Parm.Axis axis) {
    // TODO Auto-generated method stub
    return null;
  }

  static Trim createTrim() {
    // TODO Auto-generated method stub
    return null;
  }

  static Curve2DReference createCurve2DReference(String startingParameterValue,
      String endingParameterValue, String curve2DIndex) {
    // TODO Auto-generated method stub
    return null;
  }

  static Hole createHole() {
    // TODO Auto-generated method stub
    return null;
  }

  static SpecialCurve createSpecialCurve() {
    // TODO Auto-generated method stub
    return null;
  }

  static SpecialPoint createSpecialPoint() {
    // TODO Auto-generated method stub
    return null;
  }

  static End createEnd() {
    // TODO Auto-generated method stub
    return null;
  }

  static Connect createConnect(String firstSurfaceIndex,
      Curve2DReference curve2dReferenceForFirstSurface, String secondSurfaceIndex,
      Curve2DReference curve2dReferenceForSecondSurface) {
    // TODO Auto-generated method stub
    return null;
  }

  static GroupNameList createGroupName() {
    // TODO Auto-generated method stub
    return null;
  }

  static SmoothingGroup createSmoothingGroup(String groupNumberOrOff) {
    // TODO Auto-generated method stub
    return null;
  }

  static MergingGroup createMergingGroup(String groupNumberOrOff, String resolution) {
    // TODO Auto-generated method stub
    return null;
  }

  static ObjectName createObjectName(String objectName) {
    // TODO Auto-generated method stub
    return null;
  }
}
