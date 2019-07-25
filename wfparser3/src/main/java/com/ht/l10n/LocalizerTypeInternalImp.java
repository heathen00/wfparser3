package com.ht.l10n;

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
  private final String instanceName;
  private final Map<UID<LocalizerField>, LocalizerFieldInternal> localizerFieldMap;
  private final UID<LocalizerType> localizerTypeUid;

  LocalizerTypeInternalImp(LocalizerFactoryInternal factoryIntrnal, LocalizerInternal localizerInternal,
      String groupName, String typeName, String instanceName) {
    this.factoryInternal = factoryIntrnal;
    this.localizerInternal = localizerInternal;
    this.groupName = groupName;
    this.typeName = typeName;
    this.instanceName = instanceName;
    localizerFieldMap = new HashMap<>();
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
  public String getInstanceName() {
    return instanceName;
  }

  @Override
  public LocalizerFieldInternal getLocalizerFieldInternal(UID<LocalizerField> fieldUid) {
    if (null == fieldUid) {
      throw new NullPointerException("fieldUid cannot be null");
    }
    LocalizerFieldInternal localizerField = localizerFieldMap.get(fieldUid);
    if (null == localizerField) {
      localizerField = factoryInternal.createUndefinedLocalizer().getLocalizerTypeInternal(null)
          .getLocalizerFieldInternal(null);
    }
    return localizerField;
  }

  @Override
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    return getLocalizerFieldInternal(fieldUid);
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldUidSet() {
    return Collections.unmodifiableSet(localizerFieldMap.keySet());
  }

  @Override
  public UID<LocalizerType> getUid() {
    return localizerTypeUid;
  }

  @Override
  public LocalizerFieldInternal addLocalizerFieldInternal(LocalizerFieldInternal localizerField) {
    localizerFieldMap.put(localizerField.getUid(), localizerField);
    return localizerField;
  }

  @Override
  public boolean isDefined() {
    return true;
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", groupName, typeName, instanceName);
  }
}
