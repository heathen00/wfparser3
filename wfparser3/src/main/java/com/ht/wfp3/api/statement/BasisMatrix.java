package com.ht.wfp3.api.statement;

/**
 * Basis Matrix in U and V Axes for Basis Matrix Free Form Curves and Surfaces.
 * 
 * bmat u matrix
 * 
 * bmat v matrix
 * 
 * Free-form geometry statement.
 * 
 * Sets the basis matrices used for basis matrix curves and surfaces. The u and v values must be
 * specified in separate bmat statements.
 * 
 * NOTE: The deg statement must be given before the bmat statements and the size of the matrix must
 * be appropriate for the degree.
 * 
 * u specifies that the basis matrix is applied in the u direction.
 * 
 * v specifies that the basis matrix is applied in the v direction.
 * 
 * matrix lists the contents of the basis matrix with column subscript j varying the fastest. If n
 * is the degree in the given u or v direction, the matrix (i,j) should be of size (n + 1) x (n +
 * 1).
 * 
 * There is no default. A value must be supplied.
 * 
 * NOTE: The arrangement of the matrix is different from that commonly found in other references.
 * For more information, see the examples at the end of this section and also the section,
 * "Mathematics for free-form curves and surfaces."
 * 
 * @author nickl
 *
 */
public interface BasisMatrix extends Comparable<BasisMatrix>, Statement {
  Axis getBasisMatrixAxis();

  Matrix getMatrix();
}
