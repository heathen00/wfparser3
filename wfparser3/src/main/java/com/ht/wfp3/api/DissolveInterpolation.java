package com.ht.wfp3.api;

/**
 * The dissolve interpolation polygonal geometry statement.
 * 
 * d_interp on/off
 * 
 * Polygonal geometry statement.
 * 
 * Sets dissolve interpolation on or off.
 * 
 * Dissolve interpolation creates an interpolation or blend across a polygon between the dissolve
 * (d) values of the materials assigned to its vertices. This feature is used to create effects
 * exhibiting varying degrees of apparent transparency, as in glass or clouds.
 * 
 * To support dissolve interpolation, materials must be assigned per vertex, not per element. All
 * the materials assigned to the vertices involved in the dissolve interpolation must contain a
 * dissolve factor command to specify a dissolve.
 * 
 * on turns on dissolve interpolation.
 * 
 * off turns off dissolve interpolation. The default is off.
 * 
 * 
 * @author nickl
 *
 */
public interface DissolveInterpolation extends Node, Commentable {
  boolean isEnabled();
}
