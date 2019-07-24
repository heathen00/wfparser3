package com.ht.wrap.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.wrap.Assert;
import com.ht.wrap.Factory;
import com.ht.wrap.ResourceBundleWrapper;

import java.util.Locale;
import java.util.MissingResourceException;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class ResourceBundleWrapperAcceptanceTest {
  private Factory wrapperFactory;
  private Assert wrapperAssert;

  @Before
  public void setUp() throws Exception {
    wrapperFactory = Factory.createWrapperFactory();
    wrapperAssert = Assert.createWrapperAssert();
  }

  @Test
  public void ResourceBundleWrapper_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(wrapperFactory);
    assertNotNull(wrapperAssert);
  }

  @Test(expected = MissingResourceException.class)
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocale_MissingResourceExceptionIsThrown() {
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapper("com.does.not.Exist", Locale.CANADA_FRENCH);

    resourceBundleWrapper.loadResourceBundle();
  }

  @Test
  public void ResourceBundleWraper_loadResourceBundleThatDoesNotExistForDefaultLocale_missingResourceExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForRootLocale_missingResourceExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocaleButDoesExistInSimilarLocale_resourceBundleForSimilarLocaleLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocaleButDoesExistInDefaultLocale_resourceBundleForDefaultLocaleLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocaleButDoesExistInRootLocale_resourceBundleForRootLocaleLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForSpecificLocale_resourceBundleForSpecificIsLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForDefaultLocale_resourceBundleForDefaultLocaleIsLoaded() {

    // TODO you might need to add a check to ensure the "default" locale is the expected locale and
    // if not just skip. I wonder if there is functionality in JUnit to dynamically skip a test if
    // the proper preconditions do not exist?? Check!
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForRootLocale_resourceBundleForRootLocaleIsLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleFromPropertiesFileThatExistsForSpecificLocaleButIsEclipsedByResourceBundleFromJavaClassFile_resourceBundleFromPropertiesFileIsLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForRootLocaleButAlsoExistsForSpecificLocale_resourceBundleForRootLocaleIsLoaded() {
    // TODO check to ensure both the formatted and unformatted strings exist.
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getUnformattedStringThatDoesNotExistInResourceBundle_missingResourceExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getFormattedStringThatDoesNotExistInResourceBundle_missingResourceExceptionIsThrown() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getUnformattedStringThatExistsInResourceBundle_stringIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getFormattedStringThatExistsInResourceBundle_stringIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getFormattedStringButNoFormatObjectsSpecified_stringWithNoFormattingIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getFormattedStringButLessThanRequiredNumberOfFormatObjectsSpecified_partiallyFormattedStringIsReturned() {
    fail("not implemented yet");
  }

  @Test
  @Ignore("not worked on yet")
  public void ResourceBundleWrapper_getFormattedStringButWrongObjectTypesSpecifiedForFormatObjects_FIX__iHaveNoIdeaWhatWillHappen() {
    fail("not implemented yet");
  }
}
