package com.ht.l10n;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.ht.common.UID;

final class LocalizerTypeInternalImp implements LocalizerTypeInternal {
  private final Localizer localizer;
  private final String groupName;
  private final String typeName;
  private final String instanceName;
  private final Map<UID<LocalizerField>, LocalizerField> localizerFieldMap;
  private final UID<LocalizerType> localizerTypeUid;

  LocalizerTypeInternalImp(Localizer localizer, String groupName, String typeName,
      String instanceName) {
    this.localizer = localizer;
    this.groupName = groupName;
    this.typeName = typeName;
    this.instanceName = instanceName;
    localizerFieldMap = new HashMap<>();
    localizerTypeUid = UID.createUid(getFullyQualifiedName(), this);
  }

  @Override
  public Localizer getLocalizer() {
    return localizer;
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
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    if (null == fieldUid) {
      throw new NullPointerException("fieldUid cannot be null");
    }
    LocalizerField localizerField = localizerFieldMap.get(fieldUid);
    if (null == localizerField) {
      localizerField = Factory.createFactory().createUndefinedLocalizer().getLocalizerField(null);
    }
    return localizerField;
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    return Collections.unmodifiableSet(localizerFieldMap.keySet());
  }

  @Override
  public UID<LocalizerType> getUid() {
    return localizerTypeUid;
  }

  @Override
  public void addLocalizerField(LocalizerField localizerField) {
    localizerFieldMap.put(localizerField.getUid(), localizerField);
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
