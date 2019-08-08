package com.ht.localizer;

import java.util.Collections;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.ht.uid.UID;

public final class StubLocalizerFactory {
  private final LocalizerSystemInternal localizerSystemInternal;

  public static StubLocalizerFactory createStubLocalizerFactory(
      final LocalizerSystem localizerSystem) {
    return new StubLocalizerFactory((LocalizerSystemInternal) localizerSystem);
  }

  private StubLocalizerFactory(final LocalizerSystemInternal localizerSystemInternal) {
    this.localizerSystemInternal = localizerSystemInternal;
  }

  private void operationNotSupported() {
    throw new UnsupportedOperationException("operation not supported by stub");
  }

  public LocalizerType createStubLocalizerType(String groupName, String typeName,
      String methodName) {
    return new LocalizerTypeInternal() {
      final String myGroupName = groupName;
      final String myTypeName = typeName;
      final String myMethodName = methodName;
      final Localizer myLocalizer = createDefaultStubLocalizer();

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        operationNotSupported();
        return null;
      }


      @Override
      public LocalizerInstanceInternal getLocalizerInstanceInternal(
          UID<LocalizerInstance> instanceUid) {
        return localizerSystemInternal.getLocalizerFactoryInternal().createUndefinedLocalizer()
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
        return myMethodName;
      }

      @Override
      public String getGroupName() {
        return myGroupName;
      }

      @Override
      public UID<LocalizerType> getUid() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerInstanceInternal addLocalizerInstanceInternal(
          LocalizerInstanceInternal localizerInstanceInternal) {
        return localizerInstanceInternal;
      }

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public String getFullyQualifiedName() {
        return String.join(".", myGroupName, myTypeName, myMethodName);
      }
    };
  }


  public LocalizerType createExternalStubLocalizerType(String groupName, String typeName,
      String methodName) {
    return new LocalizerType() {
      private String myGroupName = groupName;
      private String myTypeName = typeName;
      private String myMethodName = methodName;

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public UID<LocalizerType> getUid() {
        return localizerSystemInternal.getLocalizerFactoryInternal().getUidFactory()
            .createUid(getFullyQualifiedName(), this);
      }

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public Localizer getLocalizer() {
        operationNotSupported();
        return null;
      }

      @Override
      public String getMethodName() {
        return myMethodName;
      }

      @Override
      public String getGroupName() {
        return myGroupName;
      }

      @Override
      public String getFullyQualifiedName() {
        return String.join(".", myGroupName, myTypeName, myMethodName);
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
        operationNotSupported();
      }

      @Override
      public Set<UID<LocalizerType>> getLocalizerTypeUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerTypeInternal getLocalizerTypeInternal(UID<LocalizerType> typeUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
        return getLocalizerTypeInternal(typeUid);
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
        return Collections.unmodifiableSortedSet(myLocalizerBundleSet);
      }

      @Override
      public Locale getLocale() {
        operationNotSupported();
        return null;
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
        operationNotSupported();
        return null;
      }

      @Override
      public UID<Localizer> getUid() {
        operationNotSupported();
        return null;
      }
    };
  }

  public LocalizerBundle createDefaultStubLocalizerBundle() {
    return new LocalizerBundleInternal() {

      @Override
      public String getUnformattedString(LocalizerInstance localizerInstance)
          throws LocalizerException {
        return "test unformatted string for localizerInstance with instanceName "
            + localizerInstance.getInstanceName();
      }

      @Override
      public Locale getTargetLocale() {
        operationNotSupported();
        return null;
      }

      @Override
      public String getResourceBundleName() {
        operationNotSupported();
        return null;
      }

      @Override
      public Locale getResolvedLocale() {
        operationNotSupported();
        return null;
      }

      @Override
      public String getFormattedString(LocalizerInstance localizerInstance, Object... parameters)
          throws LocalizerException {
        return "test formatted string for localizerInstance with instanceName "
            + localizerInstance.getInstanceName();
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

  public LocalizerInstance createStubLocalizerInstance(String methodName, String instanceName) {
    return new LocalizerInstanceInternal() {
      private final String myGroupName = "testBundle00";
      private final String myTypeName = "testType00";
      private final String myMethodName = methodName;
      private final String myInstanceName = instanceName;

      @Override
      public String getUnformattedString() {
        operationNotSupported();
        return null;
      }

      @Override
      public String getFullyQualifiedName() {
        return String.join(".", myGroupName, myTypeName, myMethodName, myInstanceName);
      }

      @Override
      public String getFormattedString(Object... parameters) {
        operationNotSupported();
        return null;
      }

      @Override
      public String getInstanceName() {
        return myInstanceName;
      }

      @Override
      public LocalizerType getLocalizerType() {
        operationNotSupported();
        return null;
      }

      @Override
      public UID<LocalizerInstance> getUid() {
        operationNotSupported();
        return null;
      }

      @Override
      public boolean isDefined() {
        return false;
      }
    };
  }

  public Localizer createPublishedStubLocalizer() {
    return new Localizer() {

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public void setLocale(Locale locale) throws LocalizerException {
        operationNotSupported();
      }

      @Override
      public Set<UID<LocalizerType>> getLocalizerTypeUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public Locale getLocale() {
        operationNotSupported();
        return null;
      }

      @Override
      public String getName() {
        operationNotSupported();
        return null;
      }

      @Override
      public UID<Localizer> getUid() {
        operationNotSupported();
        return null;
      }
    };
  }

  public Localizer createUndefinedLocalizer() {
    return localizerSystemInternal.getLocalizerFactoryInternal().createUndefinedLocalizer();
  }
}
