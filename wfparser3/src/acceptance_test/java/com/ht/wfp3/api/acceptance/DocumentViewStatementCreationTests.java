package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import com.ht.wfp3.api.document.Cursor;
import com.ht.wfp3.api.document.Document;
import com.ht.wfp3.api.document.DocumentFactory;
import com.ht.wfp3.api.statement.BasisMatrix;
import com.ht.wfp3.api.statement.Bevel;
import com.ht.wfp3.api.statement.Blank;
import com.ht.wfp3.api.statement.Call;
import com.ht.wfp3.api.statement.ColorInterpolation;
import com.ht.wfp3.api.statement.Comment;
import com.ht.wfp3.api.statement.Connect;
import com.ht.wfp3.api.statement.CparmCurveApprox;
import com.ht.wfp3.api.statement.CparmaSurfaceApprox;
import com.ht.wfp3.api.statement.CparmbSurfaceApprox;
import com.ht.wfp3.api.statement.Csh;
import com.ht.wfp3.api.statement.CspaceCurveApprox;
import com.ht.wfp3.api.statement.CspaceSurfaceApprox;
import com.ht.wfp3.api.statement.CurvCurveApprox;
import com.ht.wfp3.api.statement.CurvSurfaceApprox;
import com.ht.wfp3.api.statement.Curve;
import com.ht.wfp3.api.statement.Curve2D;
import com.ht.wfp3.api.statement.Curve2DReference;
import com.ht.wfp3.api.statement.CurveOrSurface;
import com.ht.wfp3.api.statement.Degree;
import com.ht.wfp3.api.statement.DissolveInterpolation;
import com.ht.wfp3.api.statement.End;
import com.ht.wfp3.api.statement.Face;
import com.ht.wfp3.api.statement.GeoVertex;
import com.ht.wfp3.api.statement.GroupNameList;
import com.ht.wfp3.api.statement.Hole;
import com.ht.wfp3.api.statement.LevelOfDetail;
import com.ht.wfp3.api.statement.Line;
import com.ht.wfp3.api.statement.MapLib;
import com.ht.wfp3.api.statement.MaterialLib;
import com.ht.wfp3.api.statement.MergingGroup;
import com.ht.wfp3.api.statement.NormalVertex;
import com.ht.wfp3.api.statement.ObjectName;
import com.ht.wfp3.api.statement.ParamVertex;
import com.ht.wfp3.api.statement.Parm;
import com.ht.wfp3.api.statement.Point;
import com.ht.wfp3.api.statement.RayTracingObject;
import com.ht.wfp3.api.statement.ShadowObject;
import com.ht.wfp3.api.statement.SmoothingGroup;
import com.ht.wfp3.api.statement.SpecialCurve;
import com.ht.wfp3.api.statement.SpecialPoint;
import com.ht.wfp3.api.statement.Statement;
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.StepSize;
import com.ht.wfp3.api.statement.Surface;
import com.ht.wfp3.api.statement.TexVertex;
import com.ht.wfp3.api.statement.Trim;
import com.ht.wfp3.api.statement.Unknown;
import com.ht.wfp3.api.statement.UseMap;
import com.ht.wfp3.api.statement.UseMaterial;
import com.ht.wfp3.api.statement.VertexReference;
import com.ht.wfp3.api.statement.VertexReferenceGroup;

import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class DocumentViewStatementCreationTests {

  private Document objDocument;
  private StatementFactory statementFactory;

  private Document createObjDocument() {
    DocumentFactory documentFactory = Document.getDocumentFactory();
    Document objDocument = documentFactory.createObjDocument();
    return objDocument;
  }

  private StatementFactory createStatementFactory() {
    return Statement.createStatementFactory();
  }
  
  @Before
  public void setup() {
    objDocument = createObjDocument();
    statementFactory = createStatementFactory();
  }

  @Test
  public void Document_createEmptyObjDocument_EmptyDocumentIsCreated() {
    assertNotNull(objDocument);
  }

  @Test
  public void Document_addOneGeometricVertexToEmptyObjDocumentAtCursor_OneGeometricVertexIsAddedAtCursor() {
    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    GeoVertex geoVertex = statementFactory.createGeoVertex("1.000", "2.000", "3.000", "4.000");
    objDocument.append(geoVertex, cursor);
    cursor.previousLine();

    assertEquals(geoVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneTextureVertexToEmptyObjDocumentAtCursor_OneTextureVertexIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    TexVertex texVertex = statementFactory.createTexVertex("3.3", "2.2", "1.1");
    objDocument.append(texVertex, cursor);
    cursor.previousLine();

    assertEquals(texVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneNormalVertexToEmptyObjDocumentAtCursor_OneNormalVertexIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    NormalVertex normalVertex = statementFactory.createNormalVertex("9.9", "8.8", "7.7");
    objDocument.append(normalVertex, cursor);
    cursor.previousLine();

    assertEquals(normalVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneParamVertexToEmptyObjDocumentAtCursor_OneParamVertexIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    ParamVertex paramVertex = statementFactory.createParamVertex("3.13", "3.31", "1.33");
    objDocument.append(paramVertex, cursor);
    cursor.previousLine();

    assertEquals(paramVertex, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOnePointToEmptyObjDocumentAtCursor_OnePointIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Point point = statementFactory.createPoint();
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    point.appendReferenceNumbers(vertexReferenceGroup);
    objDocument.append(point, cursor);
    cursor.previousLine();

    assertEquals(point, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneLineToEmptyObjDocumentAtCursor_OneLineIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Line line = statementFactory.createLine();
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "1"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "2"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "3"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "4"));
    line.appendReferenceNumbers(vertexReferenceGroup);
    objDocument.append(line, cursor);
    cursor.previousLine();

    assertEquals(line, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneFaceToEmptyObjDocumentAtCursor_OneFaceIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Face face = statementFactory.createFace();
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "1"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "1"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "2"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "2"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "3"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "3"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "4"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "4"));
    face.appendReferenceNumbers(vertexReferenceGroup);
    objDocument.append(face, cursor);
    cursor.previousLine();

    assertEquals(face, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCSTypeToEmptyObjDocumentAtCursor_OneCSTypeIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CurveOrSurface cstype =
        statementFactory.createCurveOrSurfaceType("rat", CurveOrSurface.Type.BMATRIX);
    objDocument.append(cstype, cursor);
    cursor.previousLine();

    assertEquals(cstype, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneDegreeToEmptyObjDocumentAtCursor_OneDegreeIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Degree deg = statementFactory.createDegree("5", "6");
    objDocument.append(deg, cursor);
    cursor.previousLine();

    assertEquals(deg, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneBasisMatrixToEmptyObjDocumentAtCursor_OneBasisMatrixIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    String[] flattenedUAxisBasisMatrix =
        { "1.234", "-1.234", "4.321", "-4.321", "9.876", "-9.876" };
    BasisMatrix bmat = statementFactory.createBasisMatrix(BasisMatrix.Axis.U,
        Arrays.asList(flattenedUAxisBasisMatrix));
    objDocument.append(bmat, cursor);
    cursor.previousLine();

    assertEquals(bmat, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneStepToEmptyObjDocumentAtCursor_OneStepIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    StepSize step = statementFactory.createStepSize("7", "33");
    objDocument.append(step, cursor);
    cursor.previousLine();

    assertEquals(step, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurveToEmptyObjDocumentAtCursor_OneCurveIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Curve curv = statementFactory.createCurve("1.23456", "9.5321");
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    curv.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(curv, cursor);
    cursor.previousLine();

    assertEquals(curv, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurve2DToEmptyObjDocumentAtCursor_OneCurve2DIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Curve2D curv2 = statementFactory.createCurve2D();
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "1"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "2"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "3"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "4"));
    curv2.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(curv2, cursor);
    cursor.previousLine();

    assertEquals(curv2, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSurfaceToEmptyObjDocumentAtCursor_OneSurfaceIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Surface surf = statementFactory.createSurface("1.2345", "-5.4321", "9.8765", "-5.6789");
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "1"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "1"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "1"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "2"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "2"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "2"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "3"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "3"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "3"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.GEOMETRIC, "4"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.TEXTURE, "4"));
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.NORMAL, "4"));
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(surf, cursor);
    cursor.previousLine();

    assertEquals(surf, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCallToEmptyObjDocumentAtCursor_OneCallIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Call call = statementFactory.createCall("filename.obj");
    call.appendArgument("3");
    call.appendArgument("5");
    call.appendArgument("78");
    objDocument.append(call, cursor);
    cursor.previousLine();

    assertEquals(call, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCshToEmptyObjDocumentAtCursor_OneCshIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Csh csh = statementFactory.createCsh("-", "pwd");
    objDocument.append(csh, cursor);
    cursor.previousLine();

    assertEquals(csh, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneParmToEmptyObjDocumentAtCursor_OneParmIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Parm parm = statementFactory.createParm("u");
    parm.appendParameterValue("1.0000");
    parm.appendParameterValue("4.4444");
    parm.appendParameterValue("-9.22");
    parm.appendParameterValue("111.9876");
    objDocument.append(parm, cursor);
    cursor.previousLine();

    assertEquals(parm, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneTrimToEmptyObjDocumentAtCursor_OneTrimIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Trim trim = statementFactory.createTrim();
    trim.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("1.4567", "-99.5463", "1"));
    trim.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("1.111", "2.22", "5"));
    trim.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("6.789", "0.543", "77"));
    objDocument.append(trim, cursor);
    cursor.previousLine();

    assertEquals(trim, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneHoleToEmptyObjDocumentAtCursor_OneHoleIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Hole hole = statementFactory.createHole();
    hole.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("3.333", "4.444", "22"));
    hole.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("-1.111", "-2.232", "3"));
    hole.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("4.78", "99.99", "11"));
    objDocument.append(hole, cursor);
    cursor.previousLine();

    assertEquals(hole, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSpecialCurveToEmptyObjDocumentAtCursor_OneSpecialCurveIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    SpecialCurve scrv = statementFactory.createSpecialCurve();
    scrv.appendSpecialCurve2DReference(
        statementFactory.createCurve2DReference("3.333", "4.444", "22"));
    scrv.appendSpecialCurve2DReference(
        statementFactory.createCurve2DReference("3.333", "4.444", "22"));
    objDocument.append(scrv, cursor);
    cursor.previousLine();

    assertEquals(scrv, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSpecialPointToEmptyObjDocumentAtCursor_OneSpecialPointIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    SpecialPoint sp = statementFactory.createSpecialPoint();
    VertexReferenceGroup vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "1"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "300"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "6"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    vertexReferenceGroup = statementFactory.createVertexReferenceGroup();
    vertexReferenceGroup.addVertexReference(
        statementFactory.createVertexReference(VertexReference.Type.PARAMETER, "88"));
    sp.appendSpecialPointVertexReferenceGroup(vertexReferenceGroup);
    objDocument.append(sp, cursor);
    cursor.previousLine();

    assertEquals(sp, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneEndToEmptyObjDocumentAtCursor_OneEndIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    End end = statementFactory.createEnd();
    objDocument.append(end, cursor);
    cursor.previousLine();

    assertEquals(end, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneConnectToEmptyObjDocumentAtCursor_OneConnectIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Curve2DReference curve2dReferenceForSurface3 =
        statementFactory.createCurve2DReference("1.111", "2.222", "1");
    Curve2DReference curve2dReferenceForSurface4 =
        statementFactory.createCurve2DReference("3.333", "4.444", "2");
    Connect con = statementFactory.createConnect("3", curve2dReferenceForSurface3, "4",
        curve2dReferenceForSurface4);
    objDocument.append(con, cursor);
    cursor.previousLine();

    assertEquals(con, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneGroupNameToEmptyObjDocumentAtCursor_OneGroupNameIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    GroupNameList g = statementFactory.createGroupName();
    g.appendGroupName("cube");
    g.appendGroupName("top");
    objDocument.append(g, cursor);
    cursor.previousLine();

    assertEquals(g, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneSmoothingGroupToEmptyObjDocumentAtCursor_OneSmoothingGroupIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    SmoothingGroup s = statementFactory.createSmoothingGroup("3");
    objDocument.append(s, cursor);
    cursor.previousLine();

    assertEquals(s, objDocument.peek(cursor));
  }

  @Test
  public void Document_addMergingGroupToEmptyObjDocumentAtCursor_OneMergingGroupIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    MergingGroup mg = statementFactory.createMergingGroup("3", "0.6");
    objDocument.append(mg, cursor);
    cursor.previousLine();

    assertEquals(mg, objDocument.peek(cursor));
  }

  @Test
  public void Document_addObjectNameToEmptyObjDocumentAtCursor_OneObjectNameIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    ObjectName o = statementFactory.createObjectName("test_cube");
    objDocument.append(o, cursor);
    cursor.previousLine();

    assertEquals(o, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneBevelToEmptyObjDocumentAtCursor_OneBevelIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Bevel bevel = statementFactory.createBevel("on");
    objDocument.append(bevel, cursor);
    cursor.previousLine();

    assertEquals(bevel, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneColorInterpolationToEmptyObjDocumentAtCursor_OneColorInterpolationIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    ColorInterpolation c_interp = statementFactory.createColorInterpolation("ON");
    objDocument.append(c_interp, cursor);
    cursor.previousLine();

    assertEquals(c_interp, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneDissolveInterpolationToEmptyObjDocumentAtCursor_OneDissolveInterpolationIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    DissolveInterpolation d_interp = statementFactory.createDissolveInterpolation("Off");
    objDocument.append(d_interp, cursor);
    cursor.previousLine();

    assertEquals(d_interp, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneLevelOfDetailToEmptyObjDocumentAtCursor_OneLevelOfDetailIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    LevelOfDetail lod = statementFactory.createLevelOfDetail("55");
    objDocument.append(lod, cursor);
    cursor.previousLine();

    assertEquals(lod, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneMapLibToEmptyObjDocumentAtCursor_OneMapLibIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    // TODO I have no idea if there is a file extension limitation or not. It does not say in the
    // specification.
    MapLib maplib = statementFactory.createMapLib();
    maplib.appendMapLibFileName("library1.textures");
    maplib.appendMapLibFileName("library2.textures");
    objDocument.append(maplib, cursor);
    cursor.previousLine();

    assertEquals(maplib, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneUseMapToEmptyObjDocumentAtCursor_OneUseMapIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    UseMap usemap = statementFactory.createUseMap("test_map_name");
    objDocument.append(usemap, cursor);
    cursor.previousLine();

    assertEquals(usemap, cursor);
  }

  @Test
  public void Document_addOneUseMaterialToEmptyObjDocumentAtCursor_OneUseMaterialIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    UseMaterial usemtl = statementFactory.createUseMaterial("test_material");
    objDocument.append(usemtl, cursor);
    cursor.previousLine();

    assertEquals(usemtl, objDocument.peek(cursor));
  }


  @Test
  public void Document_addOneMaterialLibToEmptyObjDocumentAtCursor_OneMaterialLibIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    // TODO I have no idea if there is a file extension limitation or not. It does not say in the
    // specification.
    MaterialLib mtllib = statementFactory.createMaterialLib();
    mtllib.appendMaterialLibFileName("library1.materials");
    mtllib.appendMaterialLibFileName("library2.materials");
    objDocument.append(mtllib, cursor);
    cursor.previousLine();

    assertEquals(mtllib, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneShadowObjectToEmptyObjDocumentAtCursor_OneShadowObjectIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    ShadowObject shadow_obj = statementFactory.createShadowObject("shadow.obj");
    objDocument.append(shadow_obj, cursor);
    cursor.previousLine();

    assertEquals(shadow_obj, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneRayTracingObjectToEmptyObjDocumentAtCursor_OneRayTracingObjectIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    RayTracingObject trace_obj = statementFactory.createRayTracingObject("ray_tracing.obj");
    objDocument.append(trace_obj, cursor);
    cursor.previousLine();

    assertEquals(trace_obj, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCparmCurveApproxToEmptyObjDocumentAtCursor_OneCparmCurveApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CparmCurveApprox ctech = statementFactory.createCparmCurveApprox("2.3333");
    objDocument.append(ctech, cursor);
    cursor.previousLine();

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCspaceCurveApproxToEmptyObjDocumentAtCursor_OneCspaceCurveApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CspaceCurveApprox ctech = statementFactory.createCspaceCurveApprox("1.56");
    objDocument.append(ctech, cursor);
    cursor.previousLine();

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurvCurveApproxToEmptyObjDocumentAtCursor_OneCurvCurveApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CurvCurveApprox ctech = statementFactory.createCurvCurveAprox("1.1876", "93.45");
    objDocument.append(ctech, cursor);
    cursor.previousLine();

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCparmaSurfaceApproxToEmptyObjDocumentAtCursor_OneCparmaSurfaceApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CparmaSurfaceApprox stech = statementFactory.createCparmaSurfaceApprox("1.234", "3.333");
    objDocument.append(stech, cursor);
    cursor.previousLine();

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCparmbSurfaceApproxToEmptyObjDocumentAtCursor_OneCparmbSurfaceApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CparmbSurfaceApprox stech = statementFactory.createCparmbSurfaceApprox("5.678");
    objDocument.append(stech, cursor);
    cursor.previousLine();

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCspaceSurfaceApproxToEmptyObjDocumentAtCursor_OneCspaceSurfaceApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CspaceSurfaceApprox stech = statementFactory.createCspaceSurfaceApprox("1.11");
    objDocument.append(stech, cursor);
    cursor.previousLine();

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCurvSurfaceApproxToEmptyObjDocumentAtCursor_OneCurvSurfaceApproxIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    CurvSurfaceApprox stech = statementFactory.createCurvSurfaceApprox("1.5678", "90.0");
    objDocument.append(stech, cursor);
    cursor.previousLine();

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  public void Document_addCommentedGeoVertexToEmptyObjDocumentAtCursor_OneCommentedGeoVertexIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    GeoVertex v = statementFactory.createGeoVertex("1.0000", "2.0000", "3.0000", "4.0000");
    if (v.canComment()) {
      Comment comment = statementFactory.createComment(" This is a comment.");
      v.setComment(comment);
    }
    objDocument.append(v, cursor);
    cursor.previousLine();

    assertTrue(v.canComment());
    assertEquals(v, objDocument.peek(cursor));
    assertEquals(v.getComment(), ((GeoVertex) objDocument.peek(cursor)).getComment());
  }

  @Test
  public void Document_addCommmentedBlankToEmptyObjDocumentAtCursor_OneCommentedBlankIsAddedATCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Blank blank = statementFactory.createBlank();
    if (blank.canComment()) {
      Comment comment = statementFactory.createComment(" This is a comment on a blank line.");
      blank.setComment(comment);
    }
    objDocument.append(blank, cursor);
    cursor.previousLine();

    assertTrue(blank.canComment());
    assertEquals(blank, objDocument.peek(cursor));
    assertEquals(blank.getComment(), ((Blank) objDocument.peek(cursor)).getComment());
  }

  @Test
  public void Document_addOneBlankToEmptyObjDocumentAtCursor_OneBlankIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    Blank blank = statementFactory.createBlank();
    objDocument.append(blank, cursor);
    cursor.previousLine();

    assertEquals(blank, objDocument.peek(cursor));
  }

  @Test
  public void Document_addUnknownToEmptyObjDocumentAtCursor_OneUnknownIsAddedAtCursor() {

    Cursor cursor = objDocument.createCursor();
    cursor.setToEof();

    List<String> tokens = Arrays.asList("some", "unknown", "tokens");
    Unknown unknown = statementFactory.createUnknown(tokens);
    objDocument.append(unknown, cursor);
    cursor.previousLine();

    assertEquals(unknown, objDocument.peek(cursor));
  }
}

