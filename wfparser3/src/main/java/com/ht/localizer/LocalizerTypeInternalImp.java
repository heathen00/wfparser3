package com.ht.localizer;

import com.ht.uid.UID;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

final class LocalizerTypeInternalImp implements LocalizerTypeInternal {
  private final LocalizerFactoryInternal factoryInternal;
  private final LocalizerInternal localizerInternal;
  private final String groupName;
  private final String typeName;
  private final String methodName;
  private final Map<UID<LocalizerInstance>, LocalizerInstanceInternal> localizerInstanceMap;
  private final UID<LocalizerType> localizerTypeUid;

  LocalizerTypeInternalImp(LocalizerFactoryInternal factoryInternal,
      LocalizerInternal localizerInternal, String groupName, String typeName, String methodName) {
    this.factoryInternal = factoryInternal;
    this.localizerInternal = localizerInternal;
    this.groupName = groupName;
    this.typeName = typeName;
    this.methodName = methodName;
    localizerInstanceMap = new HashMap<>();
    localizerTypeUid = UID.createUid(getFullyQualifiedName(), this);
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
      UID<LocalizerInstance> instanceUid) {
    if (null == instanceUid) {
      throw new NullPointerException("instanceUid cannot be null");
    }
    LocalizerInstanceInternal localizerInstance = localizerInstanceMap.get(instanceUid);
    if (null == localizerInstance) {
      localizerInstance = factoryInternal.createUndefinedLocalizer().getLocalizerTypeInternal(null)
          .getLocalizerInstanceInternal(null);
    }
    return localizerInstance;
  }

  @Override
  public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
    return getLocalizerInstanceInternal(instanceUid);
  }

  @Override
  public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
    return Collections.unmodifiableSet(localizerInstanceMap.keySet());
  }

  @Override
  public UID<LocalizerType> getUid() {
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
