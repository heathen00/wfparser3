package com.ht.wfp3.api;

public interface Document {

  void insertElementAtLine(GeoVertex geoVertex, int line);

  void insertElementAtLine(TexVertex texVertex, int line);

  void insertElementAtLine(NormalVertex normalVertex, int line);

  void insertElementAtLine(ParamVertex paramVertex, int line);

  Element peekAtElementAtLine(int line);
}
