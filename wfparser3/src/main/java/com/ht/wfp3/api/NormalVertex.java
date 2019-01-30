package com.ht.wfp3.api;

/**
 * The Normal Vertex data.
 * 
 * Polygonal and free-form geometry statement.
 * 
 * Specifies a normal vector with components i, j, and k.
 * 
 * Vertex normals affect the smooth-shading and rendering of geometry. For polygons, vertex normals
 * are used in place of the actual facet normals. For surfaces, vertex normals are interpolated over
 * the entire surface and replace the actual analytic surface normal.
 * 
 * When vertex normals are present, they supersede smoothing groups.
 * 
 * i j k are the i, j, and k coordinates for the vertex normal. They are floating point numbers.
 *
 * The vertex data is represented by four vertex lists; one for each type of vertex coordinate. A
 * right-hand coordinate system is used to specify the coordinate locations.
 * 
 * When vertices are loaded into the Advanced Visualizer, they are sequentially numbered, starting
 * with 1. These reference numbers are used in element statements.
 * 
 * @author nickl
 *
 */
public interface NormalVertex extends Node {
  String getICoord();

  String getJCoord();

  String getKCoord();
}
