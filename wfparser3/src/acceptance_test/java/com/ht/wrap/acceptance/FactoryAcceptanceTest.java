package com.ht.wrap.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.wrap.Assert;
import com.ht.wrap.WrapperFactory;
import com.ht.wrap.ResourceBundleWrapper;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class FactoryAcceptanceTest {
  private WrapperFactory wrapperFactory;
  private Assert wrapperAssert;

  @Before
  public void setUp() throws Exception {
    wrapperFactory = WrapperFactory.createWrapperFactory();
    wrapperAssert = Assert.createWrapperAssert();
  }

  @Test
  public void Factory_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(wrapperFactory);
    assertNotNull(wrapperAssert);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createResourceBundleWithNullResourceName_nullPointerExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapper((String) null, Locale.CANADA_FRENCH);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Factory_createResourceBundleWithEmptyResourceName_invalidParameterExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapper("   ", Locale.CANADA_FRENCH);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createResourceBundleWithNullLocale_nullPointerExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapper("com.resource.name.is.NotImportant", null);
  }

  @Test
  public void Factory_createResourceBundleWithValidParameters_expectedResourceBundleIsReturned() {
    final String expectedBaseBundleName = "com.resource.bundle.TestResourceBundleName";
    final Locale expectedLocale = Locale.CANADA_FRENCH;
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapper(expectedBaseBundleName, expectedLocale);

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedBaseBundleName, expectedLocale,
        resourceBundleWrapper);
  }
}
