package com.ht.l10n;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import com.ht.common.UID;

final class LocalizerTypeImp implements LocalizerType {
  private final String groupName;
  private final String typeName;
  private final String instanceName;
  private final Map<UID<LocalizerField>, LocalizerField> localizerFieldMap;

  LocalizerTypeImp(String groupName, String typeName, String instanceName) {
    this.groupName = groupName;
    this.typeName = typeName;
    this.instanceName = instanceName;
    localizerFieldMap = new HashMap<>();
  }

  @Override
  public Localizer getLocalizer() {
    // TODO Auto-generated method stub
    return null;
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

}
