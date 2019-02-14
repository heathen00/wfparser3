package com.ht.wfp3.api.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.mock;

import com.ht.wfp3.api.document.Cursor;
import com.ht.wfp3.api.document.NonExistentLineException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class CursorTests {

  @Mock
  MockDocument mockDocument;

  @InjectMocks
  private Cursor cursor;

  @Before
  public void setUp() throws Exception {
    when(mockDocument.getNumLines()).thenReturn(Integer.valueOf(20));
    cursor = mockDocument.createCursor();
  }

  @Test
  public void Cursor_createTestDocument_testDocumentNotNullAndNotEmpty() {
    assertNotNull(mockDocument);
    assertEquals(Integer.valueOf(20), mockDocument.getNumLines());
  }

  @Test
  public void Cursor_createCursorFromDocument_CursorIsCreatedAndAtBof() {

    assertNotNull(cursor);
    assertEquals(Integer.valueOf(1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToEof_cursorAtEof() {
    cursor.toEof();

    assertEquals(mockDocument.getNumLines(), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToBof_cursorAtBof() {
    cursor.toBof();

    assertEquals(Integer.valueOf(1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToNextLine_CursorAtNextLine() {
    Integer startLine = Integer.valueOf(3);
    cursor.toLineNumber(startLine);
    cursor.toNextLine();

    assertEquals(Integer.valueOf(startLine.intValue() + 1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToPreviousLine_CursorAtPreviousLine() {
    Integer startLine = Integer.valueOf(3);
    cursor.toLineNumber(startLine);
    cursor.toPreviousLine();

    assertEquals(Integer.valueOf(startLine.intValue() + 1), cursor.getLineNumber());
  }

  @Test
  public void Cursor_setToSpecificLine_CursorAtSpecificLine() {
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
  public void Cursor_hasNextLineWhenNotAtEof_hasNextLineIsTrue() {
    cursor.toLineNumber(Integer.valueOf(5));

    assertTrue(cursor.hasNextLine());
  }

  @Test
  public void Cursor_hasPreviousLineWhenAtBof_hasPreviousLineIsFalse() {
    cursor.toBof();

    assertFalse(cursor.hasPreviousLine());
  }

  @Test
  public void Cursor_hasPreviousLineWhenNotAtBof_hasPreviousLineIsTrue() {
    cursor.toLineNumber(Integer.valueOf(13));

    assertTrue(cursor.hasPreviousLine());
  }

  @Test
  public void Cursor_setToAnotherCursor_cursorsAreEqualAndAtSameLine() {
    Cursor initialCursor = mockDocument.createCursor();
    initialCursor.toLineNumber(Integer.valueOf(11));

    cursor.toCursor(initialCursor);

    assertEquals(initialCursor, cursor);
    assertEquals(initialCursor.getLineNumber(), cursor.getLineNumber());
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToNextLineWhenAtEof_nonExistentLineExceptionIsThrown() {
    cursor.toEof();
    cursor.toNextLine();
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToPreviousLineWhenAtBof_nonExistentLineExceptionIsThrown() {
    cursor.toBof();
    cursor.toPreviousLine();
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToLineNumberIsZero_nonExistentLineExceptionIsThrown() {
    cursor.toLineNumber(Integer.valueOf(0));
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToLineNumberIsGreaterThanNumberOfLines_nonExistentLineExceptionIsThrown() {
    cursor.toLineNumber(Integer.valueOf(mockDocument.getNumLines().intValue() + 1));
  }

  @Test(expected = NonExistentLineException.class)
  public void Cursor_setToNegativeLineNumber_nonExistentLineExceptionIsThrown() {
    cursor.toLineNumber(Integer.valueOf(-1));
  }

  @Test
  public void Cursor_cursorsFromSameDocumentAndAtSameLine_areEqualAndHaveSameHashCode() {
    Integer lineNumber = Integer.valueOf(10);
    Cursor anotherCursor = mockDocument.createCursor();
    anotherCursor.toLineNumber(lineNumber);

    cursor.toLineNumber(lineNumber);

    assertEquals(anotherCursor, cursor);
    assertEquals(anotherCursor.hashCode(), cursor.hashCode());
  }

  @Test
  public void Cursor_cursorsFromDifferentDocumentsAndAtSameLine_areNotEqualAndDoNotHaveSameHashCode() {
    Integer lineNumber = Integer.valueOf(2);
    MockDocument anotherMockDocument = mock(MockDocument.class);
    Cursor anotherCursor = anotherMockDocument.createCursor();
    anotherCursor.toLineNumber(lineNumber);

    cursor.toLineNumber(lineNumber);

    assertNotEquals(anotherCursor, cursor);
    assertNotEquals(anotherCursor.hashCode(), cursor.hashCode());
  }

  @Test
  public void Cursor_cursorsFromSameDocumentAndAtDifferentLine_areNotEqualAndDoNotHaveSameHashCode() {
    Cursor anotherCursor = mockDocument.createCursor();
    anotherCursor.toLineNumber(Integer.valueOf(3));

    cursor.toLineNumber(Integer.valueOf(15));

    assertNotEquals(anotherCursor, cursor);
    assertNotEquals(anotherCursor.hashCode(), cursor.hashCode());
  }

  @Test(expected = NullPointerException.class)
  public void Cursor_toCursorPassedANull_NullPointerExceptionIsThrown() {
    cursor.toCursor(null);
  }

  @Test(expected = NullPointerException.class)
  public void Cursor_toLineNumberPassedANull_NullPointerExceptionIsThrown() {
    cursor.toLineNumber(null);
  }
}
