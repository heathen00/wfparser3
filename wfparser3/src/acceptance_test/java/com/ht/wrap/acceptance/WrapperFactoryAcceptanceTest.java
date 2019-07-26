package com.ht.wrap.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.wrap.Assert;
import com.ht.wrap.ResourceBundleWrapper;
import com.ht.wrap.WrapperFactory;

import java.util.Locale;

import org.junit.Before;
import org.junit.Test;

public class WrapperFactoryAcceptanceTest {
  private WrapperFactory wrapperFactory;
  private Assert wrapperAssert;

  @Before
  public void setUp() throws Exception {
    wrapperFactory = WrapperFactory.createWrapperFactory();
    wrapperAssert = Assert.createWrapperAssert();
  }

  @Test
  public void WrapperFactory_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(wrapperFactory);
    assertNotNull(wrapperAssert);
  }

  @Test(expected = NullPointerException.class)
  public void WrapperFactory_createResourceBundleWrapperForLocaleWithNullResourceName_nullPointerExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapperForLocale((String) null, Locale.CANADA_FRENCH);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void WrapperFactory_createResourceBundleWraperForLocaleWithEmptyResourceName_unsupportedOperationExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapperForLocale("   ", Locale.CANADA_FRENCH);
  }

  @Test(expected = NullPointerException.class)
  public void WrapperFactory_createResourceBundleWrapperForLocaleWithNullLocale_nullPointerExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapperForLocale("com.resource.name.is.NotImportant", null);
  }

  @Test
  public void WrapperFactory_createResourceBundleWrapperForLocaleWithValidParameters_expectedResourceBundleIsReturned() {
    final String expectedBaseBundleName = "com.resource.bundle.TestResourceBundleName";
    final Locale expectedLocale = Locale.CANADA_FRENCH;

    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedBaseBundleName, expectedLocale);

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedBaseBundleName, expectedLocale,
        resourceBundleWrapper);
  }

  @Test(expected = NullPointerException.class)
  public void WrapperFactory_createResourceBundleWrapperForRootLocaleWithNullResourceName_nullPointerExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapperForRootLocale((String) null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void WrapperFactory_createResourceBundleWrapperForRootLocaleWithEmptyResourceName_unsupportedOperationExceptionIsThrown() {
    wrapperFactory.createResourceBundleWrapperForRootLocale("   ");
  }

  @Test
  public void WrapperFactory_createResourceBundleWrapperForRootLocaleWithValidParameters_expectedResourceBundleIsReturned() {
    final String expectedBaseBundleName = "com.resource.bundle.TestResourceBundleName";
    final Locale expectedLocale = Locale.ROOT;

    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForRootLocale(expectedBaseBundleName);

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedBaseBundleName, expectedLocale,
        resourceBundleWrapper);
  }
}
