package com.ht.wfp3.api;

/**
 * The Document View of the OBJ file.
 * 
 * This view of the file is line oriented with the first line starting at line number "1". The
 * objective of this view is to provide a convenient view appropriate for parsers. All operations
 * are based on line number and aside from checking for nulls and valid line numbers, no validation
 * of the elements is performed. Thus, if a single line element is inserted within a multi-line
 * element, no error will be generated until the logical view of this data is retrieved since, at
 * that point, validation is required.
 * 
 * @author nickl
 *
 */
public interface Document {

  /**
   * Peek at the element at the specified line number. Does not modify the structure of the
   * document.
   * 
   * @param lineNumber The line number of the desired element.
   * @return The element at the specified line number.
   */
  Element peekAtElementAtLine(int lineNumber);

  /**
   * Insert a geometric vertex element at the specified line number.
   * 
   * @param geoVertex The geometric vertex element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(GeoVertex geoVertex, int lineNumber);

  /**
   * Insert a texture vertex element at the specified line number.
   * 
   * @param texVertex The texture vertex element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(TexVertex texVertex, int lineNumber);

  /**
   * Insert a normal vertex element at the specified line number.
   * 
   * @param normalVertex The normal vertex element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(NormalVertex normalVertex, int lineNumber);

  /**
   * Insert a parametric vertex element at the specified line number.
   * 
   * @param paramVertex The parametric vertex element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(ParamVertex paramVertex, int lineNumber);

  /**
   * Insert a point element at the specified line number.
   * 
   * @param point The point element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(Point point, int lineNumber);

  /**
   * Insert a line element at the specified line number.
   * 
   * @param line The line element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(Line line, int lineNumber);

  /**
   * Insert a face element at the specified line number.
   * 
   * @param face The face element to insert.
   * @param lineNumber The line number at which to insert it.
   */
  void insertElementAtLine(Face face, int lineNumber);

}
