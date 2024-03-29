package com.nickmlanglois.wfp3.api.document;

import com.nickmlanglois.wfp3.api.statement.BasisMatrix;
import com.nickmlanglois.wfp3.api.statement.Bevel;
import com.nickmlanglois.wfp3.api.statement.Call;
import com.nickmlanglois.wfp3.api.statement.ColorInterpolation;
import com.nickmlanglois.wfp3.api.statement.Connect;
import com.nickmlanglois.wfp3.api.statement.Csh;
import com.nickmlanglois.wfp3.api.statement.Curve;
import com.nickmlanglois.wfp3.api.statement.Curve2D;
import com.nickmlanglois.wfp3.api.statement.CurveApproxCparmTechnique;
import com.nickmlanglois.wfp3.api.statement.CurveApproxCspaceTechnique;
import com.nickmlanglois.wfp3.api.statement.CurveApproxCurvTechnique;
import com.nickmlanglois.wfp3.api.statement.CurveOrSurfaceType;
import com.nickmlanglois.wfp3.api.statement.Degree;
import com.nickmlanglois.wfp3.api.statement.DissolveInterpolation;
import com.nickmlanglois.wfp3.api.statement.End;
import com.nickmlanglois.wfp3.api.statement.Face;
import com.nickmlanglois.wfp3.api.statement.GeoVertex;
import com.nickmlanglois.wfp3.api.statement.GroupNameList;
import com.nickmlanglois.wfp3.api.statement.Hole;
import com.nickmlanglois.wfp3.api.statement.LevelOfDetail;
import com.nickmlanglois.wfp3.api.statement.Line;
import com.nickmlanglois.wfp3.api.statement.MapLib;
import com.nickmlanglois.wfp3.api.statement.MaterialLib;
import com.nickmlanglois.wfp3.api.statement.MergingGroup;
import com.nickmlanglois.wfp3.api.statement.NormalVertex;
import com.nickmlanglois.wfp3.api.statement.ObjectName;
import com.nickmlanglois.wfp3.api.statement.ParamVertex;
import com.nickmlanglois.wfp3.api.statement.Parm;
import com.nickmlanglois.wfp3.api.statement.Point;
import com.nickmlanglois.wfp3.api.statement.RayTracingObject;
import com.nickmlanglois.wfp3.api.statement.ShadowObject;
import com.nickmlanglois.wfp3.api.statement.SmoothingGroup;
import com.nickmlanglois.wfp3.api.statement.SpecialCurve;
import com.nickmlanglois.wfp3.api.statement.SpecialPoint;
import com.nickmlanglois.wfp3.api.statement.StepSize;
import com.nickmlanglois.wfp3.api.statement.Surface;
import com.nickmlanglois.wfp3.api.statement.SurfaceApproxCparmaTechnique;
import com.nickmlanglois.wfp3.api.statement.SurfaceApproxCparmbTechnique;
import com.nickmlanglois.wfp3.api.statement.SurfaceApproxCspaceTechnique;
import com.nickmlanglois.wfp3.api.statement.SurfaceApproxCurvTechnique;
import com.nickmlanglois.wfp3.api.statement.TexVertex;
import com.nickmlanglois.wfp3.api.statement.Trim;
import com.nickmlanglois.wfp3.api.statement.UseMap;
import com.nickmlanglois.wfp3.api.statement.UseMaterial;

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
public interface DocumentView {

  Cursor createCursor();

  DocumentLine peek(Cursor cursor) throws EmptyDocumentException;

  void append(GeoVertex geoVertex, Cursor cursor);

  void append(TexVertex texVertex, Cursor cursor);

  void append(NormalVertex normalVertex, Cursor cursor);

  void append(ParamVertex paramVertex, Cursor cursor);

  void append(Point point, Cursor cursor);

  void append(Line line, Cursor cursor);

  void append(Face face, Cursor cursor);

  void append(CurveOrSurfaceType cstype, Cursor cursor);

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

  void append(CurveApproxCparmTechnique ctech, Cursor cursor);

  void append(CurveApproxCspaceTechnique ctech, Cursor cursor);

  void append(CurveApproxCurvTechnique ctech, Cursor cursor);

  void append(SurfaceApproxCparmaTechnique stech, Cursor cursor);

  void append(SurfaceApproxCparmbTechnique stech, Cursor cursor);

  void append(SurfaceApproxCspaceTechnique stech, Cursor cursor);

  void append(SurfaceApproxCurvTechnique stech, Cursor cursor);

  boolean equals(Object obj);

  int hashCode();

}
