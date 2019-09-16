package com.nickmlanglois.localizer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import com.nickmlanglois.uid.Uid;
import com.nickmlanglois.uid.UidFactory;

final class UndefinedLocalizerTypeInternalImp implements LocalizerTypeInternal {
  private final UndefinedLocalizerInternalImp undefinedLocalizer;
  private final String groupName;
  private final String typeName;
  private final String methodName;
  private final Uid<LocalizerType> undefinedLocalizerTypeUid;
  private final LocalizerInstanceInternal undefinedLocalizerInstance;
  private final Set<Uid<LocalizerInstance>> undefinedLocalizerInstanceUidSet;

  UndefinedLocalizerTypeInternalImp(UidFactory uidFactory,
      UndefinedLocalizerInternalImp undefinedLocalizer) {
    this.undefinedLocalizer = undefinedLocalizer;
    groupName = "undef.group";
    typeName = "undef.type";
    methodName = "undef.method";
    undefinedLocalizerTypeUid =
        uidFactory.createUid(String.join(".", groupName, typeName, methodName), this);
    undefinedLocalizerInstance = new UndefinedLocalizerInstanceInternalImp(uidFactory, this);
    undefinedLocalizerInstanceUidSet = new HashSet<>();
    undefinedLocalizerInstanceUidSet.add(undefinedLocalizerInstance.getUid());
  }

  @Override
  public Uid<LocalizerType> getUid() {
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
      Uid<LocalizerInstance> instanceUid) {
    return undefinedLocalizerInstance;
  }

  @Override
  public LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid) {
    return getLocalizerInstanceInternal(instanceUid);
  }

  @Override
  public Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet() {
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
