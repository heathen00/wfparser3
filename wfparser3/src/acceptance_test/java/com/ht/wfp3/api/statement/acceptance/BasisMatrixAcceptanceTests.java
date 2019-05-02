package com.ht.wfp3.api.statement.acceptance;

import static org.junit.Assert.*;
import org.junit.Test;

public class BasisMatrixAcceptanceTests {

  @Test
  public void BasisMatrix_createBasisMatrixWithNullAxisParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_createBasisMatrixWithNullMatrixParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_copyBasisMatrixWithNullBasisMatrixParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_createBasisMatrixWithValidParameters_validNonNullBasisMatrixIsCreatedWithSpecifiedParameters() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_copyBasisMatrixWithValidParameter_validNonNullCopyBasisMatrixIsCreated() {
    // TODO Ensure NOT the same instance.
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_createTwoBasisMatricesWithEqualParameters_equalAndHashCodesAreEqual() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_createTwoBasisMatricesWithSameAxisButDifferentMatrixParameters_notEqualAndHashCodesAreNotEqual() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_createTwoBasisMatricesWithDifferentAxisButEqualMatrixParameters_notEqualAndHashCodesAreNotEqual() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_compareToTwoEqualBasisMatrices_zeroIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_compareToTwoBasisMatricesWithSameAxisButFirstHasHigherMatrixCompareToValue_oneIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_compareToTwoBasisMatricesWithSameAxisButFirstHasLowerMatrixCompareToValue_negativeOneIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_compareToTwoBasisMatricesWithSameMatrixCompareToValueButFirstIsUAxisAndSecondIsVAxis_oneIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_compareToTwoBasisMatricesWithSameMatrixCompareToValueButFirstIsVAxisAndSecondisUAxis_negativeOneIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void BasisMatrix_copyMaliciousMutableBasisMatrix_validImmutableBasisMatrixIsCreated() {
    fail("Not yet implemented");
  }
}
