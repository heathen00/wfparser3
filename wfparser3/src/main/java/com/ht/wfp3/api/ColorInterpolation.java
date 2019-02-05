package com.ht.wfp3.api;

/**
 * The color interpolation polygonal geometry statement.
 * 
 * c_interp on/off
 * 
 * Polygonal geometry statement.
 * 
 * Sets color interpolation on or off.
 * 
 * Color interpolation creates a blend across the surface of a polygon between the materials
 * assigned to its vertices. This creates a blending of colors across a face element.
 * 
 * To support color interpolation, materials must be assigned per vertex, not per element. The
 * illumination models for all materials of vertices attached to the polygon must be the same. Color
 * interpolation applies to the values for ambient (Ka), diffuse (Kd), specular (Ks), and specular
 * highlight (Ns) material properties.
 * 
 * on turns on color interpolation.
 * 
 * off turns off color interpolation. The default is off.
 * 
 * @author nickl
 *
 */
public interface ColorInterpolation extends Statement, Commentable {
  boolean isEnabled();
}
