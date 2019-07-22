package com.ht.l10n.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import com.ht.l10n.Assert;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.StubFactory;
import com.ht.l10n.System;

import java.util.Set;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class SystemAcceptanceTest {
  private System localizerSystem;
  private Factory localizerFactory;
  private StubFactory localizerStubFactory;
  private Assert localizerAssert;

  @Before
  public void setUp() throws Exception {
    localizerSystem = System.getSystem();
    localizerFactory = localizerSystem.getFactory();
    localizerStubFactory = localizerStubFactory.createStubFactory();
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void System_createReusedInstances_reusedInstancesCreated() {
    assertNotNull(localizerSystem);
    assertNotNull(localizerFactory);
    assertNotNull(localizerStubFactory);
    assertNotNull(localizerAssert);

  }

  @Test
  public void System_getLocalizerSetWhenNoLocalizersCreated_emptyLocalizerSetReturned() {
    Set<Localizer> localizerSet = localizerSystem.getLocalizerSet();

    assertTrue(localizerSet.isEmpty());
    localizerAssert.assertSetIsUnmodifiable(localizerSet);
  }

  @Test(expected = NullPointerException.class)
  public void System_getLocalizerWithNullLocalizerUID_nullPointerExceptionIsThrown() {
    localizerSystem.getLocalizer(null);
  }

  @Test
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

