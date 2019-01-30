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

  static ReferenceNumbers createReferenceNumbers(String geometricVertexReferenceNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  static ReferenceNumbers createReferenceNumbers(String geometricVertexReferenceNumber,
      String textureVertexReferenceNumber) {
    // TODO Auto-generated method stub
    return null;
  }

  static ReferenceNumbers createReferenceNumbers(String geometricVertexReferenceNumber,
      String textureVertexReferenceNumber, String normalVertexReferenceNumber) {
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
}
