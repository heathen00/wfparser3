package com.nickmlanglois.wrap.acceptance;

import static org.junit.Assert.assertNotNull;
import com.nickmlanglois.wrap.Assert;
import com.nickmlanglois.wrap.ResourceBundleWrapper;
import com.nickmlanglois.wrap.WrapperFactory;
import java.util.Locale;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class WrapperFactoryAcceptanceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

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

  @Test
  public void WrapperFactory_createResourceBundleWrapperForLocaleWithNullResourceName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("baseBundleName cannot be null");

    wrapperFactory.createResourceBundleWrapperForLocale((String) null, Locale.CANADA_FRENCH);
  }

  @Test
  public void WrapperFactory_createResourceBundleWraperForLocaleWithEmptyResourceName_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("baseBundleName cannot be empty");

    wrapperFactory.createResourceBundleWrapperForLocale("   ", Locale.CANADA_FRENCH);
  }

  @Test
  public void WrapperFactory_createResourceBundleWrapperForLocaleWithNullLocale_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("locale cannot be null");

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

  @Test
  public void WrapperFactory_createResourceBundleWrapperForRootLocaleWithNullResourceName_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("baseBundleName cannot be null");

    wrapperFactory.createResourceBundleWrapperForRootLocale((String) null);
  }

  @Test
  public void WrapperFactory_createResourceBundleWrapperForRootLocaleWithEmptyResourceName_unsupportedOperationExceptionIsThrown() {
    thrown.expect(UnsupportedOperationException.class);
    thrown.expectMessage("baseBundleName cannot be empty");

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
