package com.ht.l10n;

import com.ht.uid.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

final class UndefinedLocalizerTypeInternalImp implements LocalizerTypeInternal {
  private final UndefinedLocalizerInternalImp undefinedLocalizer;
  private final String groupName;
  private final String typeName;
  private final String methodName;
  private final UID<LocalizerType> undefinedLocalizerTypeUid;
  private final LocalizerInstanceInternal undefinedLocalizerInstance;
  private final Set<UID<LocalizerInstance>> undefinedLocalizerInstanceUidSet;

  UndefinedLocalizerTypeInternalImp(UndefinedLocalizerInternalImp undefinedLocalizer) {
    this.undefinedLocalizer = undefinedLocalizer;
    groupName = "undef.group";
    typeName = "undef.type";
    methodName = "undef.method";
    undefinedLocalizerTypeUid =
        UID.createUid(String.join(".", groupName, typeName, methodName), this);
    undefinedLocalizerInstance = new UndefinedLocalizerInstanceInternalImp(this);
    undefinedLocalizerInstanceUidSet = new HashSet<>();
    undefinedLocalizerInstanceUidSet.add(undefinedLocalizerInstance.getUid());
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
  public String getMethodName() {
    return methodName;
  }

  @Override
  public LocalizerInstanceInternal getLocalizerInstanceInternal(
      UID<LocalizerInstance> instanceUid) {
    return undefinedLocalizerInstance;
  }

  @Override
  public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
    return getLocalizerInstanceInternal(instanceUid);
  }

  @Override
  public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
    return Collections.unmodifiableSet(undefinedLocalizerInstanceUidSet);
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public LocalizerInstanceInternal addLocalizerInstanceInternal(
      LocalizerInstanceInternal localizerInstanceInternal) {
    throw new UnsupportedOperationException(
        "cannot add localizer instance to undefined localizer type");
  }

  @Override
  public String getFullyQualifiedName() {
    return String.join(".", groupName, typeName, methodName);
  }
}
