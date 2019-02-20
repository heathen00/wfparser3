package com.ht.wfp3.api.document;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CursorUnitTests {

  @Mock
  private VisibleDocumentImp mockDocument;

  @InjectMocks
  private VisibleCursorImp cursor;

  private static VisibleCursorImp createVisibleCursorImp(VisibleDocumentImp mockDocument) {
    return new VisibleCursorImp(mockDocument);
  }

  @Before
  public void setUp() throws Exception {
    cursor = createVisibleCursorImp(mockDocument);
  }

  @Test
  public void Cursor_cursorEqualsWithNull_NotEqual() {
    assertNotEquals(null, cursor);
  }

  @Test
  public void Cursor_cursorEqualsWithNonCursor_NotEqual() {
    Object object = new Object();

    assertNotEquals(object, cursor);
  }

  @Test
  public void Cursor_cursorEqualsWithSelf_Equal() {
    assertEquals(cursor, cursor);
  }

  @Test(expected = NullPointerException.class)
  public void Cursor_createCursorImpWithNullDocument_NullPointerExceptionIsThrown() {
    @SuppressWarnings("unused")
    VisibleCursorImp cursorToTest = new VisibleCursorImp(null);
  }
}