package com.ht.wrap.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import com.ht.wrap.Assert;
import com.ht.wrap.WrapperFactory;
import com.ht.wrap.ResourceBundleWrapper;

import java.util.IllegalFormatConversionException;
import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

import org.junit.Before;
import org.junit.Test;

public class ResourceBundleWrapperAcceptanceTest {
  private WrapperFactory wrapperFactory;
  private Assert wrapperAssert;

  private final Locale defaultExpectedLocale = Locale.CANADA_FRENCH;
  private final String defaultExistingUnformattedKey = "test.unformatted.key";
  private final String defaultExistingFormattedKey = "test.formatted.key";
  private final Object[] defaultExpectedFormatObjectsArray = { "some string", Integer.valueOf(33) };
  private final String defaultNonexistentUnformattedKey = "non.existent.unformatted.key";
  private final String defaultNonexistentFormattedKey = "non.existent.formatted.key";


  private String generateResourceBundleStringToCheck(String rootString,
      String resourceBundleBaseName, Locale locale) {
    return rootString + ": " + resourceBundleBaseName + ": " + locale;
  }

  private boolean isLocaleEnglishCanada(Locale locale) {
    return Locale.CANADA.equals(locale);
  }

  @Before
  public void setUp() throws Exception {
    wrapperFactory = WrapperFactory.createWrapperFactory();
    wrapperAssert = Assert.createWrapperAssert();
  }

  @Test
  public void ResourceBundleWrapper_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(wrapperFactory);
    assertNotNull(wrapperAssert);
  }

  @Test(expected = MissingResourceException.class)
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocale_MissingResourceExceptionIsThrown() {
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale("resource.bundle.does.not.Exist", Locale.CANADA_FRENCH);

    resourceBundleWrapper.loadResourceBundle();
  }

  @Test(expected = MissingResourceException.class)
  public void ResourceBundleWraper_loadResourceBundleThatDoesNotExistForDefaultLocale_missingResourceExceptionIsThrown() {
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale("resource.bundle.does.not.Exist", Locale.getDefault());

    resourceBundleWrapper.loadResourceBundle();
  }

  @Test(expected = MissingResourceException.class)
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForRootLocale_missingResourceExceptionIsThrown() {
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale("resource.bundle.does.not.Exist", Locale.ROOT);

    resourceBundleWrapper.loadResourceBundle();
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocaleButDoesExistInSimilarLocale_resourceBundleForSimilarLocaleLoaded() {
    final Locale initialLocale = Locale.CANADA_FRENCH;
    final Locale expectedLocale = Locale.FRENCH;
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperNoSpecificButSimilarExists";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, initialLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocaleButDoesExistInDefaultLocale_resourceBundleForDefaultLocaleLoaded() {
    final Locale initialLocale = Locale.CANADA_FRENCH;
    final Locale expectedLocale = Locale.getDefault();
    if (!isLocaleEnglishCanada(expectedLocale)) {
      return;
    }

    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperNoSpecificButDefaultsExists";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, initialLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocaleButDoesExistInRootLocale_resourceBundleForRootLocaleLoaded() {
    final Locale initialLocale = Locale.CANADA_FRENCH;
    final Locale expectedLocale = Locale.ROOT;
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperNoSpecificButRootExists";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, initialLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForSpecificLocale_resourceBundleForSpecificIsLoaded() {
    final Locale expectedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsForSpecificLocale";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForDefaultLocale_resourceBundleForDefaultLocaleIsLoaded() {
    final Locale expectedLocale = Locale.getDefault();
    if (!isLocaleEnglishCanada(expectedLocale)) {
      return;
    }
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsForDefaultLocale";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForRootLocale_resourceBundleForRootLocaleIsLoaded() {
    final Locale expectedLocale = Locale.ROOT;
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsForRootLocale";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleFromPropertiesFileThatExistsForSpecificLocaleButIsEclipsedByResourceBundleFromJavaClassFile_resourceBundleFromPropertiesFileIsLoaded() {
    final Locale expectedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsForSpecificLocaleButEclipsedByClass";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatExistsForRootLocaleButAlsoExistsForSpecificLocale_resourceBundleForRootLocaleIsLoaded() {
    final Locale expectedLocale = Locale.ROOT;
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsLoadForRootLocaleWhenSpecificExists";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, expectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, expectedLocale);
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test(expected = MissingResourceException.class)
  public void ResourceBundleWrapper_getUnformattedStringThatDoesNotExistInResourceBundle_missingResourceExceptionIsThrown() {
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsNonExistentStringDefinitions";
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, defaultExpectedLocale);
    try {
      resourceBundleWrapper.loadResourceBundle();
    } catch (MissingResourceException mre) {
      fail("exception happend at unexpected location");
    }

    resourceBundleWrapper.getUnformattedString(defaultNonexistentUnformattedKey);
  }

  @Test(expected = MissingResourceException.class)
  public void ResourceBundleWrapper_getFormattedStringThatDoesNotExistInResourceBundle_missingResourceExceptionIsThrown() {
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsNonExistentStringDefinitions";
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, defaultExpectedLocale);
    try {
      resourceBundleWrapper.loadResourceBundle();
    } catch (MissingResourceException mre) {
      fail("exception happend at unexpected location");
    }

    resourceBundleWrapper.getFormattedString(defaultNonexistentFormattedKey,
        defaultExpectedFormatObjectsArray);
  }

  @Test
  public void ResourceBundleWrapper_getStringsThatExistInResourceBundle_stringsAreReturned() {
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsStringDefinitionsExist";
    final String expectedUnformattedString = generateResourceBundleStringToCheck(
        "unformatted string", expectedResourceBundleBaseName, defaultExpectedLocale);
    final String expectedFormattedStringNoFormat = generateResourceBundleStringToCheck(
        "formatted string: %s %d", expectedResourceBundleBaseName, defaultExpectedLocale);
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, defaultExpectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test(expected = MissingFormatArgumentException.class)
  public void ResourceBundleWrapper_getFormattedStringButNoFormatObjectsSpecified_missingFormatArgumentExceptionIsThrown() {
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsStringDefinitionsExist";
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, defaultExpectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    resourceBundleWrapper.getFormattedString(defaultExistingFormattedKey);
  }

  @Test(expected = IllegalFormatConversionException.class)
  public void ResourceBundleWrapper_getFormattedStringButWrongObjectTypesSpecifiedForFormatObjects_IllegalFormatConversionExceptionIsThrown() {
    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsStringDefinitionsExist";
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, defaultExpectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    resourceBundleWrapper.getFormattedString(defaultExistingFormattedKey, "some string",
        new Object());
  }
}
