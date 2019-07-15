package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public final class StubFactory {
  private static final StubFactory STUB_FACTORY_SINGLETON = new StubFactory();

  public static StubFactory createStubFactory() {
    return STUB_FACTORY_SINGLETON;
  }

  private StubFactory() {}

  public LocalizerType createStubLocalizerType(String groupName, String typeName,
      String instanceName) {
    return new LocalizerTypeInternal() {
      final String myGroupName = groupName;
      final String myTypeName = typeName;
      final String myInstanceName = instanceName;
      final Localizer myLocalizer = createDefaultStubLocalizer();

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
        return FactoryInternal.createFactoryInternal().createUndefinedLocalizer()
            .getLocalizerField(fieldUid);
      }

      @Override
      public Localizer getLocalizer() {
        return myLocalizer;
      }

      @Override
      public String getInstanceName() {
        return myInstanceName;
      }

      @Override
      public String getGroupName() {
        return myGroupName;
      }

      @Override
      public UID<LocalizerType> getUid() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public void addLocalizerField(LocalizerField localizerField) {}

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public String getFullyQualifiedName() {
        return String.join(".", myGroupName, myTypeName, myInstanceName);
      }
    };
  }

  public Localizer createDefaultStubLocalizer() {
    return new LocalizerInternal() {
      final Set<LocalizerBundle> myLocalizerBundleSet = initLocalizerBundleSet();

      private Set<LocalizerBundle> initLocalizerBundleSet() {
        Set<LocalizerBundle> localizerBundleSet = new HashSet<>();
        localizerBundleSet.add(createDefaultStubLocalizerBundle());
        return localizerBundleSet;
      }

      @Override
      public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<LocalizerBundle> getLocalizerBundleSet() {
        return Collections.unmodifiableSet(myLocalizerBundleSet);
      }

      @Override
      public Locale getLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public LocalizerBundleInternal addLocalizerBundleInternal(
          LocalizerBundleInternal localizerBundleInternal) {
        return localizerBundleInternal;
      }

      @Override
      public LocalizerTypeInternal addLocalizerTypeInternal(
          LocalizerTypeInternal localizerTypeInternal) {
        return localizerTypeInternal;
      }
    };
  }

  public LocalizerBundle createDefaultStubLocalizerBundle() {
    return new LocalizerBundleInternal() {

      @Override
      public String getUnformattedString(LocalizerField localizerField) throws LocalizerException {
        return "test unformatted string for localizerField with fieldName "
            + localizerField.getFieldName();
      }

      @Override
      public Locale getTargetLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getResourceBundleName() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Locale getResolvedLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getFormattedString(LocalizerField localizerField, Object... parameters)
          throws LocalizerException {
        return "test formatted string for localizerField with fieldName "
            + localizerField.getFieldName();
      }

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public void loadL10nResource(Locale locale) {}
    };
  }

  public LocalizerField createStubLocalizerField(String fieldName, String instanceName) {
    return new LocalizerFieldInternal() {
      private final String myGroupName = "testBundle00";
      private final String myTypeName = "testType00";
      private final String myFieldName = fieldName;
      private final String myInstanceName = instanceName;

      @Override
      public String getUnformattedString() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getFullyQualifiedName() {
        return String.join(".", myGroupName, myTypeName, myFieldName, myInstanceName);
      }

      @Override
      public String getFormattedString(Object... parameters) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getFieldName() {
        return myFieldName;
      }

      @Override
      public LocalizerType getLocalizerType() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public UID<LocalizerField> getUid() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public boolean isDefined() {
        return false;
      }
    };
  }
}
