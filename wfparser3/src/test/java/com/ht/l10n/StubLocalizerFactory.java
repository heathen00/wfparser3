package com.ht.l10n;

import com.ht.uid.UID;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

public final class StubLocalizerFactory {
  private static final StubLocalizerFactory STUB_FACTORY_SINGLETON = new StubLocalizerFactory();

  public static StubLocalizerFactory createStubLocalizerFactory() {
    return STUB_FACTORY_SINGLETON;
  }

  private StubLocalizerFactory() {}

  public LocalizerType createStubLocalizerType(String groupName, String typeName,
      String methodName) {
    return new LocalizerTypeInternal() {
      final String myGroupName = groupName;
      final String myTypeName = typeName;
      final String myInstanceName = methodName;
      final Localizer myLocalizer = createDefaultStubLocalizer();

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }


      @Override
      public LocalizerInstanceInternal getLocalizerInstanceInternal(
          UID<LocalizerInstance> instanceUid) {
        return SystemInternal.getSystemInternal().getFactoryInternal().createUndefinedLocalizer()
            .getLocalizerTypeInternal(null).getLocalizerInstanceInternal(null);
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
        return getLocalizerInstanceInternal(instanceUid);
      }

      @Override
      public Localizer getLocalizer() {
        return myLocalizer;
      }

      @Override
      public String getMethodName() {
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
      public LocalizerInstanceInternal addLocalizerInstanceInternal(
          LocalizerInstanceInternal localizerFieldInternal) {
        return localizerFieldInternal;
      }

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


  public LocalizerType createExternalStubLocalizerType(String groupName, String typeName,
      String methodName) {
    return new LocalizerType() {
      private String myGroupName = groupName;
      private String myTypeName = typeName;
      private String myInstanceName = methodName;

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public UID<LocalizerType> getUid() {
        return UID.createUid(getFullyQualifiedName(), this);
      }

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        throw new UnsupportedOperationException("operation not supported by stub");
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> fieldUid) {
        throw new UnsupportedOperationException("operation not supported by stub");
      }

      @Override
      public Localizer getLocalizer() {
        throw new UnsupportedOperationException("operation not supported by stub");
      }

      @Override
      public String getMethodName() {
        return myInstanceName;
      }

      @Override
      public String getGroupName() {
        return myGroupName;
      }

      @Override
      public String getFullyQualifiedName() {
        return String.join(".", myGroupName, myTypeName, myInstanceName);
      }
    };
  }

  public Localizer createDefaultStubLocalizer() {
    return new LocalizerInternal() {
      final SortedSet<LocalizerBundle> myLocalizerBundleSet = initLocalizerBundleSet();

      private SortedSet<LocalizerBundle> initLocalizerBundleSet() {
        SortedSet<LocalizerBundle> localizerBundleSet = new TreeSet<>();
        localizerBundleSet.add(createDefaultStubLocalizerBundle());
        return localizerBundleSet;
      }

      @Override
      public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerType>> getLocalizerTypeUidSet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerTypeInternal getLocalizerTypeInternal(UID<LocalizerType> typeUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
        return getLocalizerTypeInternal(typeUid);
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> fieldUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
        return Collections.unmodifiableSortedSet(myLocalizerBundleSet);
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

      @Override
      public String getName() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public UID<Localizer> getUid() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }
    };
  }

  public LocalizerBundle createDefaultStubLocalizerBundle() {
    return new LocalizerBundleInternal() {

      @Override
      public String getUnformattedString(LocalizerInstance localizerField)
          throws LocalizerException {
        return "test unformatted string for localizerField with fieldName "
            + localizerField.getInstanceName();
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
      public String getFormattedString(LocalizerInstance localizerField, Object... parameters)
          throws LocalizerException {
        return "test formatted string for localizerField with fieldName "
            + localizerField.getInstanceName();
      }

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public void loadL10nResource(Locale locale) {}

      @Override
      public int compareTo(LocalizerBundle o) {
        return 0;
      }
    };
  }

  public LocalizerInstance createStubLocalizerField(String fieldName, String instanceName) {
    return new LocalizerInstanceInternal() {
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
      public String getInstanceName() {
        return myInstanceName;
      }

      @Override
      public LocalizerType getLocalizerType() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public UID<LocalizerInstance> getUid() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public boolean isDefined() {
        return false;
      }
    };
  }

  public Localizer createExternalStubLocalizer() {
    return new Localizer() {

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public void setLocale(Locale locale) throws LocalizerException {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerType>> getLocalizerTypeUidSet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> fieldUid) {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public Locale getLocale() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public String getName() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }

      @Override
      public UID<Localizer> getUid() {
        throw new UnsupportedOperationException("this operation not supported by stub");
      }
    };
  }

  public Localizer createUndefinedLocalizer() {
    return SystemInternal.getSystemInternal().getFactoryInternal().createUndefinedLocalizer();
  }
}
