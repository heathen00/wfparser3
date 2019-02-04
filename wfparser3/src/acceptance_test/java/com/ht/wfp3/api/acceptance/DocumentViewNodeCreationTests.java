package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.*;

import com.ht.wfp3.api.BasisMatrix;
import com.ht.wfp3.api.Call;
import com.ht.wfp3.api.Connect;
import com.ht.wfp3.api.Csh;
import com.ht.wfp3.api.Curve;
import com.ht.wfp3.api.Curve2D;
import com.ht.wfp3.api.Curve2DReference;
import com.ht.wfp3.api.CurveOrSurface;
import com.ht.wfp3.api.Degree;
import com.ht.wfp3.api.Document;
import com.ht.wfp3.api.End;
import com.ht.wfp3.api.Face;
import com.ht.wfp3.api.Factory;
import com.ht.wfp3.api.GeoVertex;
import com.ht.wfp3.api.GroupNameList;
import com.ht.wfp3.api.Hole;
import com.ht.wfp3.api.Line;
import com.ht.wfp3.api.MergingGroup;
import com.ht.wfp3.api.NormalVertex;
import com.ht.wfp3.api.ObjectName;
import com.ht.wfp3.api.ParamVertex;
import com.ht.wfp3.api.Parm;
import com.ht.wfp3.api.Point;
import com.ht.wfp3.api.SmoothingGroup;
import com.ht.wfp3.api.SpecialCurve;
import com.ht.wfp3.api.SpecialPoint;
import com.ht.wfp3.api.StepSize;
import com.ht.wfp3.api.Surface;
import com.ht.wfp3.api.TexVertex;
import com.ht.wfp3.api.Trim;
import com.ht.wfp3.api.VertexReference;
import com.ht.wfp3.api.VertexReferenceGroup;

import java.util.Arrays;

import org.junit.Test;

public class DocumentViewNodeCreationTests {

  private static final int FIRST_LINE = 1;

  // TODO: You know you'll need a test for adding to line "0". Your choices is that it fails OR it
  // just adds to line 1.

  // TODO: Can you add nodes out of order, i.e., one at line 2, then one at line 5, then another at
  // line 1?
  // TODO: What if you skip lines? Should the skipped lines just be rendered as blank lines?

  // TODO: It might be useful to have an end of document marker.


  @Test
  public void Document_createEmptyObjDocument_EmptyDocumentIsCreated() {
    Document objDocument = Factory.createObjDocument();

    assertNotNull(objDocument);
  }

  @Test
  public void Document_addOneGeometricVertexToEmptyObjDocumentAtSpecifiedLine_OneGeometricVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    GeoVertex geoVertex = Factory.createGeoVertex("1.000", "2.000", "3.000", "4.000");
    objDocument.insertNodeAtLine(geoVertex, FIRST_LINE);

    assertEquals(geoVertex, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneTextureVertexToEmptyObjDocumentAtSpecifiedLine_OneTextureVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    TexVertex texVertex = Factory.createTexVertex("3.3", "2.2", "1.1");
    objDocument.insertNodeAtLine(texVertex, FIRST_LINE);

    assertEquals(texVertex, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneNormalVertexToEmptyObjDocumentAtSpecifiedLine_OneNormalVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    NormalVertex normalVertex = Factory.createNormalVertex("9.9", "8.8", "7.7");
    objDocument.insertNodeAtLine(normalVertex, FIRST_LINE);

    assertEquals(normalVertex, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneParamVertexToEmptyObjDocumentAtSpecifiedLine_OneParamVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    ParamVertex paramVertex = Factory.createParamVertex("3.13", "3.31", "1.33");
    objDocument.insertNodeAtLine(paramVertex, FIRST_LINE);

    assertEquals(paramVertex, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOnePointToEmptyObjDocumentAtSpecifiedLine_OnePointIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(point, FIRST_LINE);

    assertEquals(point, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneLineToEmptyObjDocumentAtSpecifiedLine_OneLineIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(line, FIRST_LINE);

    assertEquals(line, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneFaceToEmptyObjDocumentAtSpecifiedLine_OneFaceIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(face, FIRST_LINE);

    assertEquals(face, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneCSTypeToEmptyObjDocumentAtSpecifiedLine_OneCSTypeIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    CurveOrSurface cstype = Factory.createCurveOrSurfaceType(false, CurveOrSurface.Type.BMATRIX);
    objDocument.insertNodeAtLine(cstype, FIRST_LINE);

    assertEquals(cstype, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneDegreeToEmptyObjDocumentAtSpecifiedLine_OneDegreeIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Degree deg = Factory.createDegree("5", "6");
    objDocument.insertNodeAtLine(deg, FIRST_LINE);

    assertEquals(deg, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneBasisMatrixToEmptyObjDocumentAtSpecifiedLine_OneBasisMatrixIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    String[] flattenedUAxisBasisMatrix =
        { "1.234", "-1.234", "4.321", "-4.321", "9.876", "-9.876" };
    BasisMatrix bmat =
        Factory.createBasisMatrix(BasisMatrix.Axis.U, Arrays.asList(flattenedUAxisBasisMatrix));
    objDocument.insertNodeAtLine(bmat, FIRST_LINE);

    assertEquals(bmat, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneStepToEmptyObjDocumentAtSpecifiedLine_OneStepIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    StepSize step = Factory.createStepSize("7", "33");
    objDocument.insertNodeAtLine(step, FIRST_LINE);

    assertEquals(step, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneCurveToEmptyObjDocumentAtSpecifiedLine_OneCurveIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(curv, FIRST_LINE);

    assertEquals(curv, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneCurve2DToEmptyObjDocumentAtSpecifiedLine_OneCurve2DIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(curv2, FIRST_LINE);

    assertEquals(curv2, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneSurfaceToEmptyObjDocumentAtSpecifiedLine_OneSurfaceIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(surf, FIRST_LINE);

    assertEquals(surf, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneCallToEmptyObjDocumentAtSpecifiedLine_OneCallIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Call call = Factory.createCall("filename.obj");
    call.appendArgument("3");
    call.appendArgument("5");
    call.appendArgument("78");
    objDocument.insertNodeAtLine(call, FIRST_LINE);

    assertEquals(call, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneCshToEmptyObjDocumentAtSpecifiedLine_OneCshIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Csh csh = Factory.createCsh(true, "pwd");
    objDocument.insertNodeAtLine(csh, FIRST_LINE);

    assertEquals(csh, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneParmToEmptyObjDocumentAtSpecifiedLine_OneParmIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Parm parm = Factory.createParm(Parm.Axis.U);
    parm.appendParameterValue("1.0000");
    parm.appendParameterValue("4.4444");
    parm.appendParameterValue("-9.22");
    parm.appendParameterValue("111.9876");
    objDocument.insertNodeAtLine(parm, FIRST_LINE);

    assertEquals(parm, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneTrimToEmptyObjDocumentAtSpecifiedLine_OneTrimIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Trim trim = Factory.createTrim();
    trim.appendTrimmingCurve2DReference(Factory.createCurve2DReference("1.4567", "-99.5463", "1"));
    trim.appendTrimmingCurve2DReference(Factory.createCurve2DReference("1.111", "2.22", "5"));
    trim.appendTrimmingCurve2DReference(Factory.createCurve2DReference("6.789", "0.543", "77"));
    objDocument.insertNodeAtLine(trim, FIRST_LINE);

    assertEquals(trim, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneHoleToEmptyObjDocumentAtSpecifiedLine_OneHoleIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Hole hole = Factory.createHole();
    hole.appendTrimmingCurve2DReference(Factory.createCurve2DReference("3.333", "4.444", "22"));
    hole.appendTrimmingCurve2DReference(Factory.createCurve2DReference("-1.111", "-2.232", "3"));
    hole.appendTrimmingCurve2DReference(Factory.createCurve2DReference("4.78", "99.99", "11"));
    objDocument.insertNodeAtLine(hole, FIRST_LINE);

    assertEquals(hole, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneSpecialCurveToEmptyObjDocumentAtSpecifiedLine_OneSpecialCurveIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    SpecialCurve scrv = Factory.createSpecialCurve();
    scrv.appendSpecialCurve2DReference(Factory.createCurve2DReference("3.333", "4.444", "22"));
    scrv.appendSpecialCurve2DReference(Factory.createCurve2DReference("3.333", "4.444", "22"));
    objDocument.insertNodeAtLine(scrv, FIRST_LINE);

    assertEquals(scrv, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneSpecialPointToEmptyObjDocumentAtSpecifiedLine_OneSpecialPointIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

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
    objDocument.insertNodeAtLine(sp, FIRST_LINE);

    assertEquals(sp, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }

  @Test
  public void Document_addOneEndToEmptyObjDocumentAtSpecifiedLine_OneEndIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    End end = Factory.createEnd();
    objDocument.insertNodeAtLine(end, FIRST_LINE);

    assertEquals(end, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneConnectToEmptyObjDocumentAtSpecifiedLine_OneConnectIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();

    Curve2DReference curve2dReferenceForSurface3 = Factory.createCurve2DReference("1.111", "2.222", "1");
    Curve2DReference curve2dReferenceForSurface4 = Factory.createCurve2DReference("3.333", "4.444", "2");
    Connect con = Factory.createConnect("3", curve2dReferenceForSurface3, "4", curve2dReferenceForSurface4);
    objDocument.insertNodeAtLine(con, FIRST_LINE);
    
    assertEquals(con, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneGroupNameToEmptyObjDocumentAtSpecifiedLine_OneGroupNameIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    GroupNameList g = Factory.createGroupName();
    g.appendGroupName("cube");
    g.appendGroupName("top");
    objDocument.insertNodeAtLine(g, FIRST_LINE);
    
    assertEquals(g, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneSmoothingGroupToEmptyObjDocumentAtSpecifiedLine_OneSmoothingGroupIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    SmoothingGroup s = Factory.createSmoothingGroup("3");
    objDocument.insertNodeAtLine(s, FIRST_LINE);
    
    assertEquals(s, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addMergingGroupToEmptyObjDocumentAtSpecifiedLine_OneMergingGroupIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    MergingGroup mg = Factory.createMergingGroup("3", "0.6");
    objDocument.insertNodeAtLine(mg, FIRST_LINE);
    
    assertEquals(mg, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addObjectNameToEmptyObjDocumentAtSpecifiedLine_OneObjectNameIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    ObjectName o = Factory.createObjectName("test_cube");
    objDocument.insertNodeAtLine(o, FIRST_LINE);
    
    assertEquals(o, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
}
