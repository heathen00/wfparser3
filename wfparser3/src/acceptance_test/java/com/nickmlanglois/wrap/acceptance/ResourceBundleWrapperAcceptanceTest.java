package com.nickmlanglois.wrap.acceptance;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import com.nickmlanglois.wrap.Assert;
import com.nickmlanglois.wrap.ResourceBundleWrapper;
import com.nickmlanglois.wrap.WrapperFactory;
import java.util.IllegalFormatConversionException;
import java.util.Locale;
import java.util.MissingFormatArgumentException;
import java.util.MissingResourceException;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

public class ResourceBundleWrapperAcceptanceTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private WrapperFactory wrapperFactory;
  private Assert wrapperAssert;

  private Locale defaultExpectedLocale;
  private String defaultExistingUnformattedKey;
  private String defaultExistingFormattedKey;
  private Object[] defaultExpectedFormatObjectsArray;
  private String defaultNonexistentUnformattedKey;
  private String defaultNonexistentFormattedKey;


  private String generateResourceBundleStringToCheck(String rootString,
      String resourceBundleBaseName, Locale locale) {
    return rootString + ": " + resourceBundleBaseName + ": " + locale;
  }

  private boolean isLocaleEnglishCanada(Locale locale) {
    return Locale.CANADA.equals(locale);
  }

  @Before
  public void setUp() {
    wrapperFactory = WrapperFactory.createWrapperFactory();
    wrapperAssert = Assert.createWrapperAssert();

    defaultExpectedLocale = Locale.CANADA_FRENCH;
    defaultExistingUnformattedKey = "test.unformatted.key";
    defaultExistingFormattedKey = "test.formatted.key";
    defaultExpectedFormatObjectsArray = new Object[] { "some string", Integer.valueOf(33) };
    defaultNonexistentUnformattedKey = "non.existent.unformatted.key";
    defaultNonexistentFormattedKey = "non.existent.formatted.key";
  }

  @Test
  public void ResourceBundleWrapper_createTestingAssets_testingAssetsAreCreated() {
    assertNotNull(wrapperFactory);
    assertNotNull(wrapperAssert);
    assertNotNull(defaultExpectedLocale);
    assertNotNull(defaultExistingUnformattedKey);
    assertNotNull(defaultExistingFormattedKey);
    assertNotNull(defaultExpectedFormatObjectsArray);
    assertNotNull(defaultNonexistentUnformattedKey);
    assertNotNull(defaultNonexistentFormattedKey);
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForSpecificLocale_MissingResourceExceptionIsThrown() {
    thrown.expect(MissingResourceException.class);
    thrown.expectMessage("Can't find bundle for base name");

    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale("resource.bundle.does.not.Exist",
            Locale.CANADA_FRENCH);

    resourceBundleWrapper.loadResourceBundle();
  }

  @Test
  public void ResourceBundleWraper_loadResourceBundleThatDoesNotExistForDefaultLocale_missingResourceExceptionIsThrown() {
    thrown.expect(MissingResourceException.class);
    thrown.expectMessage("Can't find bundle for base name");

    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale("resource.bundle.does.not.Exist",
            Locale.getDefault());

    resourceBundleWrapper.loadResourceBundle();
  }

  @Test
  public void ResourceBundleWrapper_loadResourceBundleThatDoesNotExistForRootLocale_missingResourceExceptionIsThrown() {
    thrown.expect(MissingResourceException.class);
    thrown.expectMessage("Can't find bundle for base name");

    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale("resource.bundle.does.not.Exist", Locale.ROOT);

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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, initialLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, initialLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, initialLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
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
    ResourceBundleWrapper resourceBundleWrapper = wrapperFactory
        .createResourceBundleWrapperForLocale(expectedResourceBundleBaseName, expectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedResourceBundleWrapper(expectedResourceBundleBaseName,
        expectedLocale, resourceBundleWrapper);
    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_getUnformattedStringThatDoesNotExistInResourceBundle_missingResourceExceptionIsThrown() {
    thrown.expect(MissingResourceException.class);
    thrown.expectMessage("Can't find resource for bundle ");

    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsNonExistentStringDefinitions";
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName,
            defaultExpectedLocale);
    try {
      resourceBundleWrapper.loadResourceBundle();
    } catch (MissingResourceException mre) {
      fail("exception happend at unexpected location");
    }

    resourceBundleWrapper.getUnformattedString(defaultNonexistentUnformattedKey);
  }

  @Test
  public void ResourceBundleWrapper_getFormattedStringThatDoesNotExistInResourceBundle_missingResourceExceptionIsThrown() {
    thrown.expect(MissingResourceException.class);
    thrown.expectMessage("Can't find resource for bundle");

    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsNonExistentStringDefinitions";
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName,
            defaultExpectedLocale);
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
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName,
            defaultExpectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    wrapperAssert.assertExpectedLocalizedAndFormattedStrings(expectedUnformattedString,
        expectedFormattedStringNoFormat, defaultExpectedFormatObjectsArray,
        defaultExistingUnformattedKey, defaultExistingFormattedKey, resourceBundleWrapper);
  }

  @Test
  public void ResourceBundleWrapper_getFormattedStringButNoFormatObjectsSpecified_missingFormatArgumentExceptionIsThrown() {
    thrown.expect(MissingFormatArgumentException.class);
    thrown.expectMessage("Format specifier ");

    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsStringDefinitionsExist";
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName,
            defaultExpectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    resourceBundleWrapper.getFormattedString(defaultExistingFormattedKey);
  }

  @Test
  public void ResourceBundleWrapper_getFormattedStringButWrongObjectTypesSpecifiedForFormatObjects_IllegalFormatConversionExceptionIsThrown() {
    thrown.expect(IllegalFormatConversionException.class);

    final String expectedResourceBundleBaseName =
        "com.ht.wrap.test.resource.ResourceBundleWrapperExistsStringDefinitionsExist";
    ResourceBundleWrapper resourceBundleWrapper =
        wrapperFactory.createResourceBundleWrapperForLocale(expectedResourceBundleBaseName,
            defaultExpectedLocale);
    resourceBundleWrapper.loadResourceBundle();

    resourceBundleWrapper.getFormattedString(defaultExistingFormattedKey, "some string",
        new Object());
  }
}
