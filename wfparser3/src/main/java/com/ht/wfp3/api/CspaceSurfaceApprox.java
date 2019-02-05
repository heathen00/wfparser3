package com.ht.wfp3.api;

/**
 * The constant spatial division surface approximation technique.
 * 
 * stech cspace maxlength
 * 
 * Specifies a surface with constant spatial subdivision.
 * 
 * The surface is subdivided in rectangular regions until the length in real space of any rectangle
 * edge is less than the maxlength. These rectangular regions are then triangulated.
 * 
 * maxlength is the length in real space of any rectangle edge. The smaller the value, the finer the
 * resolution.
 * 
 * @author nickl
 *
 */
public interface CspaceSurfaceApprox extends SurfaceApprox {
  String getMaxLength();
}
