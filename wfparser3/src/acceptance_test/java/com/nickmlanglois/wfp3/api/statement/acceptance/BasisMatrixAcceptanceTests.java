package com.nickmlanglois.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.wfp3.api.statement.Axis;
import com.nickmlanglois.wfp3.api.statement.BasisMatrix;
import com.nickmlanglois.wfp3.api.statement.EqualsHashCodeAndCompareToTester;
import com.nickmlanglois.wfp3.api.statement.Matrix;
import com.nickmlanglois.wfp3.api.statement.MatrixBuilder;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester;
import com.nickmlanglois.wfp3.api.statement.StatementFactory;
import com.nickmlanglois.wfp3.api.statement.MutabilityTester.Creator;

public class BasisMatrixAcceptanceTests {
  private static final String BASIS_MATRIX_KEYWORD = "bmat";

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void BasisMatrix_createBasisMatrixWithNullAxisParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("axis cannot be null");

    Matrix matrix = buildDefaultMatrix();

    statementFactory.createBasisMatrix(null, matrix);
  }

  @Test
  public void BasisMatrix_createBasisMatrixWithNullMatrixParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("matrix cannot be null");

    statementFactory.createBasisMatrix(Axis.U, null);
  }

  @Test
  public void BasisMatrix_copyBasisMatrixWithNullBasisMatrixParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("bmat cannot be null");

    statementFactory.copyBasisMatrix(null);
  }

  @Test
  public void BasisMatrix_createBasisMatrixWithValidParameters_validNonNullBasisMatrixIsCreatedWithSpecifiedParameters() {
    Axis axis = Axis.V;
    Matrix matrix = buildDefaultMatrix();

    BasisMatrix basisMatrix = statementFactory.createBasisMatrix(axis, matrix);

    assertNotNull(basisMatrix);
    assertEquals(BASIS_MATRIX_KEYWORD, basisMatrix.getKeyword());
    assertEquals(axis, basisMatrix.getAxis());
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
  public void BasisMatrix_checkMutableDefensiveCopy_validImmutableInstanceIsCreated()
      throws Exception {
    final Matrix matrix =
        matrixBuilder.clear().rowByRow().append(BigDecimal.valueOf(2.2d)).end().build();
    final MutabilityTester<BasisMatrix> mutabilityTester =
        new MutabilityTester<BasisMatrix>(new Creator<BasisMatrix>() {

          @Override
          public BasisMatrix create() {
            return statementFactory.createBasisMatrix(Axis.U, mutable(matrix));
          }

          @Override
          public BasisMatrix copy(BasisMatrix o) {
            return statementFactory.copyBasisMatrix(mutable(o));
          }

          @Override
          public Map<String, Object> getExpectedMemberData() {
            Map<String, Object> methodDataMap = new HashMap<>();
            methodDataMap.put("getMatrix", matrix);
            return methodDataMap;
          }
        });
    mutabilityTester.assertImmutability();
  }

  // TODO what about empty matrix?
}
