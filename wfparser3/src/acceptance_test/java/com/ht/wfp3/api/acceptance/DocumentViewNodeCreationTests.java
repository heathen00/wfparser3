package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.*;

import com.ht.wfp3.api.BasisMatrix;
import com.ht.wfp3.api.CurveOrSurface;
import com.ht.wfp3.api.Degree;
import com.ht.wfp3.api.Document;
import com.ht.wfp3.api.Face;
import com.ht.wfp3.api.Factory;
import com.ht.wfp3.api.GeoVertex;
import com.ht.wfp3.api.Line;
import com.ht.wfp3.api.NormalVertex;
import com.ht.wfp3.api.ParamVertex;
import com.ht.wfp3.api.Point;
import com.ht.wfp3.api.StepSize;
import com.ht.wfp3.api.TexVertex;

import java.util.Arrays;

import org.junit.Test;

public class DocumentViewNodeCreationTests {

  private static final int FIRST_LINE = 1;

  // TODO: You know you'll need a test for adding to line "0". Your choices is that it fails OR it
  // just adds to line 1.
  
  // TODO: Can you add nodes out of order, i.e., one at line 2, then one at line 5, then another at line 1?
  // TODO: What if you skip lines?  Should the skipped lines just be rendered as blank lines?


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
    point.appendReferenceNumbers(Factory.createReferenceNumbers("1"));
    point.appendReferenceNumbers(Factory.createReferenceNumbers("2"));
    point.appendReferenceNumbers(Factory.createReferenceNumbers("3"));
    point.appendReferenceNumbers(Factory.createReferenceNumbers("4"));
    objDocument.insertNodeAtLine(point, FIRST_LINE);

    assertEquals(point, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneLineToEmptyObjDocumentAtSpecifiedLine_OneLineIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    Line line = Factory.createLine();
    line.appendReferenceNumbers(Factory.createReferenceNumbers("1", "1"));
    line.appendReferenceNumbers(Factory.createReferenceNumbers("2", "2"));
    line.appendReferenceNumbers(Factory.createReferenceNumbers("3", "3"));
    line.appendReferenceNumbers(Factory.createReferenceNumbers("4", "4"));
    objDocument.insertNodeAtLine(line, FIRST_LINE);

    assertEquals(line, objDocument.peekAtNodeAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneFaceToEmptyObjDocumentAtSpecifiedLine_OneFaceIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    Face face = Factory.createFace();
    face.appendReferenceNumbers(Factory.createReferenceNumbers("1", "1", "1"));
    face.appendReferenceNumbers(Factory.createReferenceNumbers("2", "2", "2"));
    face.appendReferenceNumbers(Factory.createReferenceNumbers("3", "3", "3"));
    face.appendReferenceNumbers(Factory.createReferenceNumbers("4", "4", "4"));
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
    
    String[] flattenedUAxisBasisMatrix = { 
        "1.234", "-1.234", "4.321", 
        "-4.321", "9.876", "-9.876"
    };
    BasisMatrix bmat = Factory.createBasisMatrix(BasisMatrix.Axis.U, Arrays.asList(flattenedUAxisBasisMatrix));
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
}
