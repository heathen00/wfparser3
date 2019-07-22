package com.ht.l10n.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.l10n.Factory;
import com.ht.l10n.System;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SystemAcceptanceTest {
  private System localizerSystem;
  private Factory localizerFactory;

  @Before
  public void setUp() throws Exception {
    localizerSystem = System.getSystem();
    localizerFactory = localizerSystem.getFactory();
  }

  @Test
  public void System_getSystem_systemIsReturned() {
    assertNotNull(localizerSystem);
  }

  @Test
  public void System_getFactoryFromSystem_factoryIsReturned() {
    assertNotNull(localizerFactory);
  }

  @Test
  @Ignore("Not implemented yet")
  public void System_getLocalizerSetWhenNoLocalizersCreated_emptyLocalizerSetReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void System_getLocalizerWithNullLocalizerUID_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void System_getLocalizerWithLocalizerUIDWhenNoLocalizersDefined_undefinedLocalizerIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void System_getLocalizerSetWhenOneLocalizersCreated_localizerSetContainingExpectedLocalizerIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void System_getLocalizerUsingLocalizerUIDForExistingLocalizerWhenLocalizersCreated_expectedLocalizerIsReturned() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not implemented yet")
  public void System_getLocalizerUsingLocalizerUIDForNonExistingLocalizerWhenLocalizersCreated_undefinedLocalizerIsReturned() {
    fail("Not yet implemented");
  }
}

