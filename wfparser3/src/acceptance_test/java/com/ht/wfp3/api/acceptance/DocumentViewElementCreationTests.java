package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.*;

import com.ht.wfp3.api.Document;
import com.ht.wfp3.api.Factory;
import com.ht.wfp3.api.GeoVertex;
import com.ht.wfp3.api.NormalVertex;
import com.ht.wfp3.api.ParamVertex;
import com.ht.wfp3.api.TexVertex;

import org.junit.Test;

public class DocumentViewElementCreationTests {
  
  private static final int FIRST_LINE = 1;
  
  // TODO: You know you'll need a test for adding to line "0".  Your choices is that it fails OR it just adds to line 1.

  
  @Test
  public void Document_createEmptyObjDocument_EmptyDocumentIsCreated() {
    Document objDocument = Factory.createObjDocument();
    
    assertNotNull(objDocument);
  }
    
  @Test
  public void Document_addOneGeometricVertexToEmptyObjDocumentAtSpecifiedLine_OneGeometricVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    GeoVertex geoVertex = Factory.createGeoVertex("1.000", "2.000", "3.000", "4.000");
    objDocument.insertElementAtLine(geoVertex, FIRST_LINE);
    
    assertEquals(geoVertex, objDocument.peekAtElementAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneTextureVertexToEmptyObjDocumentAtSpecifiedLine_OneTextureVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    TexVertex texVertex = Factory.createTexVertex("3.3", "2.2", "1.1");
    objDocument.insertElementAtLine(texVertex, FIRST_LINE);
    
    assertEquals(texVertex, objDocument.peekAtElementAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneNormalVertexToEmptyObjDocumentAtSpecifiedLine_OneNormalVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    NormalVertex normalVertex = Factory.createNormalVertex("9.9", "8.8", "7.7");
    objDocument.insertElementAtLine(normalVertex, FIRST_LINE);
    
    assertEquals(normalVertex, objDocument.peekAtElementAtLine(FIRST_LINE));
  }
  
  @Test
  public void Document_addOneParamVertexToEmptyObjDocumentAtSpecifiedLine_OneParamVertexIsAddedAtSpecifiedLine() {
    Document objDocument = Factory.createObjDocument();
    
    ParamVertex paramVertex = Factory.createParamVertex("3.13", "3.31", "1.33");
    objDocument.insertElementAtLine(paramVertex, FIRST_LINE);
    
    assertEquals(paramVertex, objDocument.peekAtElementAtLine(FIRST_LINE));
    
  }
}
