package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import java.util.Arrays;
import java.util.List;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.ht.wfp3.api.document.Cursor;
import com.ht.wfp3.api.document.Document;
import com.ht.wfp3.api.document.DocumentFactory;
import com.ht.wfp3.api.document.EmptyDocumentException;
import com.ht.wfp3.api.document.VisibleDocumentImp;
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
import com.ht.wfp3.api.statement.CurveOrSurfaceType;
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
import com.ht.wfp3.api.statement.MatrixBuilder;
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
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.StepSize;
import com.ht.wfp3.api.statement.Surface;
import com.ht.wfp3.api.statement.TexVertex;
import com.ht.wfp3.api.statement.Trim;
import com.ht.wfp3.api.statement.Unknown;
import com.ht.wfp3.api.statement.UnknownStatementStub;
import com.ht.wfp3.api.statement.UseMap;
import com.ht.wfp3.api.statement.UseMaterial;
import com.ht.wfp3.api.statement.VertexReferenceGroupBuilder;

public class DocumentViewStatementCreationAcceptanceTests {

  // TODO all error scenarios. Work on them as you work on success scenarios.

  private StatementFactory statementFactory;

  private VisibleDocumentImp objDocument;

  private Cursor cursor;

  private VisibleDocumentImp createObjDocument() {
    VisibleDocumentImp objDocument = new VisibleDocumentImp();
    return objDocument;
  }

  private StatementFactory createStatementFactory() {
    return StatementFactory.createStatementFactory();
  }

  @Before
  public void setup() {
    objDocument = createObjDocument();
    cursor = objDocument.createCursor();
    cursor.toEof();
    statementFactory = createStatementFactory();
  }

  @Test
  public void Document_createEmptyObjDocument_EmptyDocumentIsCreated() {
    DocumentFactory documentFactory = DocumentFactory.createDocumentFactory();
    Document objDocumentToTest = documentFactory.createObjDocument();
    assertNotNull(objDocumentToTest);
  }

  @Test
  public void Document_createCursor_defaultCursorIsCreated() {
    Cursor cursor = objDocument.createCursor();

    assertEquals(Integer.valueOf(1), cursor.getLineNumber());
    assertEquals(Integer.valueOf(0), objDocument.getNumberOfLines());
  }

  @Test
  public void Document_createStatementFactory_statementFactoryIsCreated() {
    assertNotNull(statementFactory);
  }

  // Error scenarios.

  @Test(expected = NullPointerException.class)
  public void Document_appendApiGuardIsPassedANullCursor_nullPointerExceptionIsThrown() {
    GeoVertex geoVertex = statementFactory.createGeoVertex("5.555", "5.555", "5.555", "5.555");
    objDocument.guardAppendApis(geoVertex, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Document_appendApiGuardIsPassedCursorFromAnotherDocument_illegalArgumentExceptionIsThrown() {
    VisibleDocumentImp otherDocument = new VisibleDocumentImp();
    Cursor otherCursor = otherDocument.createCursor();
    GeoVertex geoVertex = statementFactory.createGeoVertex("5.555", "5.555", "5.555", "5.555");

    objDocument.guardAppendApis(geoVertex, otherCursor);
  }

  @Test(expected = NullPointerException.class)
  public void Document_appendApiGuardIsPassedANullStatement_nullPointerExceptionIsThrown() {
    objDocument.guardAppendApis(null, cursor);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Document_appendApiGuardIsPassedAnUnknownStatement_illegalArgumentExceptionIsThrown() {
    UnknownStatementStub unknownStatement = new UnknownStatementStub();

    objDocument.guardAppendApis(unknownStatement, cursor);
  }

  @Test(expected = NullPointerException.class)
  public void Document_peekAtDocumentLineWithNullCursor_nullPointerExceptionIsThrown()
      throws Exception {
    objDocument.peek(null);
  }

  @Test(expected = EmptyDocumentException.class)
  public void Document_peekInEmptyDocument_emptyDocumentExceptionIsThrown() throws Exception {
    objDocument.peek(cursor);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Document_peekUsingCursorFromAnotherDocument_illegalArgumentExceptionIsThrown()
      throws Exception {
    VisibleDocumentImp otherDocument = new VisibleDocumentImp();
    Cursor otherCursor = otherDocument.createCursor();
    otherCursor.toEof();
    GeoVertex geoVertex = statementFactory.createGeoVertex("5.555", "5.555", "5.555", "5.555");
    objDocument.append(geoVertex, cursor);

    objDocument.peek(otherCursor);
  }

  // Valid scenarios.

  @Test
  public void Document_addOneGeometricVertexToEmptyObjDocumentAtCursor_OneGeometricVertexIsAddedAtCursor()
      throws Exception {
    GeoVertex geoVertex = statementFactory.createGeoVertex("1.000", "2.000", "3.000", "4.000");
    objDocument.append(geoVertex, cursor);

    assertEquals(geoVertex, objDocument.peek(cursor));
    assertEquals(Integer.valueOf(1), objDocument.getNumberOfLines());
  }

  @Test
  public void Document_addOneTextureVertexToEmptyObjDocumentAtCursor_OneTextureVertexIsAddedAtCursor()
      throws Exception {
    TexVertex texVertex = statementFactory.createTexVertex("3.3", "2.2", "1.1");
    objDocument.append(texVertex, cursor);

    assertEquals(texVertex, objDocument.peek(cursor));
    assertEquals(Integer.valueOf(1), objDocument.getNumberOfLines());
  }

  @Test
  public void Document_addOneNormalVertexToEmptyObjDocumentAtCursor_OneNormalVertexIsAddedAtCursor()
      throws Exception {
    NormalVertex normalVertex = statementFactory.createNormalVertex("9.9", "8.8", "7.7");
    objDocument.append(normalVertex, cursor);

    assertEquals(normalVertex, objDocument.peek(cursor));
    assertEquals(Integer.valueOf(1), objDocument.getNumberOfLines());
  }

  @Test
  public void Document_addOneParamVertexToEmptyObjDocumentAtCursor_OneParamVertexIsAddedAtCursor()
      throws Exception {
    ParamVertex paramVertex = statementFactory.createParamVertex("3.13", "3.31", "1.33");
    objDocument.append(paramVertex, cursor);

    assertEquals(paramVertex, objDocument.peek(cursor));
    assertEquals(Integer.valueOf(1), objDocument.getNumberOfLines());
  }

  @Test
  public void Document_addOnePointToEmptyObjDocumentAtCursor_OnePointIsAddedAtCursor()
      throws Exception {
    Point point = statementFactory.createPoint();
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();
    point.appendReferenceNumbers(vertexReferenceGroupBuilder.clear().geoVertexRef(1).build());
    point.appendReferenceNumbers(vertexReferenceGroupBuilder.clear().geoVertexRef(2).build());
    point.appendReferenceNumbers(vertexReferenceGroupBuilder.clear().geoVertexRef(3).build());
    point.appendReferenceNumbers(vertexReferenceGroupBuilder.clear().geoVertexRef(4).build());

    objDocument.append(point, cursor);

    assertEquals(point, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneLineToEmptyObjDocumentAtCursor_OneLineIsAddedAtCursor()
      throws Exception {
    Line line = statementFactory.createLine();
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();
    line.appendReferenceNumbers(
        vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(1).build());
    line.appendReferenceNumbers(
        vertexReferenceGroupBuilder.clear().geoVertexRef(2).texVertexRef(2).build());
    line.appendReferenceNumbers(
        vertexReferenceGroupBuilder.clear().geoVertexRef(3).texVertexRef(3).build());
    line.appendReferenceNumbers(
        vertexReferenceGroupBuilder.clear().geoVertexRef(4).texVertexRef(4).build());

    objDocument.append(line, cursor);

    assertEquals(line, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneFaceToEmptyObjDocumentAtCursor_OneFaceIsAddedAtCursor()
      throws Exception {
    Face face = statementFactory.createFace();
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();

    vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(1).normalVertexRef(1);
    face.appendReferenceNumbers(vertexReferenceGroupBuilder.build());

    vertexReferenceGroupBuilder.clear().geoVertexRef(2).texVertexRef(2).normalVertexRef(2);
    face.appendReferenceNumbers(vertexReferenceGroupBuilder.build());

    vertexReferenceGroupBuilder.clear().geoVertexRef(3).texVertexRef(3).normalVertexRef(3);
    face.appendReferenceNumbers(vertexReferenceGroupBuilder.build());

    vertexReferenceGroupBuilder.clear().geoVertexRef(4).texVertexRef(4).normalVertexRef(4);
    face.appendReferenceNumbers(vertexReferenceGroupBuilder.build());

    objDocument.append(face, cursor);

    assertEquals(face, objDocument.peek(cursor));
  }

  @Test
  public void Document_addOneCSTypeToEmptyObjDocumentAtCursor_OneCSTypeIsAddedAtCursor()
      throws Exception {
    CurveOrSurfaceType cstype =
        statementFactory.createCurveOrSurface("rat", CurveOrSurfaceType.Key.BMATRIX);
    objDocument.append(cstype, cursor);

    assertEquals(cstype, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneDegreeToEmptyObjDocumentAtCursor_OneDegreeIsAddedAtCursor()
      throws Exception {
    Degree deg = statementFactory.createDegree(5, 6);
    objDocument.append(deg, cursor);

    assertEquals(deg, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneBasisMatrixToEmptyObjDocumentAtCursor_OneBasisMatrixIsAddedAtCursor()
      throws Exception {

    // ROUGH THOUGHTS: Handling matrices is awkward. The idea I have right now is to use a
    // MatrixBuilder class. The MatrixBuilder builds the matrix row by row.
    // The data can be strings, floats, doubles, big decimals or a mixture of these values. They'll
    // be converted to the common internal format (BigDecimal) when the bmat statement is created.

    MatrixBuilder matrixBuilder = statementFactory.createMatrixBuilder();
    matrixBuilder.clear().buildRowByRow() //
        .append("1.234").append("-1.234").append("4.321").endRow() //
        .append("-4.321").append("9.876").append("-9.876").endRow() //
        .append("4.545").append("5.454").append("1.111").endRow();

    BasisMatrix bmat =
        statementFactory.createBasisMatrix(BasisMatrix.Axis.U, matrixBuilder.build());
    objDocument.append(bmat, cursor);

    assertEquals(bmat, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneStepToEmptyObjDocumentAtCursor_OneStepIsAddedAtCursor()
      throws Exception {
    StepSize step = statementFactory.createStepSize("7", "33");
    objDocument.append(step, cursor);

    assertEquals(step, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCurveToEmptyObjDocumentAtCursor_OneCurveIsAddedAtCursor()
      throws Exception {
    Curve curv = statementFactory.createCurve("1.23456", "9.5321");
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();
    curv.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().geoVertexRef(1).build());
    curv.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().geoVertexRef(2).build());
    curv.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().geoVertexRef(3).build());
    curv.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().geoVertexRef(4).build());

    objDocument.append(curv, cursor);

    assertEquals(curv, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCurve2DToEmptyObjDocumentAtCursor_OneCurve2DIsAddedAtCursor()
      throws Exception {
    Curve2D curv2 = statementFactory.createCurve2D();
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();

    curv2.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(1).build());
    curv2.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(2).build());
    curv2.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(3).build());
    curv2.appendControlPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(4).build());

    objDocument.append(curv2, cursor);

    assertEquals(curv2, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneSurfaceToEmptyObjDocumentAtCursor_OneSurfaceIsAddedAtCursor()
      throws Exception {
    Surface surf = statementFactory.createSurface("1.2345", "-5.4321", "9.8765", "-5.6789");
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();

    vertexReferenceGroupBuilder.clear().geoVertexRef(1).texVertexRef(1).normalVertexRef(1);
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroupBuilder.build());

    vertexReferenceGroupBuilder.clear().geoVertexRef(2).texVertexRef(2).normalVertexRef(2);
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroupBuilder.build());

    vertexReferenceGroupBuilder.clear().geoVertexRef(3).texVertexRef(3).normalVertexRef(3);
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroupBuilder.build());

    vertexReferenceGroupBuilder.clear().geoVertexRef(4).texVertexRef(4).normalVertexRef(4);
    surf.appendControlPointVertexReferenceGroup(vertexReferenceGroupBuilder.build());

    objDocument.append(surf, cursor);

    assertEquals(surf, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCallToEmptyObjDocumentAtCursor_OneCallIsAddedAtCursor()
      throws Exception {
    Call call = statementFactory.createCall("filename.obj");
    call.appendArgument("3");
    call.appendArgument("5");
    call.appendArgument("78");
    objDocument.append(call, cursor);

    assertEquals(call, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCshToEmptyObjDocumentAtCursor_OneCshIsAddedAtCursor()
      throws Exception {
    Csh csh = statementFactory.createCsh("-", "pwd");
    objDocument.append(csh, cursor);

    assertEquals(csh, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneParmToEmptyObjDocumentAtCursor_OneParmIsAddedAtCursor()
      throws Exception {
    Parm parm = statementFactory.createParm("u");
    parm.appendParameterValue("1.0000");
    parm.appendParameterValue("4.4444");
    parm.appendParameterValue("-9.22");
    parm.appendParameterValue("111.9876");
    objDocument.append(parm, cursor);

    assertEquals(parm, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneTrimToEmptyObjDocumentAtCursor_OneTrimIsAddedAtCursor()
      throws Exception {
    Trim trim = statementFactory.createTrim();
    trim.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("1.4567", "-99.5463", "1"));
    trim.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("1.111", "2.22", "5"));
    trim.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("6.789", "0.543", "77"));
    objDocument.append(trim, cursor);

    assertEquals(trim, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneHoleToEmptyObjDocumentAtCursor_OneHoleIsAddedAtCursor()
      throws Exception {
    Hole hole = statementFactory.createHole();
    hole.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("3.333", "4.444", "22"));
    hole.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("-1.111", "-2.232", "3"));
    hole.appendTrimmingCurve2DReference(
        statementFactory.createCurve2DReference("4.78", "99.99", "11"));
    objDocument.append(hole, cursor);

    assertEquals(hole, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneSpecialCurveToEmptyObjDocumentAtCursor_OneSpecialCurveIsAddedAtCursor()
      throws Exception {
    SpecialCurve scrv = statementFactory.createSpecialCurve();
    scrv.appendSpecialCurve2DReference(
        statementFactory.createCurve2DReference("3.333", "4.444", "22"));
    scrv.appendSpecialCurve2DReference(
        statementFactory.createCurve2DReference("3.333", "4.444", "22"));
    objDocument.append(scrv, cursor);

    assertEquals(scrv, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneSpecialPointToEmptyObjDocumentAtCursor_OneSpecialPointIsAddedAtCursor()
      throws Exception {
    SpecialPoint sp = statementFactory.createSpecialPoint();
    VertexReferenceGroupBuilder vertexReferenceGroupBuilder =
        statementFactory.createVertexReferenceGroupBuilder();

    sp.appendSpecialPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(1).build());
    sp.appendSpecialPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(300).build());
    sp.appendSpecialPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(6).build());
    sp.appendSpecialPointVertexReferenceGroup(
        vertexReferenceGroupBuilder.clear().paramVertexRef(88).build());

    objDocument.append(sp, cursor);

    assertEquals(sp, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneEndToEmptyObjDocumentAtCursor_OneEndIsAddedAtCursor()
      throws Exception {
    End end = statementFactory.createEnd();
    objDocument.append(end, cursor);

    assertEquals(end, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneConnectToEmptyObjDocumentAtCursor_OneConnectIsAddedAtCursor()
      throws Exception {
    Curve2DReference curve2dReferenceForSurface3 =
        statementFactory.createCurve2DReference("1.111", "2.222", "1");
    Curve2DReference curve2dReferenceForSurface4 =
        statementFactory.createCurve2DReference("3.333", "4.444", "2");
    Connect con = statementFactory.createConnect("3", curve2dReferenceForSurface3, "4",
        curve2dReferenceForSurface4);
    objDocument.append(con, cursor);

    assertEquals(con, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneGroupNameToEmptyObjDocumentAtCursor_OneGroupNameIsAddedAtCursor()
      throws Exception {
    GroupNameList g = statementFactory.createGroupName();
    g.appendGroupName("cube");
    g.appendGroupName("top");
    objDocument.append(g, cursor);

    assertEquals(g, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneSmoothingGroupToEmptyObjDocumentAtCursor_OneSmoothingGroupIsAddedAtCursor()
      throws Exception {
    SmoothingGroup s = statementFactory.createSmoothingGroup("3");
    objDocument.append(s, cursor);

    assertEquals(s, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addMergingGroupToEmptyObjDocumentAtCursor_OneMergingGroupIsAddedAtCursor()
      throws Exception {
    MergingGroup mg = statementFactory.createMergingGroup("3", "0.6");
    objDocument.append(mg, cursor);

    assertEquals(mg, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addObjectNameToEmptyObjDocumentAtCursor_OneObjectNameIsAddedAtCursor()
      throws Exception {
    ObjectName o = statementFactory.createObjectName("test_cube");
    objDocument.append(o, cursor);

    assertEquals(o, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneBevelToEmptyObjDocumentAtCursor_OneBevelIsAddedAtCursor()
      throws Exception {
    Bevel bevel = statementFactory.createBevel("on");
    objDocument.append(bevel, cursor);

    assertEquals(bevel, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneColorInterpolationToEmptyObjDocumentAtCursor_OneColorInterpolationIsAddedAtCursor()
      throws Exception {
    ColorInterpolation c_interp = statementFactory.createColorInterpolation("ON");
    objDocument.append(c_interp, cursor);

    assertEquals(c_interp, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneDissolveInterpolationToEmptyObjDocumentAtCursor_OneDissolveInterpolationIsAddedAtCursor()
      throws Exception {
    DissolveInterpolation d_interp = statementFactory.createDissolveInterpolation("Off");
    objDocument.append(d_interp, cursor);

    assertEquals(d_interp, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneLevelOfDetailToEmptyObjDocumentAtCursor_OneLevelOfDetailIsAddedAtCursor()
      throws Exception {
    LevelOfDetail lod = statementFactory.createLevelOfDetail("55");
    objDocument.append(lod, cursor);

    assertEquals(lod, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneMapLibToEmptyObjDocumentAtCursor_OneMapLibIsAddedAtCursor()
      throws Exception {

    // TODO I have no idea if there is a file extension limitation or not. It does
    // not say in the
    // specification.
    MapLib maplib = statementFactory.createMapLib();
    maplib.appendMapLibFileName("library1.textures");
    maplib.appendMapLibFileName("library2.textures");
    objDocument.append(maplib, cursor);

    assertEquals(maplib, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneUseMapToEmptyObjDocumentAtCursor_OneUseMapIsAddedAtCursor() {
    UseMap usemap = statementFactory.createUseMap("test_map_name");
    objDocument.append(usemap, cursor);

    assertEquals(usemap, cursor);
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneUseMaterialToEmptyObjDocumentAtCursor_OneUseMaterialIsAddedAtCursor()
      throws Exception {
    UseMaterial usemtl = statementFactory.createUseMaterial("test_material");
    objDocument.append(usemtl, cursor);

    assertEquals(usemtl, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneMaterialLibToEmptyObjDocumentAtCursor_OneMaterialLibIsAddedAtCursor()
      throws Exception {

    // TODO I have no idea if there is a file extension limitation or not. It does
    // not say in the
    // specification.
    MaterialLib mtllib = statementFactory.createMaterialLib();
    mtllib.appendMaterialLibFileName("library1.materials");
    mtllib.appendMaterialLibFileName("library2.materials");
    objDocument.append(mtllib, cursor);

    assertEquals(mtllib, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneShadowObjectToEmptyObjDocumentAtCursor_OneShadowObjectIsAddedAtCursor()
      throws Exception {
    ShadowObject shadow_obj = statementFactory.createShadowObject("shadow.obj");
    objDocument.append(shadow_obj, cursor);

    assertEquals(shadow_obj, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneRayTracingObjectToEmptyObjDocumentAtCursor_OneRayTracingObjectIsAddedAtCursor()
      throws Exception {
    RayTracingObject trace_obj = statementFactory.createRayTracingObject("ray_tracing.obj");
    objDocument.append(trace_obj, cursor);

    assertEquals(trace_obj, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCparmCurveApproxToEmptyObjDocumentAtCursor_OneCparmCurveApproxIsAddedAtCursor()
      throws Exception {
    CparmCurveApprox ctech = statementFactory.createCparmCurveApprox("2.3333");
    objDocument.append(ctech, cursor);

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCspaceCurveApproxToEmptyObjDocumentAtCursor_OneCspaceCurveApproxIsAddedAtCursor()
      throws Exception {
    CspaceCurveApprox ctech = statementFactory.createCspaceCurveApprox("1.56");
    objDocument.append(ctech, cursor);

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCurvCurveApproxToEmptyObjDocumentAtCursor_OneCurvCurveApproxIsAddedAtCursor()
      throws Exception {
    CurvCurveApprox ctech = statementFactory.createCurvCurveAprox("1.1876", "93.45");
    objDocument.append(ctech, cursor);

    assertEquals(ctech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCparmaSurfaceApproxToEmptyObjDocumentAtCursor_OneCparmaSurfaceApproxIsAddedAtCursor()
      throws Exception {
    CparmaSurfaceApprox stech = statementFactory.createCparmaSurfaceApprox("1.234", "3.333");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCparmbSurfaceApproxToEmptyObjDocumentAtCursor_OneCparmbSurfaceApproxIsAddedAtCursor()
      throws Exception {
    CparmbSurfaceApprox stech = statementFactory.createCparmbSurfaceApprox("5.678");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCspaceSurfaceApproxToEmptyObjDocumentAtCursor_OneCspaceSurfaceApproxIsAddedAtCursor()
      throws Exception {
    CspaceSurfaceApprox stech = statementFactory.createCspaceSurfaceApprox("1.11");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneCurvSurfaceApproxToEmptyObjDocumentAtCursor_OneCurvSurfaceApproxIsAddedAtCursor()
      throws Exception {
    CurvSurfaceApprox stech = statementFactory.createCurvSurfaceApprox("1.5678", "90.0");
    objDocument.append(stech, cursor);

    assertEquals(stech, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addCommentedGeoVertexToEmptyObjDocumentAtCursor_OneCommentedGeoVertexIsAddedAtCursor()
      throws Exception {
    GeoVertex v = statementFactory.createGeoVertex("1.0000", "2.0000", "3.0000", "4.0000");
    if (v.canComment()) {
      Comment comment = statementFactory.createComment(" This is a comment.");
      v.setComment(comment);
    }
    objDocument.append(v, cursor);

    assertTrue(v.canComment());
    assertEquals(v, objDocument.peek(cursor));
    assertEquals(v.getComment(), ((GeoVertex) objDocument.peek(cursor)).getComment());
  }

  @Test
  @Ignore("not implemented")
  public void Document_addCommmentedBlankToEmptyObjDocumentAtCursor_OneCommentedBlankIsAddedATCursor()
      throws Exception {
    Blank blank = statementFactory.createBlank();
    if (blank.canComment()) {
      Comment comment = statementFactory.createComment(" This is a comment on a blank line.");
      blank.setComment(comment);
    }
    objDocument.append(blank, cursor);

    assertTrue(blank.canComment());
    assertEquals(blank, objDocument.peek(cursor));
    assertEquals(blank.getComment(), ((Blank) objDocument.peek(cursor)).getComment());
  }

  @Test
  @Ignore("not implemented")
  public void Document_addOneBlankToEmptyObjDocumentAtCursor_OneBlankIsAddedAtCursor()
      throws Exception {
    Blank blank = statementFactory.createBlank();
    objDocument.append(blank, cursor);

    assertEquals(blank, objDocument.peek(cursor));
  }

  @Test
  @Ignore("not implemented")
  public void Document_addUnknownToEmptyObjDocumentAtCursor_OneUnknownIsAddedAtCursor()
      throws Exception {
    List<String> tokens = Arrays.asList("some", "unknown", "tokens");
    Unknown unknown = statementFactory.createUnknown(tokens);
    objDocument.append(unknown, cursor);

    assertEquals(unknown, objDocument.peek(cursor));
  }
}
