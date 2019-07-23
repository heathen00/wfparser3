package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class UndefinedLocalizerTypeInternalImp implements LocalizerTypeInternal {
  private final UndefinedLocalizerInternalImp undefinedLocalizer;
  private final String groupName;
  private final String typeName;
  private final String instanceName;
  private final UID<LocalizerType> undefinedLocalizerTypeUid;
  private final LocalizerFieldInternal undefinedLocalizerField;
  private final Set<UID<LocalizerField>> undefinedLocalizerFieldUidSet;

  UndefinedLocalizerTypeInternalImp(UndefinedLocalizerInternalImp undefinedLocalizer) {
    this.undefinedLocalizer = undefinedLocalizer;
    groupName = "undef.group";
    typeName = "undef.type";
    instanceName = "undef.instance";
    undefinedLocalizerTypeUid =
        UID.createUid(String.join(".", groupName, typeName, instanceName), this);
    undefinedLocalizerField = new UndefinedLocalizerFieldInternalImp(this);
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
  public LocalizerFieldInternal getLocalizerFieldInternal(UID<LocalizerField> fieldUid) {
    return undefinedLocalizerField;
  }

  @Override
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    return getLocalizerFieldInternal(fieldUid);
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldUidSet() {
    return Collections.unmodifiableSet(undefinedLocalizerFieldUidSet);
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public LocalizerFieldInternal addLocalizerFieldInternal(
      LocalizerFieldInternal localizerFieldInternal) {
    throw new UnsupportedOperationException(
        "cannot add localizer field to undefined localizer type");
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", groupName, typeName, instanceName);
  }
}
