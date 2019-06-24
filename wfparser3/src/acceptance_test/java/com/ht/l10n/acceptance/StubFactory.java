package com.ht.l10n.acceptance;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import com.ht.common.UID;
import com.ht.l10n.Localizer;
import com.ht.l10n.LocalizerBundle;
import com.ht.l10n.LocalizerException;
import com.ht.l10n.LocalizerField;
import com.ht.l10n.LocalizerType;

public final class StubFactory {
  private static final StubFactory STUB_FACTORY_SINGLETON = new StubFactory();

  public static StubFactory createStubFactory() {
    return STUB_FACTORY_SINGLETON;
  }

  private StubFactory() {}

  public LocalizerType createStubLocalizerType(String groupName, String typeName,
      String instanceName) {
    return new LocalizerType() {
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
        throw new UnsupportedOperationException("this operation not supported by stub");
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
    };
  }

  public Localizer createDefaultStubLocalizer() {
    return new Localizer() {
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
    };
  }

  public LocalizerBundle createDefaultStubLocalizerBundle() {
    return new LocalizerBundle() {

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
    };
  }

  public LocalizerField createStubLocalizerField(String fieldName, String instanceName) {
    return new LocalizerField() {
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
    };
  }
}
