package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class UndefinedLocalizerTypeImp implements LocalizerTypeInternal {
  private final UndefinedLocalizerImp undefinedLocalizer;
  private final String groupName;
  private final String typeName;
  private final String instanceName;
  private final UID<LocalizerType> undefinedLocalizerTypeUid;
  private final LocalizerField undefinedLocalizerField;
  private final Set<UID<LocalizerField>> undefinedLocalizerFieldUidSet;

  UndefinedLocalizerTypeImp(UndefinedLocalizerImp undefinedLocalizer) {
    this.undefinedLocalizer = undefinedLocalizer;
    groupName = "undef.group";
    typeName = "undef.type";
    instanceName = "undef.instance";
    undefinedLocalizerTypeUid =
        UID.createUid(String.join(".", groupName, typeName, instanceName), this);
    undefinedLocalizerField = new UndefinedLocalizerFieldImp(this);
    undefinedLocalizerFieldUidSet = new HashSet<>();
    undefinedLocalizerFieldUidSet.add(undefinedLocalizerField.getUid());
  }

  @Override
  public UID<LocalizerType> getUid() {
    return undefinedLocalizerTypeUid;
  }

  @Override
  public Localizer getLocalizer() {
    return undefinedLocalizer;
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
    return undefinedLocalizerField;
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    return Collections.unmodifiableSet(undefinedLocalizerFieldUidSet);
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public void addLocalizerField(LocalizerField localizerField) {
    // TODO Auto-generated method stub

  }
}
