package com.ht.wfp3.api;

import java.util.List;

/**
 * The Line Polygon element.
 * 
 * l v1/vt1 v2/vt2 v3/vt3 . . .
 * 
 * Polygonal geometry statement.
 * 
 * Specifies a line and its vertex reference numbers. You can optionally include the texture vertex
 * reference numbers. Although lines cannot be shaded or rendered, they are used by other Advanced
 * Visualizer programs.
 * 
 * The reference numbers for the vertices and texture vertices must be separated by a slash (/).
 * There is no space between the number and the slash.
 * 
 * v is a reference number for a vertex on the line. A minimum of two vertex numbers are required.
 * There is no limit on the maximum. Positive values indicate absolute vertex numbers. Negative
 * values indicate relative vertex numbers.
 * 
 * vt is an optional argument.
 * 
 * vt is the reference number for a texture vertex in the line element. It must always follow the
 * first slash.
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
 * @author nickl
 *
 */
public interface Line extends Element {

  void appendReferenceNumbers(ReferenceNumbers referenceNumbers);

  List<ReferenceNumbers> getReferenceNumbers();

}
