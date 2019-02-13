package com.ht.wfp3.api.statement;

/**
 * Polygonal geometry statement to enable or disable bevel interpolation.
 * 
 * bevel on/off
 * 
 * Polygonal geometry statement.
 * 
 * Sets bevel interpolation on or off. It works only with beveled objects, that is, objects with
 * sides separated by beveled faces.
 * 
 * Bevel interpolation uses normal vector interpolation to give an illusion of roundness to a flat
 * bevel. It does not affect the smoothing of non-bevelled faces.
 * 
 * Bevel interpolation does not alter the geometry of the original object.
 * 
 * on turns on bevel interpolation.
 * 
 * off turns off bevel interpolation. The default is off.
 * 
 * NOTE: Image cannot render bevel-interpolated elements that have vertex normals.
 * 
 * @author nickl
 *
 */
public interface Bevel extends Statement, Commentable {
  boolean isEnabled();
}
