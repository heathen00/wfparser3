package com.ht.wfp3.api;

/**
 * The Document View of the OBJ file.
 * 
 * This view of the file is line oriented with the first line starting at line number "1". The
 * objective of this view is to provide a convenient view appropriate for parsers. All operations
 * are based on line number and aside from checking for nulls and valid line numbers, no validation
 * of the OBJ data is performed. Thus, if invalid data is inserted within a multi-line element, no
 * error will be generated until the logical view of this data is retrieved since, at that point,
 * validation is required.
 * 
 * @author nickl
 *
 */
public interface Document {

  Node peekAtNodeAtLine(int lineNumber);

  void insertNodeAtLine(GeoVertex geoVertex, int lineNumber);

  void insertNodeAtLine(TexVertex texVertex, int lineNumber);

  void insertNodeAtLine(NormalVertex normalVertex, int lineNumber);

  void insertNodeAtLine(ParamVertex paramVertex, int lineNumber);

  void insertNodeAtLine(Point point, int lineNumber);

  void insertNodeAtLine(Line line, int lineNumber);

  void insertNodeAtLine(Face face, int lineNumber);

  void insertNodeAtLine(CurveOrSurface cstype, int lineNumber);

  void insertNodeAtLine(Degree deg, int lineNumber);

  void insertNodeAtLine(BasisMatrix bmat, int lineNumber);

  void insertNodeAtLine(StepSize stepSize, int lineNumber);
}
