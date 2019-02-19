package com.ht.wfp3.api.document;

import com.google.common.annotations.VisibleForTesting;
import com.ht.wfp3.api.statement.BasisMatrix;
import com.ht.wfp3.api.statement.Bevel;
import com.ht.wfp3.api.statement.Blank;
import com.ht.wfp3.api.statement.Call;
import com.ht.wfp3.api.statement.ColorInterpolation;
import com.ht.wfp3.api.statement.Connect;
import com.ht.wfp3.api.statement.CparmCurveApprox;
import com.ht.wfp3.api.statement.CparmaSurfaceApprox;
import com.ht.wfp3.api.statement.CparmbSurfaceApprox;
import com.ht.wfp3.api.statement.Csh;
import com.ht.wfp3.api.statement.CspaceCurveApprox;
import com.ht.wfp3.api.statement.CspaceSurfaceApprox;
import com.ht.wfp3.api.statement.CurvCurveApprox;
import com.ht.wfp3.api.statement.CurvSurfaceApprox;
import com.ht.wfp3.api.statement.Curve;
import com.ht.wfp3.api.statement.Curve2D;
import com.ht.wfp3.api.statement.CurveOrSurface;
import com.ht.wfp3.api.statement.Degree;
import com.ht.wfp3.api.statement.DissolveInterpolation;
import com.ht.wfp3.api.statement.End;
import com.ht.wfp3.api.statement.Face;
import com.ht.wfp3.api.statement.GeoVertex;
import com.ht.wfp3.api.statement.GroupNameList;
import com.ht.wfp3.api.statement.Hole;
import com.ht.wfp3.api.statement.LevelOfDetail;
import com.ht.wfp3.api.statement.Line;
import com.ht.wfp3.api.statement.MapLib;
import com.ht.wfp3.api.statement.MaterialLib;
import com.ht.wfp3.api.statement.MergingGroup;
import com.ht.wfp3.api.statement.NormalVertex;
import com.ht.wfp3.api.statement.ObjectName;
import com.ht.wfp3.api.statement.ParamVertex;
import com.ht.wfp3.api.statement.Parm;
import com.ht.wfp3.api.statement.Point;
import com.ht.wfp3.api.statement.RayTracingObject;
import com.ht.wfp3.api.statement.ShadowObject;
import com.ht.wfp3.api.statement.SmoothingGroup;
import com.ht.wfp3.api.statement.SpecialCurve;
import com.ht.wfp3.api.statement.SpecialPoint;
import com.ht.wfp3.api.statement.Statement;
import com.ht.wfp3.api.statement.StepSize;
import com.ht.wfp3.api.statement.Surface;
import com.ht.wfp3.api.statement.TexVertex;
import com.ht.wfp3.api.statement.Trim;
import com.ht.wfp3.api.statement.Unknown;
import com.ht.wfp3.api.statement.UseMap;
import com.ht.wfp3.api.statement.UseMaterial;

import java.util.HashMap;
import java.util.Map;

class DocumentImp implements Document {
  private Map<Integer, Statement> linesMap;
  
  DocumentImp() {
    linesMap = new HashMap<Integer, Statement>();
  }
  
  private void addToDocumentStructure(Cursor cursor, Statement statement) {
    linesMap.put(cursor.getLineNumber(), statement);
  }

  @Override
  public Cursor createCursor() {
    return new CursorImp(this);
  }

  @Override
  public Statement peek(Cursor cursor) {
    return linesMap.get(cursor.getLineNumber());
  }

  @Override
  public void append(GeoVertex geoVertex, Cursor cursor) {
    addToDocumentStructure(cursor, geoVertex);
  }

  @Override
  public void append(TexVertex texVertex, Cursor cursor) {
    addToDocumentStructure(cursor, texVertex);
  }

  @Override
  public void append(NormalVertex normalVertex, Cursor cursor) {
    addToDocumentStructure(cursor, normalVertex);
  }

  @Override
  public void append(ParamVertex paramVertex, Cursor cursor) {
    addToDocumentStructure(cursor, paramVertex);
  }

  @Override
  public void append(Point point, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Line line, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Face face, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CurveOrSurface cstype, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Degree deg, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(BasisMatrix bmat, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(StepSize step, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Curve curv, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Curve2D curv2, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Surface surf, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Call call, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Csh csh, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Parm parm, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Trim trim, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Hole hole, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(SpecialCurve scrv, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(SpecialPoint sp, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(End end, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Connect con, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(GroupNameList g, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(SmoothingGroup s, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(MergingGroup mg, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(ObjectName o, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Bevel bevel, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(ColorInterpolation c_interp, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(DissolveInterpolation d_interp, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(LevelOfDetail lod, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(MapLib maplib, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(UseMaterial usemtl, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(UseMap usemap, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(MaterialLib mtllib, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(ShadowObject shadow_obj, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(RayTracingObject trace_obj, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CparmCurveApprox ctech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CspaceCurveApprox ctech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CurvCurveApprox ctech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CparmaSurfaceApprox stech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CparmbSurfaceApprox stech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CspaceSurfaceApprox stech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(CurvSurfaceApprox stech, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Blank blank, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(Unknown unknown, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }
  
  @VisibleForTesting
  public int getLineNumberAtEof() {
    // TODO Auto-generated method stub
    return -1;
  }
  
  @VisibleForTesting
  public Integer getNumberOfLines() {
    // TODO Auto-generated method stub
    return Integer.valueOf(-1);
  }
  
  public boolean equals(Object obj) {
    // TODO Auto-generated method stub
    return false;
  }

  public int hashCode() {
    // TODO Auto-generated method stub
    return -1;
  }

}
