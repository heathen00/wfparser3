package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Axis;
import com.ht.wfp3.api.statement.BasisMatrix;
import com.ht.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
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
    Matrix matrix = matrixBuilder.clear().rowByRow() //
        .append(BigDecimal.valueOf(1.1d)).append(BigDecimal.valueOf(2.2d)).end().build();
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
    EqualsHashCodeAndCompareToTester equalsHashCodeAndCompareToTester =
        EqualsHashCodeAndCompareToTester.createEqualsHashCodeAndCompareToTester();
    BasisMatrix first;
    BasisMatrix second;

    first = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());
    second = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenEqual(first, second);

    equalsHashCodeAndCompareToTester.assertEqualsSelf(first);

    // different axes, same matrix.
    first = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());
    second = statementFactory.createBasisMatrix(Axis.V, buildDefaultMatrix());

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // same axes, different matrix.
    Matrix firstMatrix = matrixBuilder.clear().rowByRow() //
        .append(BigDecimal.valueOf(1.1d)).append(BigDecimal.valueOf(1.1d))
        .append(BigDecimal.valueOf(1.1d)).end().build();
    Matrix secondMatrix = matrixBuilder.clear().rowByRow() //
        .append(BigDecimal.valueOf(2.2d)).append(BigDecimal.valueOf(2.2d))
        .append(BigDecimal.valueOf(2.2d)).end().build();
    first = statementFactory.createBasisMatrix(Axis.V, firstMatrix);
    second = statementFactory.createBasisMatrix(Axis.V, secondMatrix);

    equalsHashCodeAndCompareToTester.assertContractRespectedWhenNotEqual(true, first, second);

    // equals null
    first = statementFactory.createBasisMatrix(Axis.U, buildDefaultMatrix());

    equalsHashCodeAndCompareToTester.assertDoesNotEqualNull(first);
  }

  @Test
  public void BasisMatrix_copyMaliciousMutableBasisMatrix_validImmutableBasisMatrixIsCreated() {
    fail("Not yet implemented");
  }

  // TODO what about empty matrix?
}
