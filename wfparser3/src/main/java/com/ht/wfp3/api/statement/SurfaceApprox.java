package com.ht.wfp3.api.statement;

/**
 * The surface approximation technique free-form geometry statement.
 * 
 * stech technique resolution
 * 
 * Free-form geometry statement.
 * 
 * Specifies a surface approximation technique. The arguments specify the technique and resolution
 * for the surface.
 * 
 * You must select from one of the following techniques:
 * 
 * stech cparma ures vres
 * 
 * Specifies a surface with constant parametric subdivision using separate resolution parameters for
 * the u and v directions. Each patch of the surface is subdivided n times in parameter space, where
 * n is the resolution parameter multiplied by the degree of the surface.
 * 
 * ures is the resolution parameter for the u direction.
 * 
 * vres is the resolution parameter for the v direction.
 * 
 * The larger the values for ures and vres, the finer the resolution. If you enter a value of 0 for
 * both ures and vres, each patch is approximated by two triangles.
 * 
 * stech cparmb uvres
 * 
 * Specifies a surface with constant parametric subdivision, with refinement using one resolution
 * parameter for both the u and v directions.
 * 
 * An initial triangulation is performed using only the points on the trimming curves. This
 * triangulation is then refined until all edges are of an appropriate length. The resulting
 * triangles are not oriented along isoparametric lines as they are in the cparma technique.
 * 
 * uvres is the resolution parameter for both the u and v directions. The larger the value, the
 * finer the resolution.
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
 * stech curv maxdist maxangle
 * 
 * Specifies a surface with curvature-dependent subdivision using separate resolution parameters for
 * the maximum distance and the maximum angle.
 * 
 * The surface is subdivided in rectangular regions until 1) the distance in real space between the
 * approximating rectangle and the actual surface is less than the maxdist (approximately) and 2)
 * the angle in degrees between surface normals at the corners of the rectangle is less than the
 * maxangle. Following subdivision, the regions are triangulated.
 * 
 * maxdist is the distance in real space between the approximating rectangle and the actual surface.
 * 
 * maxangle is the angle in degrees between surface normals at the corners of the rectangle.
 * 
 * The smaller the values for maxdist and maxangle, the finer the resolution.
 * 
 * @author nickl
 *
 */
interface SurfaceApprox extends Statement {
  enum Technique {
    CPARMA, CPARMB, CSPACE, CURV
  }

  SurfaceApprox.Technique getSurfaceApproximationTechnique();
}
