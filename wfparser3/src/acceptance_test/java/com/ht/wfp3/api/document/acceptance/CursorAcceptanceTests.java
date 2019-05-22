package com.ht.wfp3.api.document.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ht.wfp3.api.document.Cursor;
import com.ht.wfp3.api.document.NonExistentLineException;
import com.ht.wfp3.api.document.VisibleCursorImp;
import com.ht.wfp3.api.document.VisibleDocumentImp;

@RunWith(MockitoJUnitRunner.class)
public class CursorAcceptanceTests {

  @Mock
  private VisibleDocumentImp mockDocument;

  @InjectMocks
  private VisibleCursorImp cursor;

  private static VisibleCursorImp createVisibleCursorImp(VisibleDocumentImp mockDocument) {
    return new VisibleCursorImp(mockDocument);
  }

  @Before
  public void setUp() throws Exception {
    when(mockDocument.getNumberOfLines()).thenReturn(Integer.valueOf(20));
    when(mockDocument.getLineNumberAtEof()).thenReturn(20);
    cursor = createVisibleCursorImp(mockDocument);
  }

  @Test
  public void Cursor_createMockDocument_mockDocumentNotNullAndNotEmpty() {
    assertNotNull(mockDocument);
    assertEquals(Integer.valueOf(20), mockDocument.getNumberOfLines());
  }

  @Test
  public void Cursor_createCursorFromDocument_CursorIsCreatedAndAtBof() {
    assertNotNull(cursor);
    assertEquals(Integer.valueOf(1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToEof_cursorAtEof() {
    cursor.toEof();

    assertEquals(mockDocument.getNumberOfLines(), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToBof_cursorAtBof() {
    cursor.toBof();

    assertEquals(Integer.valueOf(1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToNextLine_CursorAtNextLine() throws Exception {
    Integer startLine = Integer.valueOf(3);
    cursor.toLineNumber(startLine);
    cursor.toNextLine();

    assertEquals(Integer.valueOf(startLine.intValue() + 1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToPreviousLine_CursorAtPreviousLine() throws Exception {
    Integer startLine = Integer.valueOf(3);
    cursor.toLineNumber(startLine);
    cursor.toPreviousLine();

    assertEquals(Integer.valueOf(startLine.intValue() - 1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToSpecificLine_CursorAtSpecificLine() throws Exception {
    Integer startLine = Integer.valueOf(10);
    cursor.toLineNumber(startLine);

    assertEquals(startLine, cursor.getLineNumber());
  }

  @Test
  public void Cursor_hasNextLineWhenAtEof_hasNextLineIsFalse() {
    cursor.toEof();

    assertFalse(cursor.hasNextLine());
  }

  @Test
  public void Cursor_hasNextLineWhenNotAtEof_hasNextLineIsTrue() throws Exception {
    cursor.toLineNumber(Integer.valueOf(5));

    assertTrue(cursor.hasNextLine());
  }

  @Test
  public void Cursor_hasPreviousLineWhenAtBof_hasPreviousLineIsFalse() {
    cursor.toBof();

    assertFalse(cursor.hasPreviousLine());
  }

  @Test
  public void Cursor_hasPreviousLineWhenNotAtBof_hasPreviousLineIsTrue() throws Exception {
    cursor.toLineNumber(Integer.valueOf(13));

    assertTrue(cursor.hasPreviousLine());
  }

  @Test
  public void Cursor_setToAnotherCursor_cursorsAreEqualAndAtSameLine() throws Exception {
    Cursor initialCursor = CursorAcceptanceTests.createVisibleCursorImp(mockDocument);
    initialCursor.toLineNumber(Integer.valueOf(11));

    cursor.toCursor(initialCursor);

    assertEquals(initialCursor, cursor);
    assertEquals(initialCursor.getLineNumber(), cursor.getLineNumber());
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToNextLineWhenAtEof_nonExistentLineExceptionIsThrown() throws Exception {
    cursor.toEof();
    cursor.toNextLine();
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToPreviousLineWhenAtBof_nonExistentLineExceptionIsThrown()
      throws Exception {
    cursor.toBof();
    cursor.toPreviousLine();
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToLineNumberIsZero_nonExistentLineExceptionIsThrown() throws Exception {
    cursor.toLineNumber(Integer.valueOf(0));
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToLineNumberIsGreaterThanNumberOfLines_nonExistentLineExceptionIsThrown()
      throws Exception {
    cursor.toLineNumber(Integer.valueOf(mockDocument.getNumberOfLines().intValue() + 1));
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToNegativeLineNumber_nonExistentLineExceptionIsThrown() throws Exception {
    cursor.toLineNumber(Integer.valueOf(-1));
  }

  @Test
  public void Cursor_cursorsFromSameDocumentAndAtSameLine_areEqualAndHaveSameHashCode()
      throws Exception {
    Integer lineNumber = Integer.valueOf(10);
    Cursor anotherCursor = createVisibleCursorImp(mockDocument);
    anotherCursor.toLineNumber(lineNumber);

    cursor.toLineNumber(lineNumber);

    assertEquals(anotherCursor, cursor);
    assertEquals(anotherCursor.hashCode(), cursor.hashCode());
  }

  @Test
  public void Cursor_cursorsFromDifferentDocumentsAndAtSameLine_areNotEqualAndDoNotHaveSameHashCode()
      throws Exception {
    Integer lineNumber = Integer.valueOf(2);
    VisibleDocumentImp anotherMockDocument = mock(VisibleDocumentImp.class);
    when(anotherMockDocument.getLineNumberAtEof()).thenReturn(21);
    Cursor anotherCursor = createVisibleCursorImp(anotherMockDocument);
    anotherCursor.toLineNumber(lineNumber);

    cursor.toLineNumber(lineNumber);

    assertNotEquals(anotherCursor, cursor);
    assertNotEquals(anotherCursor.hashCode(), cursor.hashCode());
  }

  @Test
  public void Cursor_cursorsFromSameDocumentAndAtDifferentLine_areNotEqualAndDoNotHaveSameHashCode()
      throws Exception {
    Cursor anotherCursor = createVisibleCursorImp(mockDocument);
    anotherCursor.toLineNumber(Integer.valueOf(3));

    cursor.toLineNumber(Integer.valueOf(15));

    assertNotEquals(anotherCursor, cursor);
    assertNotEquals(anotherCursor.hashCode(), cursor.hashCode());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Cursor_setToCursorToCursorFromAnotherDocument_IllegalArgumentExceptionIsThrown()
      throws Exception {
    Integer lineNumber = Integer.valueOf(2);
    VisibleDocumentImp anotherMockDocument = mock(VisibleDocumentImp.class);
    when(anotherMockDocument.getLineNumberAtEof()).thenReturn(23);
    Cursor anotherCursor = createVisibleCursorImp(anotherMockDocument);
    anotherCursor.toLineNumber(lineNumber);

    cursor.toCursor(anotherCursor);
  }

  @Test(expected = NullPointerException.class)
  public void Cursor_toCursorPassedANull_NullPointerExceptionIsThrown() {
    cursor.toCursor(null);
  }

  @Test(expected = NullPointerException.class)
  public void Cursor_toLineNumberPassedANull_NullPointerExceptionIsThrown() throws Exception {
    cursor.toLineNumber(null);
  }
}
