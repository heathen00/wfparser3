package com.ht.wfp3.api.statement;

import java.math.BigDecimal;

/**
 * A sub-statement that represents a two dimensional matrix used by the Basis Matrix (bmat)
 * statement.
 * 
 * @author nickl
 *
 */
public interface Matrix extends Comparable<Matrix> {
  public static final BigDecimal DEFAULT_ELEMENT = BigDecimal.valueOf(0.0d);

  BigDecimal getElementAt(int row, int column);

  int getNumRows();

  int getNumColumns();

  int hashCode();

  boolean equals(Object obj);
}
