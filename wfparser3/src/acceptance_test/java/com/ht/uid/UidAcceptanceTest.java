package com.ht.uid;

import static org.junit.Assert.*;
import org.junit.Test;

public class UidAcceptanceTest {

  @Test
  public void UID_createUidWithNullKeyString_nullPointerExceptionIsThrown() {
    fail("test not implemented");
  }

  @Test
  public void UID_createUidWithNullComponent_nullPointerExceptionIsThrown() {
    fail("test not implemented");
  }

  @Test
  public void UID_createUidWithEmptyKeyString_unsupportedOperationExceptionIsThrown() {
    fail("test not implemented");
  }

  @Test
  public void UID_createUidWithComponentThatDoesNotImplementUniqueComponent_unsupportedOperationExceptionIsThrown() {
    fail("test not implemented");
  }

  @Test
  public void UID_createUidWithSameKeyTwiceButDifferentComponent_unsupportedOperationExceptionIsThrown() {
    fail("test not implemented");
  }

  @Test
  public void UID_createTheSameUidTwice_bothUidReferencesReferenceTheSameInstance() {
    fail("test not implemented");
  }

  @Test
  public void UID_ensureUidRespectsEqualsHashcodeAndCompareToContracts_contractsRespected() {
    fail("test not implemented");
  }
}
