package com.ht.wfp3.api.document;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.google.common.annotations.VisibleForTesting;
import com.ht.wfp3.api.statement.BasisMatrix;
import com.ht.wfp3.api.statement.Bevel;
import com.ht.wfp3.api.statement.Blank;
import com.ht.wfp3.api.statement.Call;
import com.ht.wfp3.api.statement.ColorInterpolation;
import com.ht.wfp3.api.statement.Connect;
import com.ht.wfp3.api.statement.CurveApproxCparmTechnique;
import com.ht.wfp3.api.statement.SurfaceApproxCparmaTechnique;
import com.ht.wfp3.api.statement.SurfaceApproxCparmbTechnique;
import com.ht.wfp3.api.statement.Csh;
import com.ht.wfp3.api.statement.CurveApproxCspaceTechnique;
import com.ht.wfp3.api.statement.SurfaceApproxCspaceTechnique;
import com.ht.wfp3.api.statement.CurveApproxCurvTechnique;
import com.ht.wfp3.api.statement.SurfaceApproxCurvTechnique;
import com.ht.wfp3.api.statement.Curve;
import com.ht.wfp3.api.statement.Curve2D;
import com.ht.wfp3.api.statement.CurveOrSurfaceType;
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
import com.ht.wfp3.api.statement.StatementFactory;
import com.ht.wfp3.api.statement.StepSize;
import com.ht.wfp3.api.statement.Surface;
import com.ht.wfp3.api.statement.TexVertex;
import com.ht.wfp3.api.statement.Trim;
import com.ht.wfp3.api.statement.Unknown;
import com.ht.wfp3.api.statement.UseMap;
import com.ht.wfp3.api.statement.UseMaterial;

class DocumentImp implements Document {
  private final StatementFactory statementFactory;
  private final DocumentFactory documentFactory;
  private final List<CursorImp> cursorImpList;
  private final Map<Integer, DocumentLine> linesMap;

  DocumentImp() {
    statementFactory = StatementFactory.createStatementFactory();
    documentFactory = DocumentFactory.createDocumentFactory();
    cursorImpList = new ArrayList<>();
    linesMap = new HashMap<>();
  }

  @VisibleForTesting
  public void guardAppendApis(Statement statement, Cursor cursor) {
    if (null == cursor) {
      throw new NullPointerException("cursor cannot be null.");
    }
    if (null == statement) {
      throw new NullPointerException("statement cannot be null");
    }
    if (!cursorImpList.contains(cursor)) {
      throw new IllegalArgumentException("cursor not from this document.");
    }
    if (!statementFactory.isSupportedStatement(statement)) {
      throw new IllegalArgumentException("unsupported statement.");
    }
  }

  private void addToDocumentStructure(Statement statement, Cursor cursor) {
    DocumentLine documentLine = documentFactory.createObjDocumentLine(statement, documentFactory.createComment(null));
    linesMap.put(cursor.getLineNumber(), documentLine);
  }

  @Override
  public Cursor createCursor() {
    CursorImp newCursorImp = new CursorImp(this);
    cursorImpList.add(newCursorImp);
    return newCursorImp;
  }

  @Override
  public DocumentLine peek(Cursor cursor) throws EmptyDocumentException {
    if (null == cursor) {
      throw new NullPointerException("cursor cannot be null.");
    }
    if (!cursorImpList.contains(cursor)) {
      throw new IllegalArgumentException("cursor not from this document.");
    }
    if (linesMap.size() == 0) {
      throw new EmptyDocumentException("the document is empty");
    }
    return linesMap.get(cursor.getLineNumber());
  }

  @Override
  public void append(GeoVertex geoVertex, Cursor cursor) {
    guardAppendApis(geoVertex, cursor);
    addToDocumentStructure(statementFactory.copyGeoVertex(geoVertex), cursor);
  }
  
  @Override
  public void append(GeoVertex geoVertex, String commentString, Cursor cursor) {
    // TODO Auto-generated method stub
    
  }

  @Override
  public void append(TexVertex texVertex, Cursor cursor) {
    guardAppendApis(texVertex, cursor);
    addToDocumentStructure(statementFactory.copyTexVertex(texVertex), cursor);
  }

  @Override
  public void append(NormalVertex normalVertex, Cursor cursor) {
    guardAppendApis(normalVertex, cursor);
    addToDocumentStructure(statementFactory.copyNormalVertex(normalVertex), cursor);
  }

  @Override
  public void append(ParamVertex paramVertex, Cursor cursor) {
    guardAppendApis(paramVertex, cursor);
    addToDocumentStructure(statementFactory.copyParamVertex(paramVertex), cursor);
  }

  @Override
  public void append(Point point, Cursor cursor) {
    guardAppendApis(point, cursor);
    addToDocumentStructure(statementFactory.copyPoint(point), cursor);
  }

  @Override
  public void append(Line line, Cursor cursor) {
    guardAppendApis(line, cursor);
    addToDocumentStructure(statementFactory.copyLine(line), cursor);
  }

  @Override
  public void append(Face face, Cursor cursor) {
    guardAppendApis(face, cursor);
    addToDocumentStructure(statementFactory.copyFace(face), cursor);
  }

  @Override
  public void append(CurveOrSurfaceType cstype, Cursor cursor) {
    guardAppendApis(cstype, cursor);
    addToDocumentStructure(statementFactory.copyCurveOrSurfaceType(cstype), cursor);
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
  public void append(CurveApproxCparmTechnique ctech, Cursor cursor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void append(CurveApproxCspaceTechnique ctech, Cursor cursor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void append(CurveApproxCurvTechnique ctech, Cursor cursor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void append(SurfaceApproxCparmaTechnique stech, Cursor cursor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void append(SurfaceApproxCparmbTechnique stech, Cursor cursor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void append(SurfaceApproxCspaceTechnique stech, Cursor cursor) {
    // TODO Auto-generated method stub

  }

  @Override
  public void append(SurfaceApproxCurvTechnique stech, Cursor cursor) {
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
    return linesMap.size();
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
