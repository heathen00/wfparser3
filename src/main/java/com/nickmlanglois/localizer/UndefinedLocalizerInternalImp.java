package com.nickmlanglois.localizer;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import com.nickmlanglois.uid.Uid;
import com.nickmlanglois.uid.UidFactory;

final class UndefinedLocalizerInternalImp implements LocalizerInternal {
  private final String undefinedName;
  private final Locale undefinedLocale;
  private final UndefinedLocalizerTypeInternalImp undefinedLocalizerTypeInternalImp;
  private final Set<Uid<LocalizerType>> undefinedLocalizerTypeUidSet;
  private final SortedSet<LocalizerBundle> undefinedLocalizerBundleSet;
  private final Uid<Localizer> undefinedLocalizerUid;

  UndefinedLocalizerInternalImp(UidFactory uidFactory) {
    undefinedName = "UNDEFINED";
    undefinedLocalizerUid = uidFactory.createUid(undefinedName, this);


    // this.localizerFactoryInternal.getUidFactory().createUid(undefinedName, this);
    final String undefinedLanguage = "xx";
    final String undefinedRegion = "ZZ";
    undefinedLocale =
        (new Locale.Builder()).setLanguage(undefinedLanguage).setRegion(undefinedRegion).build();
    undefinedLocalizerTypeInternalImp = new UndefinedLocalizerTypeInternalImp(uidFactory, this);
    undefinedLocalizerTypeUidSet = new HashSet<>();
    undefinedLocalizerTypeUidSet.add(undefinedLocalizerTypeInternalImp.getUid());
    undefinedLocalizerBundleSet = new TreeSet<>();
    undefinedLocalizerBundleSet.add(new UndefinedLocalizerBundleInternalImp(this));
  }

  @Override
  public Locale getLocale() {
    return undefinedLocale;
  }

  @Override
  public void setLocale(Locale locale) {}

  @Override
  public SortedSet<LocalizerBundle> getLocalizerBundleSet() {
    return Collections.unmodifiableSortedSet(undefinedLocalizerBundleSet);
  }

  @Override
  public LocalizerTypeInternal getLocalizerTypeInternal(Uid<LocalizerType> typeUid) {
    return undefinedLocalizerTypeInternalImp;
  }

  @Override
  public LocalizerType getLocalizerType(Uid<LocalizerType> typeUid) {
    return getLocalizerTypeInternal(typeUid);
  }

  @Override
  public Set<Uid<LocalizerType>> getLocalizerTypeUidSet() {
    return Collections.unmodifiableSet(undefinedLocalizerTypeUidSet);
  }

  @Override
  public LocalizerInstance getLocalizerInstance(Uid<LocalizerInstance> instanceUid) {
    return undefinedLocalizerTypeInternalImp.getLocalizerInstance(instanceUid);
  }

  @Override
  public Set<Uid<LocalizerInstance>> getLocalizerInstanceUidSet() {
    return undefinedLocalizerTypeInternalImp.getLocalizerInstanceUidSet();
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal) {
    throw new UnsupportedOperationException("cannot add localizer bundle to undefined localizer");
  }

  @Override
  public LocalizerTypeInternal addLocalizerTypeInternal(
      LocalizerTypeInternal localizerTypeInternal) {
    throw new UnsupportedOperationException("cannot add localizer type to undefined localizer");
  }

  @Override
  public String getName() {
    return undefinedName;
  }

  @Override
  public Uid<Localizer> getUid() {
    return undefinedLocalizerUid;
  }
}
