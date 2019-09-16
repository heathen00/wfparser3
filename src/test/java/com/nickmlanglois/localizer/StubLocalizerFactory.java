package com.nickmlanglois.localizer;

import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import com.nickmlanglois.localizer.Localizer;
import com.nickmlanglois.localizer.LocalizerBundle;
import com.nickmlanglois.localizer.LocalizerBundleInternal;
import com.nickmlanglois.localizer.LocalizerException;
import com.nickmlanglois.localizer.LocalizerFactory;
import com.nickmlanglois.localizer.LocalizerInstance;
import com.nickmlanglois.localizer.LocalizerInstanceInternal;
import com.nickmlanglois.localizer.LocalizerInternal;
import com.nickmlanglois.localizer.LocalizerType;
import com.nickmlanglois.localizer.LocalizerTypeInternal;
import com.nickmlanglois.uid.Uid;

public final class StubLocalizerFactory implements LocalizerFactory {
  private void operationNotSupported() {
    throw new UnsupportedOperationException("operation not supported by stub");
  }

  @Override
  public Localizer createLocalizer(String name, Locale locale) throws LocalizerException {
    return new LocalizerInternal() {
      private final Locale myLocale = locale;

      @Override
      public boolean isDefined() {
        return false;
      }

      @Override
      public Uid<Localizer> getUid() {
        operationNotSupported();
        return null;
      }

      @Override
      public void setLocale(Locale locale) throws LocalizerException {
        operationNotSupported();
      }

      @Override
      public String getName() {
        operationNotSupported();
        return null;
      }

      @Override
      public Set<Uid<LocalizerType>> getLocalizerTypeUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerType getLocalizerType(Uid<LocalizerType> typeUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet() {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid) {
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
        return myLocale;
      }

      @Override
      public LocalizerTypeInternal getLocalizerTypeInternal(Uid<LocalizerType> typeUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerTypeInternal addLocalizerTypeInternal(
          LocalizerTypeInternal localizerTypeInternal) {
        return localizerTypeInternal;
      }

      @Override
      public LocalizerBundleInternal addLocalizerBundleInternal(
          LocalizerBundleInternal localizerBundleInternal) {
        operationNotSupported();
        return null;
      }
    };
  }

  @Override
  public LocalizerBundle createLocalizerBundle(Localizer localizer, String resourceBundleName)
      throws LocalizerException {
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

  @Override
  public LocalizerType createLocalizerType(Localizer localizer, String groupName, String typeName,
      String methodName) throws LocalizerException {
    return new LocalizerTypeInternal() {
      final String myGroupName = groupName;
      final String myTypeName = typeName;
      final String myMethodName = methodName;
      final Localizer myLocalizer = createLocalizer(null, null);

      @Override
      public String getTypeName() {
        return myTypeName;
      }

      @Override
      public Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet() {
        operationNotSupported();
        return null;
      }


      @Override
      public LocalizerInstanceInternal getLocalizerInstanceInternal(
          Uid<LocalizerInstance> instanceUid) {
        operationNotSupported();
        return null;
      }

      @Override
      public LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid) {
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
      public Uid<LocalizerType> getUid() {
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

  @Override
  public LocalizerInstance createLocalizerInstance(LocalizerType localizerType, String instanceName)
      throws LocalizerException {
    return new LocalizerInstanceInternal() {
      private final String myGroupName = "testBundle00";
      private final String myTypeName = "testType00";
      private final String myMethodName = "BROKEN";
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
      public Uid<LocalizerInstance> getUid() {
        operationNotSupported();
        return null;
      }

      @Override
      public boolean isDefined() {
        return false;
      }
    };
  }

  public static StubLocalizerFactory createStubLocalizerFactory() {
    return new StubLocalizerFactory();
  }

  @Override
  public void resetAll() {}
}
