package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Axis;
import com.ht.wfp3.api.statement.BasisMatrix;
import com.ht.wfp3.api.statement.Matrix;
import com.ht.wfp3.api.statement.MatrixBuilder;
import com.ht.wfp3.api.statement.StatementFactory;

public class BasisMatrixAcceptanceTests {
  private static final String BASIS_MATRIX_KEYWORD = "bmat";

  private StatementFactory statementFactory;

  private MatrixBuilder matrixBuilder;

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
    matrixBuilder = statementFactory.createMatrixBuilder();
  }

  private Matrix buildDefaultMatrix() {
    Matrix matrix = matrixBuilder.clear().buildRowByRow() //
        .append(BigDecimal.valueOf(1.1d)).append(BigDecimal.valueOf(2.2d)).endRow().build();
    return matrix;
  }

  @Test(expected = NullPointerException.class)
  public void BasisMatrix_createBasisMatrixWithNullAxisParameter_nullPointerExceptionIsThrown() {
    Matrix matrix = buildDefaultMatrix();

    statementFactory.createBasisMatrix(null, matrix);
  }

  @Test(expected = NullPointerException.class)
  public void BasisMatrix_createBasisMatrixWithNullMatrixParameter_nullPointerExceptionIsThrown() {
    statementFactory.createBasisMatrix(Axis.U, null);
  }

  @Test(expected = NullPointerException.class)
  public void BasisMatrix_copyBasisMatrixWithNullBasisMatrixParameter_nullPointerExceptionIsThrown() {
    statementFactory.copyBasisMatrix(null);
  }

  @Test
  public void BasisMatrix_createBasisMatrixWithValidParameters_validNonNullBasisMatrixIsCreatedWithSpecifiedParameters() {
    Axis axis = Axis.V;
    Matrix matrix = buildDefaultMatrix();

    BasisMatrix basisMatrix = statementFactory.createBasisMatrix(axis, matrix);

    assertNotNull(basisMatrix);
    assertEquals(BASIS_MATRIX_KEYWORD, basisMatrix.getKeyword());
    assertEquals(axis, basisMatrix.getBasisMatrixAxis());
    assertEquals(matrix, basisMatrix.getMatrix());
  }

  @Test
  public void BasisMatrix_copyBasisMatrixWithValidParameter_validNonNullCopyBasisMatrixIsCreated() {
    Axis axis = Axis.U;
    Matrix matrix = buildDefaultMatrix();

    BasisMatrix originalBasisMatrix = statementFactory.createBasisMatrix(axis, matrix);
    BasisMatrix copiedBasisMatrix = statementFactory.copyBasisMatrix(originalBasisMatrix);

    assertNotNull(copiedBasisMatrix);
    assertFalse(copiedBasisMatrix == originalBasisMatrix);
    assertEquals(originalBasisMatrix, copiedBasisMatrix);
  }

  @Test
  public void BasisMatrix_exerciseAllVariantsOfEqualsHashCodeAndCompareTo_equalsHashCodeAndCompareToContractsRespected() {
    BasisMatrix first;
    BasisMatrix second;

    // Equality.
    first = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());
    second = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());

    assertTrue(first.equals(second));
    assertTrue(second.equals(first));
    assertTrue(first.compareTo(second) == 0);
    assertTrue(second.compareTo(first) == 0);
    assertTrue(first.hashCode() == second.hashCode());

    assertTrue(first.equals(first));
    assertTrue(first.compareTo(first) == 0);

    // different axes, same matrix.
    first = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());
    second = statementFactory.createBasisMatrix(Axis.V, buildDefaultMatrix());

    assertFalse(first.equals(second));
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
    assertFalse(first.hashCode() == second.hashCode());

    // same axes, different matrix.
    Matrix firstMatrix = matrixBuilder.clear().buildRowByRow() //
        .append(BigDecimal.valueOf(1.1d)).append(BigDecimal.valueOf(1.1d))
        .append(BigDecimal.valueOf(1.1d)).endRow().build();
    Matrix secondMatrix = matrixBuilder.clear().buildRowByRow() //
        .append(BigDecimal.valueOf(2.2d)).append(BigDecimal.valueOf(2.2d))
        .append(BigDecimal.valueOf(2.2d)).endRow().build();
    first = statementFactory.createBasisMatrix(Axis.V, firstMatrix);
    second = statementFactory.createBasisMatrix(Axis.V, secondMatrix);

    assertFalse(first.equals(second));
    assertTrue(first.compareTo(second) < 0);
    assertTrue(second.compareTo(first) > 0);
    assertFalse(first.hashCode() == second.hashCode());

    // equals null
    first = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());
    assertFalse(first.equals(null));
  }

  @Test
  public void BasisMatrix_copyMaliciousMutableBasisMatrix_validImmutableBasisMatrixIsCreated() {
    fail("Not yet implemented");
  }

  // TODO what about empty matrix?
  // TODO what about null in compareTo()?
}
