package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.wfp3.api.document.Cursor;
import com.ht.wfp3.api.document.Document;

import org.junit.Before;
import org.junit.Test;

public class CursorTests {

  private Document objDocument;

  @Before
  public void setUp() throws Exception {
    objDocument = Document.getDocumentFactory().createObjDocument();
  }

  @Test
  public void Cursor_createCursorFromDocument_CursorIsCreated() {
    Cursor cursor = objDocument.createCursor();

    assertNotNull(cursor);
  }

}
