package com.ht.localizer;

import com.ht.uid.UID;
import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

final class UndefinedLocalizerInternalImp implements LocalizerInternal {
  private final LocalizerFactoryInternal localizerFactoryInternal;
  private final String undefinedName;
  private final Locale undefinedLocale;
  private final UndefinedLocalizerTypeInternalImp undefinedLocalizerTypeInternalImp;
  private final Set<UID<LocalizerType>> undefinedLocalizerTypeUidSet;
  private final SortedSet<LocalizerBundle> undefinedLocalizerBundleSet;
  private final UID<Localizer> undefinedLocalizerUid;

  UndefinedLocalizerInternalImp(LocalizerFactoryInternal localizerFactoryInternal) {
    this.localizerFactoryInternal = localizerFactoryInternal;
    undefinedName = "UNDEFINED";
    undefinedLocalizerUid =
        this.localizerFactoryInternal.getUidFactory().createUid(undefinedName, this);
    final String undefinedLanguage = "xx";
    final String undefinedRegion = "ZZ";
    undefinedLocale =
        (new Locale.Builder()).setLanguage(undefinedLanguage).setRegion(undefinedRegion).build();
    undefinedLocalizerTypeInternalImp =
        new UndefinedLocalizerTypeInternalImp(this.localizerFactoryInternal, this);
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
  public LocalizerTypeInternal getLocalizerTypeInternal(UID<LocalizerType> typeUid) {
    return undefinedLocalizerTypeInternalImp;
  }

  @Override
  public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
    return getLocalizerTypeInternal(typeUid);
  }

  @Override
  public Set<UID<LocalizerType>> getLocalizerTypeUidSet() {
    return Collections.unmodifiableSet(undefinedLocalizerTypeUidSet);
  }

  @Override
  public LocalizerInstance getLocalizerInstance(UID<LocalizerInstance> instanceUid) {
    return undefinedLocalizerTypeInternalImp.getLocalizerInstance(instanceUid);
  }

  @Override
  public Set<UID<LocalizerInstance>> getLocalizerInstanceUidSet() {
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
  public UID<Localizer> getUid() {
    return undefinedLocalizerUid;
  }
}
