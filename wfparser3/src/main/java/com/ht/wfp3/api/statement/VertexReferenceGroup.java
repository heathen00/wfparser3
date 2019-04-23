package com.ht.wfp3.api.statement;

/**
 * The Vertex Reference Numbers. Not an element but a component of elements such as Point, Line,
 * Face, ...
 * 
 * Referencing groups of vertices
 * 
 * Some elements, such as faces and surfaces, may have a triplet of numbers that reference vertex
 * data.These numbers are the reference numbers for a geometric vertex, a texture vertex, and a
 * vertex normal.
 * 
 * Each triplet of numbers specifies a geometric vertex, texture vertex, and vertex normal. The
 * reference numbers must be in order and must separated by slashes (/).
 * 
 * o The first reference number is the geometric vertex.
 * 
 * o The second reference number is the texture vertex. It follows the first slash.
 * 
 * o The third reference number is the vertex normal. It follows the second slash.
 * 
 * There is no space between numbers and the slashes. There may be more than one series of geometric
 * vertex/texture vertex/vertex normal numbers on a line.
 * 
 * What sort of reference numbers are used and by which elements?
 * 
 * Point: geometric vertex (required).
 * 
 * Line: geometric vertex (required), texture vertex (optional).
 * 
 * Face: geometric vertex (required), texture vertex (optional) normal vertex (optional).
 * 
 * Curve: geometric vertex (required). NOTE: referred to as "control point" in specification.
 * 
 * Curve2D: parameter vertex (required).
 * 
 * Surface: geometric vertex (required), texture vertex (optional), normal vertex (optional).
 * 
 * @author nickl
 *
 */
public interface VertexReferenceGroup {
  VertexReference getGeoVertexRef();

  VertexReference getTexVertexRef();

  VertexReference getNormalVertexRef();
  
  boolean equals(Object object);

  public int hashCode();
}
