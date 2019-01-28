package com.ht.wfp3.api;

public interface Document {

  Element peekAtElementAtLine(int lineNumber);

  void insertElementAtLine(GeoVertex geoVertex, int lineNumber);

  void insertElementAtLine(TexVertex texVertex, int lineNumber);

  void insertElementAtLine(NormalVertex normalVertex, int lineNumber);

  void insertElementAtLine(ParamVertex paramVertex, int lineNumber);

  void insertElementAtLine(Point point, int lineNumber);

  void insertElementAtLine(Line line, int lineNumber);

  void insertElementAtLine(Face face, int lineNumber);
  
}
