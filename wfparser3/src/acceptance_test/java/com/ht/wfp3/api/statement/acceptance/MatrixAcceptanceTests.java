package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import java.math.BigDecimal;
import org.junit.Before;
import org.junit.Test;
import com.ht.wfp3.api.statement.Matrix;
import com.ht.wfp3.api.statement.MatrixBuilder;
import com.ht.wfp3.api.statement.StatementFactory;

public class MatrixAcceptanceTests {
  private enum BuildMethod {
    COL_BY_COL, ROW_BY_ROW,
  }

  private StatementFactory statementFactory;
  private MatrixBuilder matrixBuilder;

  private Matrix addDataFromArrays(double[][] arrayData, MatrixBuilder matrixBuilder) {
    for (int i = 0; i < arrayData.length; i++) {
      for (int j = 0; j < arrayData[i].length; j++) {
        matrixBuilder.append(BigDecimal.valueOf(arrayData[i][j]));
      }
      matrixBuilder.end();
    }
    return matrixBuilder.build();
  }

  private void assertValidMatrixDataRowByRow(BuildMethod expectedBuildMethod,
      double[][] expectedMatrixData, Matrix matrix) {
    int longestMinor = 0;
    for (int i = 0; i < expectedMatrixData.length; i++) {
      longestMinor = (expectedMatrixData[i].length > longestMinor ? expectedMatrixData[i].length
          : longestMinor);
    }
    for (int i = 0; i < expectedMatrixData.length; i++) {
      for (int j = 0; j < longestMinor; j++) {
        BigDecimal expectedElement = (j >= expectedMatrixData[i].length ? BigDecimal.valueOf(0.0d)
            : BigDecimal.valueOf(expectedMatrixData[i][j]));
        if (expectedBuildMethod.equals(BuildMethod.ROW_BY_ROW)) {
          assertEquals(expectedElement, matrix.getElementAt(i, j));
        } else if (expectedBuildMethod.equals(BuildMethod.COL_BY_COL)) {
          assertEquals(expectedElement, matrix.getElementAt(j, i));
        }
      }
    }
  }

  private void assertValidMatrix(BuildMethod expectedBuildMethod, double[][] expectedMatrixData,
      Matrix matrix) {
    final int expectedNumRows =
        (expectedBuildMethod.equals(BuildMethod.ROW_BY_ROW) ? expectedMatrixData.length
            : expectedMatrixData[0].length);
    final int expectedNumCols =
        (expectedBuildMethod.equals(BuildMethod.ROW_BY_ROW) ? expectedMatrixData[0].length
            : expectedMatrixData.length);

    assertNotNull(matrix);
    assertEquals(expectedNumRows, matrix.getNumRows());
    assertEquals(expectedNumCols, matrix.getNumColumns());
    assertValidMatrixDataRowByRow(expectedBuildMethod, expectedMatrixData, matrix);
  }

  @Before
  public void setup() {
    statementFactory = StatementFactory.createStatementFactory();
    matrixBuilder = statementFactory.createMatrixBuilder();
  }

  @Test
  public void Matrix_createMatrixBuilder_matrixBuilderIsCreated() {
    assertNotNull(matrixBuilder);
  }

  @Test
  public void Matrix_buildMatrixButDoNotSetBuildMethod_matrixIsBuiltRowByRow() {
    final double[][] matrixData = { //
        {1.1d, 2.1d, 3.1d}, //
        {2.1d, 2.2d, 2.3d}, //
        {3.1d, 3.2d, 3.3d}, //
        {4.1d, 4.2d, 4.3d}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder);

    assertValidMatrix(BuildMethod.ROW_BY_ROW, matrixData, matrix);
  }

  @Test
  public void Matrix_buildEmptyMatrixRowByRow_emptyMatrixIsBuilt() {
    final int expectedNumRows = 0;
    final int expectedNumCols = 0;
    Matrix matrix = matrixBuilder.rowByRow().build();

    assertNotNull(matrix);
    assertEquals(expectedNumRows, matrix.getNumRows());
    assertEquals(expectedNumCols, matrix.getNumColumns());
  }

  @Test
  public void Matrix_buildMatrixRowByRowWithOneElement_matrixWithOneElementIsBuilt() {
    final double[][] matrixData = { //
        {100.100d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.rowByRow());

    assertValidMatrix(BuildMethod.ROW_BY_ROW, matrixData, matrix);
  }

  @Test
  public void Matrix_buildMatrixRowByRowWithOneRowOfElements_matrixWithOneRowOfElementsIsBuilt() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d, 500.100d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.rowByRow());

    assertValidMatrix(BuildMethod.ROW_BY_ROW, matrixData, matrix);
  }

  @Test
  public void Matrix_buildMatrixRowByRowWithMultipleRowsAndColumns_matrixWithMultipleRowsAndColumnsIsBuilt() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d, 200.200d, 300.200d, 400.200d,}, //
        {100.300d, 200.300d, 300.300d, 400.300d,}, //
        {100.400d, 200.400d, 300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.rowByRow());

    assertValidMatrix(BuildMethod.ROW_BY_ROW, matrixData, matrix);
  }

  @Test
  public void Matrix_clearMatrixBuilderAfterBuildingRowByRow_MatrixBuilderIsCleared() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d, 200.200d, 300.200d, 400.200d,}, //
        {100.300d, 200.300d, 300.300d, 400.300d,}, //
        {100.400d, 200.400d, 300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };
    final int expectedNumRows = 0;
    final int expectedNumCols = 0;
    addDataFromArrays(matrixData, matrixBuilder.rowByRow());

    Matrix matrix = matrixBuilder.clear().build();

    assertNotNull(matrix);
    assertEquals(expectedNumRows, matrix.getNumRows());
    assertEquals(expectedNumCols, matrix.getNumColumns());
  }

  @Test
  public void Matrix_buildEmptyMatrixColumnByColumn_emptyMatrixIsBuilt() {
    final int expectedNumRows = 0;
    final int expectedNumCols = 0;
    Matrix matrix = matrixBuilder.colByCol().build();

    assertNotNull(matrix);
    assertEquals(expectedNumRows, matrix.getNumRows());
    assertEquals(expectedNumCols, matrix.getNumColumns());
  }

  @Test
  public void Matrix_buildMatrixColumnByColumnWithOneElement_matrixWithOneElementIsBuilt() {
    final double[][] matrixData = { //
        {100.100d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.colByCol());

    assertValidMatrix(BuildMethod.COL_BY_COL, matrixData, matrix);
  }

  @Test
  public void Matrix_buildMatrixColumnByColumnWithOneColumnOfElements_matrixWithOneColumnIsBuilt() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d, 500.100d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.colByCol());

    assertValidMatrix(BuildMethod.COL_BY_COL, matrixData, matrix);
  }

  @Test
  public void Matrix_buildMatrixColumnByColumnWithMultipleRowsAndColumns_matrixWithMultipleRowsAndColumnsIsBuilt() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d, 200.200d, 300.200d, 400.200d,}, //
        {100.300d, 200.300d, 300.300d, 400.300d,}, //
        {100.400d, 200.400d, 300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.colByCol());

    assertValidMatrix(BuildMethod.COL_BY_COL, matrixData, matrix);
  }

  @Test
  public void Matrix_clearMatrixBuilderAfterBuildingColumnByColumn_matrixBuilderIsCleared() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d, 200.200d, 300.200d, 400.200d,}, //
        {100.300d, 200.300d, 300.300d, 400.300d,}, //
        {100.400d, 200.400d, 300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };
    final int expectedNumRows = 0;
    final int expectedNumCols = 0;
    addDataFromArrays(matrixData, matrixBuilder.colByCol());

    Matrix matrix = matrixBuilder.clear().build();

    assertNotNull(matrix);
    assertEquals(expectedNumRows, matrix.getNumRows());
    assertEquals(expectedNumCols, matrix.getNumColumns());
  }

  @Test(expected = IllegalArgumentException.class)
  public void Matrix_accessElementInEmptyMatrix_illegalArgumentExceptionIsThrown() {
    Matrix matrix = matrixBuilder.clear().rowByRow().build();

    matrix.getElementAt(1, 200);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Matrix_accessElementOutsideRowRange_illegalArgumentExceptionIsThrown() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d, 200.200d, 300.200d, 400.200d,}, //
        {100.300d, 200.300d, 300.300d, 400.300d,}, //
        {100.400d, 200.400d, 300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.colByCol());

    matrix.getElementAt(100, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void Matrix_accessElementOutsideColumnRange_illegalArgumentExceptionIsThrown() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d, 200.200d, 300.200d, 400.200d,}, //
        {100.300d, 200.300d, 300.300d, 400.300d,}, //
        {100.400d, 200.400d, 300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.colByCol());

    matrix.getElementAt(2, 350);
  }

  @Test
  public void Matrix_buildMatrixRowByRowWhereRowsHaveDifferentNumberOfElements_matrixBuiltWhereRowsAllEqualLength() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d,}, //
        {100.300d, 200.300d, 400.300d,}, //
        {300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.rowByRow());

    assertValidMatrix(BuildMethod.ROW_BY_ROW, matrixData, matrix);
  }

  @Test
  public void Matrix_buildMatrixColumnByColumnWhereColumnsHaveDifferentNumberOfElements_matrixBuiltWhereColumnsAllEqualLength() {
    final double[][] matrixData = { //
        {100.100d, 200.100d, 300.100d, 400.100d,}, //
        {100.200d,}, //
        {100.300d, 200.300d, 400.300d,}, //
        {300.400d, 400.400d,}, //
        {100.500d, 200.500d, 300.500d, 400.500d,}, //
    };

    Matrix matrix = addDataFromArrays(matrixData, matrixBuilder.colByCol());

    assertValidMatrix(BuildMethod.COL_BY_COL, matrixData, matrix);
  }

  @Test
  public void Matrix_buildMatrixRowByRowWithEmptyRows_matrixBuiltEmptyRowFilled() {
    final double[][] expectedMatrixData = { //
        {BigDecimal.ZERO.doubleValue(), BigDecimal.ZERO.doubleValue()}, //
        {BigDecimal.ZERO.doubleValue(), BigDecimal.ZERO.doubleValue()}, //
        {3.3d, 4.4d}, //
    };
    Matrix matrix = matrixBuilder.rowByRow().end().end().append(BigDecimal.valueOf(3.3d))
        .append(BigDecimal.valueOf(4.4d)).end().build();

    assertValidMatrix(BuildMethod.ROW_BY_ROW, expectedMatrixData, matrix);
  }

  @Test
  public void Matrix_builtMatrixColumnByColumnWithEmptyColumns_matrixBuiltEmptyColumnsFilled() {
    final double[][] expectedMatrixData = { //
        {BigDecimal.ZERO.doubleValue(), BigDecimal.ZERO.doubleValue()}, //
        {BigDecimal.ZERO.doubleValue(), BigDecimal.ZERO.doubleValue()}, //
        {3.3d, 4.4d}, //
    };
    Matrix matrix = matrixBuilder.colByCol().end().end().append(BigDecimal.valueOf(3.3d))
        .append(BigDecimal.valueOf(4.4d)).end().build();

    assertValidMatrix(BuildMethod.COL_BY_COL, expectedMatrixData, matrix);
  }



  // What happens when you use / do not use "endRow()" at the end of the last row?
  // What about building multiple matrices with the same matrixbuilder?
  // TODO equals, hashCode, and compareTo
  // TODO What about trying to add a mutable Matrix to BasisMatrix. Should not be allowed!
  // build identity.
  // configure fill value for empty elements.


  @Test
  public void test() {
    fail("Not yet implemented");
  }

}
