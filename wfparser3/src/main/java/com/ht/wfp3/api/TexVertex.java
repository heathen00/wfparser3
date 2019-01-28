package com.ht.wfp3.api;

/**
 * The Texture Vertex.
 * 
 * vt u v w
 * 
 * Vertex statement for both polygonal and free-form geometry.
 * 
 * Specifies a texture vertex and its coordinates. A 1D texture requires only u texture coordinates,
 * a 2D texture requires both u and v texture coordinates, and a 3D texture requires all three
 * coordinates.
 * 
 * u is the value for the horizontal direction of the texture.
 * 
 * v is an optional argument.
 * 
 * v is the value for the vertical direction of the texture. The default is 0.
 * 
 * w is an optional argument.
 * 
 * w is a value for the depth of the texture. The default is 0.
 * 
 * 
 * @author nickl
 *
 */
public interface TexVertex extends Element {
  String getUCoord();

  String getVCoord();

  String getWCoord();
}
