package com.ht.wfp3.api;

import java.util.List;

/**
 * The Face Polygon element.
 * 
 * f v1/vt1/vn1 v2/vt2/vn2 v3/vt3/vn3 . . .
 * 
 * Polygonal geometry statement.
 * 
 * Specifies a face element and its vertex reference number. You can optionally include the texture
 * vertex and vertex normal reference numbers.
 * 
 * The reference numbers for the vertices, texture vertices, and vertex normals must be separated by
 * slashes (/). There is no space between the number and the slash.
 * 
 * v is the reference number for a vertex in the face element. A minimum of three vertices are
 * required.
 * 
 * vt is an optional argument.
 * 
 * vt is the reference number for a texture vertex in the face element. It always follows the first
 * slash.
 * 
 * vn is an optional argument.
 * 
 * vn is the reference number for a vertex normal in the face element. It must always follow the
 * second slash.
 * 
 * Face elements use surface normals to indicate their orientation. If vertices are ordered
 * counterclockwise around the face, both the face and the normal will point toward the viewer. If
 * the vertex ordering is clockwise, both will point away from the viewer. If vertex normals are
 * assigned, they should point in the general direction of the surface normal, otherwise
 * unpredictable results may occur.
 * 
 * If a face has a texture map assigned to it and no texture vertices are assigned in the f
 * statement, the texture map is ignored when the element is rendered.
 * 
 * NOTE: Any references to fo (face outline) are no longer valid as of version 2.11. You can use f
 * (face) to get the same results. References to fo in existing .obj files will still be read,
 * however, they will be written out as f when the file is saved.
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
public interface Face {

  void appendReferenceNumbers(VertexReferenceGroup referenceNumbers);

  List<VertexReferenceGroup> getReferenceNumbers();

}
