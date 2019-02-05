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

  Statement peekAtStatementAtLine(int lineNumber);

  void insertStatementAtLine(GeoVertex geoVertex, int lineNumber);

  void insertStatementAtLine(TexVertex texVertex, int lineNumber);

  void insertStatementAtLine(NormalVertex normalVertex, int lineNumber);

  void insertStatementAtLine(ParamVertex paramVertex, int lineNumber);

  void insertStatementAtLine(Point point, int lineNumber);

  void insertStatementAtLine(Line line, int lineNumber);

  void insertStatementAtLine(Face face, int lineNumber);

  void insertStatementAtLine(CurveOrSurface cstype, int lineNumber);

  void insertStatementAtLine(Degree deg, int lineNumber);

  void insertStatementAtLine(BasisMatrix bmat, int lineNumber);

  void insertStatementAtLine(StepSize step, int lineNumber);

  void insertStatementAtLine(Curve curv, int lineNumber);

  void insertStatementAtLine(Curve2D curv2, int lineNumber);

  void insertStatementAtLine(Surface surf, int lineNumber);

  void insertStatementAtLine(Call call, int lineNumber);

  void insertStatementAtLine(Csh csh, int lineNumber);

  void insertStatementAtLine(Parm parm, int lineNumber);

  void insertStatementAtLine(Trim trim, int lineNumber);

  void insertStatementAtLine(Hole hole, int lineNumber);

  void insertStatementAtLine(SpecialCurve scrv, int lineNumber);

  void insertStatementAtLine(SpecialPoint sp, int lineNumber);

  void insertStatementAtLine(End end, int lineNumber);

  void insertStatementAtLine(Connect con, int lineNumber);

  void insertStatementAtLine(GroupNameList g, int lineNumber);

  void insertStatementAtLine(SmoothingGroup s, int lineNumber);

  void insertStatementAtLine(MergingGroup mg, int lineNumber);

  void insertStatementAtLine(ObjectName o, int lineNumber);

  void insertStatementAtLine(Bevel bevel, int lineNumber);

  void insertStatementAtLine(ColorInterpolation c_interp, int lineNumber);

  void insertStatementAtLine(DissolveInterpolation d_interp, int lineNumber);

  void insertStatementAtLine(LevelOfDetail lod, int lineNumber);

  void insertStatementAtLine(MapLib maplib, int lineNumber);

  void insertStatementAtLine(UseMaterial usemtl, int lineNumber);

  void insertStatementAtLine(UseMap usemap, int lineNumber);

  void insertStatementAtLine(MaterialLib mtllib, int lineNumber);

  void insertStatementAtLine(ShadowObject shadow_obj, int lineNumber);

  void insertStatementAtLine(RayTracingObject trace_obj, int lineNumber);

  void insertStatementAtLine(CparmCurveApprox ctech, int lineNumber);

  void insertStatementAtLine(CspaceCurveApprox ctech, int lineNumber);

  void insertStatementAtLine(CurvCurveApprox ctech, int lineNumber);

  void insertStatementAtLine(CparmaSurfaceApprox stech, int lineNumber);

  void insertStatementAtLine(CparmbSurfaceApprox stech, int lineNumber);

  void insertStatementAtLine(CspaceSurfaceApprox stech, int lineNumber);

  void insertStatementAtLine(CurvSurfaceApprox stech, int lineNumber);

  void insertStatementAtLine(Blank blank, int lineNumber);

  void insertStatementAtLine(Unknown unknown, int lineNumber);
}
