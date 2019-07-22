package com.ht.l10n.acceptance;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SystemAcceptanceTest {

  @Before
  public void setUp() throws Exception {}

  @Test
  public void System_getSystem_systemIsReturned() {

    // TODO remember to unpublish the existing Factory.createFactory() method.
    // TODO remember to ensure that system is a singleton and contains the factory method.
    fail("Not yet implemented");
  }

  @Test
  public void System_getFactoryFromSystem_factoryIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void System_getLocalizerSetWhenNoLocalizersCreated_emptyLocalizerSetReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void System_getLocalizerWithNullLocalizerUID_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  public void System_getLocalizerWithLocalizerUIDWhenNoLocalizersDefined_undefinedLocalizerIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void System_getLocalizerSetWhenOneLocalizersCreated_localizerSetContainingExpectedLocalizerIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void System_getLocalizerUsingLocalizerUIDForExistingLocalizerWhenLocalizersCreated_expectedLocalizerIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  public void System_getLocalizerUsingLocalizerUIDForNonExistingLocalizerWhenLocalizersCreated_undefinedLocalizerIsReturned() {
    fail("Not yet implemented");
  }
}

