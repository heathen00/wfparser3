package com.nickmlanglois.localizer;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.nickmlanglois.uid.Uid;

final class LocalizerTypeInternalImp implements LocalizerTypeInternal {
  private final LocalizerFactoryInternal localizerFactoryInternal;
  private final LocalizerInternal localizerInternal;
  private final String groupName;
  private final String typeName;
  private final String methodName;
  private final Map<Uid<LocalizerInstance>, LocalizerInstanceInternal> localizerInstanceMap;
  private final Uid<LocalizerType> localizerTypeUid;

  LocalizerTypeInternalImp(LocalizerFactoryInternal localizerFactoryInternal,
      LocalizerInternal localizerInternal, String groupName, String typeName, String methodName) {
    this.localizerFactoryInternal = localizerFactoryInternal;
    this.localizerInternal = localizerInternal;
    this.groupName = groupName;
    this.typeName = typeName;
    this.methodName = methodName;
    localizerInstanceMap = new HashMap<>();
    localizerTypeUid =
        this.localizerFactoryInternal.getUidFactory().createUid(getFullyQualifiedName(), this);
  }

  @Override
  public Localizer getLocalizer() {
    return localizerInternal;
  }

  @Override
  public String getGroupName() {
    return groupName;
  }

  @Override
  public String getTypeName() {
    return typeName;
  }

  @Override
  public String getMethodName() {
    return methodName;
  }

  @Override
  public LocalizerInstanceInternal getLocalizerInstanceInternal(
      Uid<LocalizerInstance> instanceUid) {
    if (null == instanceUid) {
      throw new NullPointerException("instanceUid cannot be null");
    }
    LocalizerInstanceInternal localizerInstance = localizerInstanceMap.get(instanceUid);
    if (null == localizerInstance) {
      localizerInstance = localizerFactoryInternal.createUndefinedLocalizer()
          .getLocalizerTypeInternal(null).getLocalizerInstanceInternal(null);
    }
    return localizerInstance;
  }

  @Override
  public LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid) {
    return getLocalizerInstanceInternal(instanceUid);
  }

  @Override
  public Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet() {
    return Collections.unmodifiableSet(localizerInstanceMap.keySet());
  }

  @Override
  public Uid<LocalizerType> getUid() {
    return localizerTypeUid;
  }

  @Override
  public LocalizerInstanceInternal addLocalizerInstanceInternal(
      LocalizerInstanceInternal localizerInstanceInternal) {
    LocalizerInstanceInternal newLocalizerInstanceInternal = localizerInstanceInternal;
    LocalizerInstanceInternal existingLocalizerInstanceInternal =
        localizerInstanceMap.get(localizerInstanceInternal.getUid());
    if (null == existingLocalizerInstanceInternal) {
      localizerInstanceMap.put(newLocalizerInstanceInternal.getUid(), newLocalizerInstanceInternal);
    } else {
      newLocalizerInstanceInternal = existingLocalizerInstanceInternal;
    }
    return newLocalizerInstanceInternal;
  }

  @Override
  public boolean isDefined() {
    return true;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", groupName, typeName, methodName);
  }
}
