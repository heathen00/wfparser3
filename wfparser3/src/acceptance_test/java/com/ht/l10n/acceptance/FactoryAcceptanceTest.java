package com.ht.l10n.acceptance;

import static org.junit.Assert.assertNotNull;

import com.ht.common.UID;
import com.ht.l10n.Assert;
import com.ht.l10n.Factory;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;
import com.ht.l10n.StubFactory;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class FactoryAcceptanceTest {

  private Factory localizerFactory;
  private StubFactory stubFactory;
  private Assert localizerAssert;

  @Before
  public void setup() {
    localizerFactory = Factory.createFactory();
    stubFactory = StubFactory.createStubFactory();
    localizerAssert = Assert.createAssert();
  }

  @Test
  public void Factory_createFactories_factoriesCreated() {
    assertNotNull(localizerFactory);
    assertNotNull(stubFactory);
    assertNotNull(localizerAssert);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerWithNullLocale_nullPointerExceptionIsThrown() {
    localizerFactory.createLocalizer(null);
  }

  @Test
  public void Factory_createLocalizerWithLocale_localizeIsCreated() {
    Locale expectedLocale = Locale.CANADA_FRENCH;
    boolean expectedIsDefined = true;

    Localizer localizer = localizerFactory.createLocalizer(expectedLocale);

    localizerAssert.assertExpectedLocalizer(expectedLocale, expectedIsDefined, localizer);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createCompositeLocalizerBundleWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerBundle(null, "does.not.matter");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createCompositeLocalizerBundleWithNullResourceBundleName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerBundle(localizerFactory.createLocalizer(Locale.CANADA_FRENCH),
        null);
  }

  @Test
  public void Factory_createCompositeLocalizerBundleWithValidParameters_localizerBundleCreatedAsSpecified()
      throws Exception {
    final Locale expectedTargetLocale = Locale.CANADA_FRENCH;
    final Locale expectedResolvedLocale = Locale.CANADA_FRENCH;
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForCompositeResourceBundleWithRootLocaleAndNoExceptions";
    final boolean expectedIsDefined = true;
    Localizer localizer = localizerFactory.createLocalizer(expectedTargetLocale);

    LocalizerBundle localizerBundle =
        localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);

    localizerAssert.assertExpectedLocalizerBundle(expectedTargetLocale, expectedResolvedLocale,
        expectedResourceBundleName, expectedIsDefined, localizer, localizerBundle);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createCompositeLocalizerButRootLocaleResourceBundleDoesNotExist_localizerExceptionIsThrown()
      throws Exception {
    final String expectedResourceBundleName =
        "com.ht.l10n.test.resource.TestL10ResourceBundleForLocaleExistsButRootLocaleDoesNot";
    Localizer localizer = localizerFactory.createLocalizer(Locale.CANADA_FRENCH);

    localizerFactory.createLocalizerBundle(localizer, expectedResourceBundleName);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerFieldWithNullLocalizerType_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(null, "test.field");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerFieldWithNullFieldName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"), null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithEmptyFieldName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"), "");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithUnsupportedCharactersInFieldName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"),
        "SOME UNSUPPORTED\tCHARACTERS\n");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithFieldNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"),
        ".invalid.period.at.begging");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerFieldWithFieldNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerField(
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance"),
        "invalid.period.at.end.");
  }

  @Test
  public void Factory_createLocalizerFieldWithSupportedFieldName_localizerFieldIsCreated()
      throws Exception {
    final String expectedGroupName = "test.group";
    final String expectedTypeName = "test.type";
    final String expectedInstanceName = "test.instance";
    final String expectedFieldName = "valid.field.name.00";
    final String expectedFullyQualifiedName = String.join(".", expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedFieldName);
    final LocalizerType expectedLocalizerType =
        stubFactory.createStubLocalizerType("test.group", "test.type", "test.instance");
    final String expectedUnformattedString =
        "test unformatted string for localizerField with fieldName " + expectedFieldName;
    final String expectedFormattedString =
        "test formatted string for localizerField with fieldName " + expectedFieldName;
    final boolean expectedIsDefined = true;

    LocalizerField localizerField =
        localizerFactory.createLocalizerField(expectedLocalizerType, expectedFieldName);

    localizerAssert.assertExpectedLocalizerField(expectedFieldName, expectedFullyQualifiedName,
        expectedLocalizerType, expectedUnformattedString, expectedFormattedString,
        expectedIsDefined, localizerField);
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullLocalizer_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(null, "test.group", "test.type", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullGroupName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), null,
        "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithEmptyGroupName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "", "test.type",
        "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithUnsupportedCharactersInGroupName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "unsupported\ncharacters", "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithGroupNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        ".invalid.starts.with.period", "test.type", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithGroupNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(),
        "invalid.ends.with.period.", "test.type", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullTypeName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        null, "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithEmptyTypeName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group", "",
        "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithUnsupportedCharactersInTypeName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "unsupported characters", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithTypeNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        ".invalid.starts.with.period", "test.instance");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithTypeNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "invalid.ends.with.period.", "test.instance");
  }

  @Test(expected = NullPointerException.class)
  public void Factory_createLocalizerTypeWithNullInstanceName_nullPointerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "test.type", null);
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithEmptyInstanceName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "test.type", "");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithUnsupportedCharactersInInstanceName_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "test.type", "unsupported!characters");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithInstanceNameBeginningWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "test.type", ".invalid.starts.with.period");
  }

  @Test(expected = LocalizerException.class)
  public void Factory_createLocalizerTypeWithInstanceNameEndingWithAPeriod_localizerExceptionIsThrown()
      throws Exception {
    localizerFactory.createLocalizerType(stubFactory.createDefaultStubLocalizer(), "test.group",
        "test.type", "invalid.ends.with.period.");
  }

  @Test
  public void Factory_createLocalizerTypeWithValidParameters_localizerTypeIsCreated()
      throws Exception {
    final String expectedGroupName = "expected.group.name";
    final String expectedTypeName = "expected.type.name";
    final String expectedInstanceName = "expected.instance.name";
    final Set<UID<LocalizerField>> expectedLocalizerFieldKeySet = Collections.emptySet();
    final Localizer expectedLocalizer = stubFactory.createDefaultStubLocalizer();
    final UID<LocalizerType> expectedLocalizerTypeUid = UID.createUid(
        String.join(".", expectedGroupName, expectedTypeName, expectedInstanceName), stubFactory
            .createStubLocalizerType(expectedGroupName, expectedTypeName, expectedInstanceName));
    final boolean expectedIsDefined = true;

    LocalizerType localizerType = localizerFactory.createLocalizerType(expectedLocalizer,
        expectedGroupName, expectedTypeName, expectedInstanceName);

    localizerAssert.assertExpectedLocalizerType(expectedGroupName, expectedTypeName,
        expectedInstanceName, expectedLocalizerFieldKeySet, expectedLocalizer,
        expectedLocalizerTypeUid, expectedIsDefined, localizerType);
  }
}
