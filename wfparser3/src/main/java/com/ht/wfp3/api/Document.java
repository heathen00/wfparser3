package com.ht.wfp3.api;

import com.ht.wfp3.api.acceptance.Unknown;

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
  
  static DocumentFactory getDocumentFactory() {
    // TODO Auto-generated method stub
    return null;
  }

  Cursor createCursor();

  Statement peek(Cursor cursor);

  void append(GeoVertex geoVertex, Cursor cursor);

  void append(TexVertex texVertex, Cursor cursor);

  void append(NormalVertex normalVertex, Cursor cursor);

  void append(ParamVertex paramVertex, Cursor cursor);

  void append(Point point, Cursor cursor);

  void append(Line line, Cursor cursor);

  void append(Face face, Cursor cursor);

  void append(CurveOrSurface cstype, Cursor cursor);

  void append(Degree deg, Cursor cursor);

  void append(BasisMatrix bmat, Cursor cursor);

  void append(StepSize step, Cursor cursor);

  void append(Curve curv, Cursor cursor);

  void append(Curve2D curv2, Cursor cursor);

  void append(Surface surf, Cursor cursor);

  void append(Call call, Cursor cursor);

  void append(Csh csh, Cursor cursor);

  void append(Parm parm, Cursor cursor);

  void append(Trim trim, Cursor cursor);

  void append(Hole hole, Cursor cursor);

  void append(SpecialCurve scrv, Cursor cursor);

  void append(SpecialPoint sp, Cursor cursor);

  void append(End end, Cursor cursor);

  void append(Connect con, Cursor cursor);

  void append(GroupNameList g, Cursor cursor);

  void append(SmoothingGroup s, Cursor cursor);

  void append(MergingGroup mg, Cursor cursor);

  void append(ObjectName o, Cursor cursor);

  void append(Bevel bevel, Cursor cursor);

  void append(ColorInterpolation c_interp, Cursor cursor);

  void append(DissolveInterpolation d_interp, Cursor cursor);

  void append(LevelOfDetail lod, Cursor cursor);

  void append(MapLib maplib, Cursor cursor);

  void append(UseMaterial usemtl, Cursor cursor);

  void append(UseMap usemap, Cursor cursor);

  void append(MaterialLib mtllib, Cursor cursor);

  void append(ShadowObject shadow_obj, Cursor cursor);

  void append(RayTracingObject trace_obj, Cursor cursor);

  void append(CparmCurveApprox ctech, Cursor cursor);

  void append(CspaceCurveApprox ctech, Cursor cursor);

  void append(CurvCurveApprox ctech, Cursor cursor);

  void append(CparmaSurfaceApprox stech, Cursor cursor);

  void append(CparmbSurfaceApprox stech, Cursor cursor);

  void append(CspaceSurfaceApprox stech, Cursor cursor);

  void append(CurvSurfaceApprox stech, Cursor cursor);

  void append(Blank blank, Cursor cursor);

  void append(Unknown unknown, Cursor cursor);

}
