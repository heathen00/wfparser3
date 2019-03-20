package com.ht.wfp3.api.statement;

/**
 * The curve approximation technique free-form geometry statement.
 * 
 * ctech technique resolution
 * 
 * Free-form geometry statement.
 * 
 * Specifies a curve approximation technique. The arguments specify the technique and resolution for
 * the curve.
 * 
 * You must select from one of the following three techniques.
 * 
 * ctech cparm res
 * 
 * Specifies a curve with constant parametric subdivision using one resolution parameter. Each
 * polynomial segment of the curve is subdivided n times in parameter space, where n is the
 * resolution parameter multiplied by the degree of the curve.
 * 
 * res is the resolution parameter. The larger the value, the finer the resolution. If res has a
 * value of 0, each polynomial curve segment is represented by a single line segment.
 * 
 * ctech cspace maxlength
 * 
 * Specifies a curve with constant spatial subdivision. The curve is approximated by a series of
 * line segments whose lengths in real space are less than or equal to the maxlength.
 * 
 * maxlength is the maximum length of the line segments. The smaller the value, the finer the
 * resolution.
 * 
 * ctech curv maxdist maxangle
 * 
 * Specifies curvature-dependent subdivision using separate resolution parameters for the maximum
 * distance and the maximum angle.
 * 
 * The curve is approximated by a series of line segments in which 1) the distance in object space
 * between a line segment and the actual curve must be less than the maxdist parameter and 2) the
 * angle in degrees between tangent vectors at the ends of a line segment must be less than the
 * maxangle parameter.
 * 
 * maxdist is the distance in real space between a line segment and the actual curve.
 * 
 * maxangle is the angle (in degrees) between tangent vectors at the ends of a line segment.
 * 
 * The smaller the values for maxdist and maxangle, the finer the resolution.
 * 
 * NOTE: Approximation information for trimming, hole, and special curves is stored in the
 * corresponding surface. The ctech statement for the surface is used, not the ctech statement
 * applied to the curv2 statement. Although untrimmed surfaces have no explicit trimming loop, a
 * loop is constructed which bounds the legal parameter range. This implicit loop follows the same
 * rules as any other loop and is approximated according to the ctech information for the surface.
 * 
 * @author nickl
 *
 */
public interface CurveApprox extends Statement {
  enum Technique {
    CPARM, CSPACE, CURV
  }

  CurveApprox.Technique getCurveApproximationTechnique();
}
