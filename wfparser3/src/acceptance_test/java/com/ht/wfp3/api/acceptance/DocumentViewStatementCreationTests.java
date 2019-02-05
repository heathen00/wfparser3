package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.wfp3.api.BasisMatrix;
import com.ht.wfp3.api.Bevel;
import com.ht.wfp3.api.Blank;
import com.ht.wfp3.api.Call;
import com.ht.wfp3.api.ColorInterpolation;
import com.ht.wfp3.api.Comment;
import com.ht.wfp3.api.Connect;
import com.ht.wfp3.api.CparmCurveApprox;
import com.ht.wfp3.api.CparmaSurfaceApprox;
import com.ht.wfp3.api.CparmbSurfaceApprox;
import com.ht.wfp3.api.Csh;
import com.ht.wfp3.api.CspaceCurveApprox;
import com.ht.wfp3.api.CspaceSurfaceApprox;
import com.ht.wfp3.api.Cursor;
import com.ht.wfp3.api.CurvCurveApprox;
import com.ht.wfp3.api.CurvSurfaceApprox;
import com.ht.wfp3.api.Curve;
import com.ht.wfp3.api.Curve2D;
import com.ht.wfp3.api.Curve2DReference;
import com.ht.wfp3.api.CurveOrSurface;
import com.ht.wfp3.api.Degree;
import com.ht.wfp3.api.DissolveInterpolation;
import com.ht.wfp3.api.Document;
import com.ht.wfp3.api.End;
import com.ht.wfp3.api.Face;
import com.ht.wfp3.api.Factory;
import com.ht.wfp3.api.GeoVertex;
import com.ht.wfp3.api.GroupNameList;
import com.ht.wfp3.api.Hole;
import com.ht.wfp3.api.LevelOfDetail;
import com.ht.wfp3.api.Line;
import com.ht.wfp3.api.MapLib;
import com.ht.wfp3.api.MaterialLib;
import com.ht.wfp3.api.MergingGroup;
import com.ht.wfp3.api.NormalVertex;
import com.ht.wfp3.api.ObjectName;
import com.ht.wfp3.api.ParamVertex;
import com.ht.wfp3.api.Parm;
import com.ht.wfp3.api.Point;
import com.ht.wfp3.api.RayTracingObject;
import com.ht.wfp3.api.ShadowObject;
import com.ht.wfp3.api.SmoothingGroup;
import com.ht.wfp3.api.SpecialCurve;
import com.ht.wfp3.api.SpecialPoint;
import com.ht.wfp3.api.StepSize;
import com.ht.wfp3.api.Surface;
import com.ht.wfp3.api.TexVertex;
import com.ht.wfp3.api.Trim;
import com.ht.wfp3.api.UseMap;
import com.ht.wfp3.api.UseMaterial;
import com.ht.wfp3.api.VertexReference;
import com.ht.wfp3.api.VertexReferenceGroup;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

public class DocumentViewStatementCreationTests {

  @Test
  public void Document_createEmptyObjDocument_EmptyDocumentIsCreated() {
    Document objDocument = Factory.createObjDocument();

    assertNotNull(objDocument);
  }

  @Test
  public void Document_addOneGeometricVertexToEmptyObjDocumentAtCursor_OneGeometricVertexIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    GeoVertex geoVertex = Factory.createGeoVertex("1.000", "2.000", "3.000", "4.000");
    objDocument.append(geoVertex, cursor);

    assertEquals(geoVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneTextureVertexToEmptyObjDocumentAtCursor_OneTextureVertexIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    TexVertex texVertex = Factory.createTexVertex("3.3", "2.2", "1.1");
    objDocument.append(texVertex, cursor);

    assertEquals(texVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneNormalVertexToEmptyObjDocumentAtCursor_OneNormalVertexIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    NormalVertex normalVertex = Factory.createNormalVertex("9.9", "8.8", "7.7");
    objDocument.append(normalVertex, cursor);

    assertEquals(normalVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneParamVertexToEmptyObjDocumentAtCursor_OneParamVertexIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    ParamVertex paramVertex = Factory.createParamVertex("3.13", "3.31", "1.33");
    objDocument.append(paramVertex, cursor);

    assertEquals(paramVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOnePointToEmptyObjDocumentAtCursor_OnePointIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Point point = Factory.createPoint();
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    objDocument.append(point, cursor);

    assertEquals(point, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneLineToEmptyObjDocumentAtCursor_OneLineIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Line line = Factory.createLine();
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "1"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "2"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "3"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "4"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    objDocument.append(line, cursor);

    assertEquals(line, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneFaceToEmptyObjDocumentAtCursor_OneFaceIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Face face = Factory.createFace();
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "1"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "1"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "2"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "2"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "3"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "3"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "4"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "4"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    objDocument.append(face, cursor);

    assertEquals(face, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCSTypeToEmptyObjDocumentAtCursor_OneCSTypeIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CurveOrSurface cstype = Factory.createCurveOrSurfaceType("rat", CurveOrSurface.Type.BMATRIX);
    objDocument.append(cstype, cursor);

    assertEquals(cstype, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneDegreeToEmptyObjDocumentAtCursor_OneDegreeIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Degree deg = Factory.createDegree("5", "6");
    objDocument.append(deg, cursor);

    assertEquals(deg, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneBasisMatrixToEmptyObjDocumentAtCursor_OneBasisMatrixIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    String[] flattenedUAxisBasisMatrix =
        { "1.234", "-1.234", "4.321", "-4.321", "9.876", "-9.876" };
    BasisMatrix bmat =
        Factory.createBasisMatrix(BasisMatrix.Axis.U, Arrays.asList(flattenedUAxisBasisMatrix));
    objDocument.append(bmat, cursor);

    assertEquals(bmat, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneStepToEmptyObjDocumentAtCursor_OneStepIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    StepSize step = Factory.createStepSize("7", "33");
    objDocument.append(step, cursor);

    assertEquals(step, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurveToEmptyObjDocumentAtCursor_OneCurveIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Curve curv = Factory.createCurve("1.23456", "9.5321");
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(curv, cursor);

    assertEquals(curv, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurve2DToEmptyObjDocumentAtCursor_OneCurve2DIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Curve2D curv2 = Factory.createCurve2D();
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "1"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "2"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "3"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "4"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(curv2, cursor);

    assertEquals(curv2, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSurfaceToEmptyObjDocumentAtCursor_OneSurfaceIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Surface surf = Factory.createSurface("1.2345", "-5.4321", "9.8765", "-5.6789");
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "1"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "1"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "2"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "2"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "3"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "3"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.TEXTURE, "4"));
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.NORMAL, "4"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(surf, cursor);

    assertEquals(surf, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCallToEmptyObjDocumentAtCursor_OneCallIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Call call = Factory.createCall("filename.obj");
    call.appendArgument("3");
    call.appendArgument("5");
    call.appendArgument("78");
    objDocument.append(call, cursor);

    assertEquals(call, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCshToEmptyObjDocumentAtCursor_OneCshIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Csh csh = Factory.createCsh("-", "pwd");
    objDocument.append(csh, cursor);

    assertEquals(csh, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneParmToEmptyObjDocumentAtCursor_OneParmIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Parm parm = Factory.createParm("u");
    parm.appendParameterValue("1.0000");
    parm.appendParameterValue("4.4444");
    parm.appendParameterValue("-9.22");
    parm.appendParameterValue("111.9876");
    objDocument.append(parm, cursor);

    assertEquals(parm, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneTrimToEmptyObjDocumentAtCursor_OneTrimIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Trim trim = Factory.createTrim();
    trim.appendTrimmingCurve2DReference(Factory.createCurve2DReference("1.4567", "-99.5463", "1"));
    trim.appendTrimmingCurve2DReference(Factory.createCurve2DReference("1.111", "2.22", "5"));
    trim.appendTrimmingCurve2DReference(Factory.createCurve2DReference("6.789", "0.543", "77"));
    objDocument.append(trim, cursor);

    assertEquals(trim, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneHoleToEmptyObjDocumentAtCursor_OneHoleIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Hole hole = Factory.createHole();
    hole.appendTrimmingCurve2DReference(Factory.createCurve2DReference("3.333", "4.444", "22"));
    hole.appendTrimmingCurve2DReference(Factory.createCurve2DReference("-1.111", "-2.232", "3"));
    hole.appendTrimmingCurve2DReference(Factory.createCurve2DReference("4.78", "99.99", "11"));
    objDocument.append(hole, cursor);

    assertEquals(hole, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSpecialCurveToEmptyObjDocumentAtCursor_OneSpecialCurveIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    SpecialCurve scrv = Factory.createSpecialCurve();
    scrv.appendSpecialCurve2DReference(Factory.createCurve2DReference("3.333", "4.444", "22"));
    scrv.appendSpecialCurve2DReference(Factory.createCurve2DReference("3.333", "4.444", "22"));
    objDocument.append(scrv, cursor);

    assertEquals(scrv, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSpecialPointToEmptyObjDocumentAtCursor_OneSpecialPointIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    SpecialPoint sp = Factory.createSpecialPoint();
    VertexReferenceGroup vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "1"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "300"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "6"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = Factory.createVertexReferenceGroup();
    vertexReferenceGroup
        .addVertexReference(Factory.createVertexReference(VertexReference.Type.PARAMETER, "88"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(sp, cursor);

    assertEquals(sp, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneEndToEmptyObjDocumentAtCursor_OneEndIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    End end = Factory.createEnd();
    objDocument.append(end, cursor);

    assertEquals(end, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneConnectToEmptyObjDocumentAtCursor_OneConnectIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Curve2DReference curve2dReferenceForSurface3 =
        Factory.createCurve2DReference("1.111", "2.222", "1");
    Curve2DReference curve2dReferenceForSurface4 =
        Factory.createCurve2DReference("3.333", "4.444", "2");
    Connect con =
        Factory.createConnect("3", curve2dReferenceForSurface3, "4", curve2dReferenceForSurface4);
    objDocument.append(con, cursor);

    assertEquals(con, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneGroupNameToEmptyObjDocumentAtCursor_OneGroupNameIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    GroupNameList g = Factory.createGroupName();
    g.appendGroupName("cube");
    g.appendGroupName("top");
    objDocument.append(g, cursor);

    assertEquals(g, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSmoothingGroupToEmptyObjDocumentAtCursor_OneSmoothingGroupIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    SmoothingGroup s = Factory.createSmoothingGroup("3");
    objDocument.append(s, cursor);

    assertEquals(s, objDocument.peek(cursor));
  }

  @Test
  public void Document_addMergingGroupToEmptyObjDocumentAtCursor_OneMergingGroupIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    MergingGroup mg = Factory.createMergingGroup("3", "0.6");
    objDocument.append(mg, cursor);

    assertEquals(mg, objDocument.peek(cursor));
  }

  @Test
  public void Document_addObjectNameToEmptyObjDocumentAtCursor_OneObjectNameIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    ObjectName o = Factory.createObjectName("test_cube");
    objDocument.append(o, cursor);

    assertEquals(o, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneBevelToEmptyObjDocumentAtCursor_OneBevelIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Bevel bevel = Factory.createBevel("on");
    objDocument.append(bevel, cursor);

    assertEquals(bevel, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneColorInterpolationToEmptyObjDocumentAtCursor_OneColorInterpolationIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());
    
    ColorInterpolation c_interp = Factory.createColorInterpolation("ON");
    objDocument.append(c_interp, cursor);

    assertEquals(c_interp, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneDissolveInterpolationToEmptyObjDocumentAtCursor_OneDissolveInterpolationIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    DissolveInterpolation d_interp = Factory.createDissolveInterpolation("Off");
    objDocument.append(d_interp, cursor);

    assertEquals(d_interp, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneLevelOfDetailToEmptyObjDocumentAtCursor_OneLevelOfDetailIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    LevelOfDetail lod = Factory.createLevelOfDetail("55");
    objDocument.append(lod, cursor);

    assertEquals(lod, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneMapLibToEmptyObjDocumentAtCursor_OneMapLibIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    // TODO I have no idea if there is a file extension limitation or not. It does not say in the
    // specification.
    MapLib maplib = Factory.createMapLib();
    maplib.appendMapLibFileName("library1.textures");
    maplib.appendMapLibFileName("library2.textures");
    objDocument.append(maplib, cursor);

    assertEquals(maplib, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneUseMapToEmptyObjDocumentAtCursor_OneUseMapIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    UseMap usemap = Factory.createUseMap("test_map_name");
    objDocument.append(usemap, cursor);

    assertEquals(usemap, cursor);
  }

  @Test
  public void Document_addOneUseMaterialToEmptyObjDocumentAtCursor_OneUseMaterialIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    UseMaterial usemtl = Factory.createUseMaterial("test_material");
    objDocument.append(usemtl, cursor);

    assertEquals(usemtl, objDocument.peek(cursor));
  }


  @Test
  public void Document_addOneMaterialLibToEmptyObjDocumentAtCursor_OneMaterialLibIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    // TODO I have no idea if there is a file extension limitation or not. It does not say in the
    // specification.
    MaterialLib mtllib = Factory.createMaterialLib();
    mtllib.appendMaterialLibFileName("library1.materials");
    mtllib.appendMaterialLibFileName("library2.materials");
    objDocument.append(mtllib, cursor);

    assertEquals(mtllib, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneShadowObjectToEmptyObjDocumentAtCursor_OneShadowObjectIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    ShadowObject shadow_obj = Factory.createShadowObject("shadow.obj");
    objDocument.append(shadow_obj, cursor);

    assertEquals(shadow_obj, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneRayTracingObjectToEmptyObjDocumentAtCursor_OneRayTracingObjectIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    RayTracingObject trace_obj = Factory.createRayTracingObject("ray_tracing.obj");
    objDocument.append(trace_obj, cursor);

    assertEquals(trace_obj, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCparmCurveApproxToEmptyObjDocumentAtCursor_OneCparmCurveApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CparmCurveApprox ctech = Factory.createCparmCurveApprox("2.3333");
    objDocument.append(ctech, cursor);

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCspaceCurveApproxToEmptyObjDocumentAtCursor_OneCspaceCurveApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CspaceCurveApprox ctech = Factory.createCspaceCurveApprox("1.56");
    objDocument.append(ctech, cursor);

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurvCurveApproxToEmptyObjDocumentAtCursor_OneCurvCurveApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CurvCurveApprox ctech = Factory.createCurvCurveAprox("1.1876", "93.45");
    objDocument.append(ctech, cursor);

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCparmaSurfaceApproxToEmptyObjDocumentAtCursor_OneCparmaSurfaceApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CparmaSurfaceApprox stech = Factory.createCparmaSurfaceApprox("1.234", "3.333");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCparmbSurfaceApproxToEmptyObjDocumentAtCursor_OneCparmbSurfaceApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CparmbSurfaceApprox stech = Factory.createCparmbSurfaceApprox("5.678");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCspaceSurfaceApproxToEmptyObjDocumentAtCursor_OneCspaceSurfaceApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CspaceSurfaceApprox stech = Factory.createCspaceSurfaceApprox("1.11");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurvSurfaceApproxToEmptyObjDocumentAtCursor_OneCurvSurfaceApproxIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    CurvSurfaceApprox stech = Factory.createCurvSurfaceApprox("1.5678", "90.0");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addCommentedGeoVertexToEmptyObjDocumentAtCursor_OneCommentedGeoVertexIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    GeoVertex v = Factory.createGeoVertex("1.0000", "2.0000", "3.0000", "4.0000");
    if (v.canComment()) {
      Comment comment = Factory.createComment(" This is a comment.");
      v.setComment(comment);
    }
    objDocument.append(v, cursor);

    assertTrue(v.canComment());
    assertEquals(v, objDocument.peek(cursor));
    assertEquals(v.getComment(), ((GeoVertex) objDocument.peek(cursor)).getComment());
  }

  @Test
  public void Document_addCommmentedBlankToEmptyObjDocumentAtCursor_OneCommentedBlankIsAddedATCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Blank blank = Factory.createBlank();
    if (blank.canComment()) {
      Comment comment = Factory.createComment(" This is a comment on a blank line.");
      blank.setComment(comment);
    }
    objDocument.append(blank, cursor);

    assertTrue(blank.canComment());
    assertEquals(blank, objDocument.peek(cursor));
    assertEquals(blank.getComment(), ((Blank) objDocument.peek(cursor)).getComment());
  }

  @Test
  public void Document_addOneBlankToEmptyObjDocumentAtCursor_OneBlankIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    Blank blank = Factory.createBlank();
    objDocument.append(blank, cursor);

    assertEquals(blank, objDocument.peek(cursor));
  }

  @Test
  public void Document_addUnknownToEmptyObjDocumentAtCursor_OneUnknownIsAddedAtCursor() {
    Document objDocument = Factory.createObjDocument();
    Cursor cursor = objDocument.getCursor();
    cursor.setTo(objDocument.getReadOnlyEofCursor());

    List<String> tokens = Arrays.asList("some", "unknown", "tokens");
    Unknown unknown = Factory.createUnknown(tokens);
    objDocument.append(unknown, cursor);

    assertEquals(unknown, objDocument.peek(cursor));
  }
}

