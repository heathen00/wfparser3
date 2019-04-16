package com.ht.wfp3.api.statement;

/**
 * The Point Polygon element.
 * 
 * p v1 v2 v3 . . .
 * 
 * Polygonal geometry statement.
 * 
 * Specifies a point element and its vertex. You can specify multiple points with this statement.
 * Although points cannot be shaded or rendered, they are used by other Advanced Visualizer
 * programs.
 * 
 * v is the vertex reference number for a point element. Each point element requires one vertex.
 * Positive values indicate absolute vertex numbers. Negative values indicate relative vertex
 * numbers.
 * 
 * For all elements, reference numbers are used to identify geometric vertices, texture vertices,
 * vertex normals, and parameter space vertices.
 * 
 * Each of these types of vertices is numbered separately, starting with 1. This means that the
 * first geometric vertex in the file is 1, the second is 2, and so on. The first texture vertex in
 * the file is 1, the second is 2, and so on. The numbering continues sequentially throughout the
 * entire file. Frequently, files have multiple lists of vertex data. This numbering sequence
 * continues even when vertex data is separated by other data.
 * 
 * In addition to counting vertices down from the top of the first list in the file, you can also
 * count vertices back up the list from an element's position in the file. When you count up the
 * list from an element, the reference numbers are negative. A reference number of -1 indicates the
 * vertex immediately above the element. A reference number of -2 indicates two references above and
 * so on.
 * 
 * 
 * @author nickl
 *
 */
public interface Point extends Statement, UsesVertexReferenceGroups {

}
