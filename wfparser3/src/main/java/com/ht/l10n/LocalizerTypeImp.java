package com.ht.l10n;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.ht.common.UID;

final class LocalizerTypeImp implements LocalizerType {
  private final Localizer localizer;
  private final String groupName;
  private final String typeName;
  private final String instanceName;
  private final Map<UID<LocalizerField>, LocalizerField> localizerFieldMap;
  private final UID<LocalizerType> localizerTypeUid;

  LocalizerTypeImp(Localizer localizer, String groupName, String typeName, String instanceName) {
    this.localizer = localizer;
    this.groupName = groupName;
    this.typeName = typeName;
    this.instanceName = instanceName;
    localizerFieldMap = new HashMap<>();
    localizerTypeUid =
        UID.createUid(String.join(".", getGroupName(), getTypeName(), getInstanceName()), this);
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
    // TODO Auto-generated method stub
    return null;
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    return localizerFieldMap.keySet();
  }

  @Override
  public UID<LocalizerType> getUid() {
    return localizerTypeUid;
  }
}
