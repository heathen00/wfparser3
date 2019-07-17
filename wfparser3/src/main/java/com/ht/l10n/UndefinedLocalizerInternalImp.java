package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

final class UndefinedLocalizerInternalImp implements LocalizerInternal {
  private final Locale undefinedLocale;
  private final UndefinedLocalizerTypeInternalImp undefinedLocalizerTypeInternalImp;
  private final Set<UID<LocalizerType>> undefinedLocalizerTypeUidSet;
  private final SortedSet<LocalizerBundle> undefinedLocalizerBundleSet;

  UndefinedLocalizerInternalImp() {
    final String undefinedLanguage = "xx";
    final String undefinedRegion = "ZZ";
    undefinedLocale =
        (new Locale.Builder()).setLanguage(undefinedLanguage).setRegion(undefinedRegion).build();
    undefinedLocalizerTypeInternalImp = new UndefinedLocalizerTypeInternalImp(this);
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
  public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
    return Collections.unmodifiableSet(undefinedLocalizerTypeUidSet);
  }

  @Override
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    return undefinedLocalizerTypeInternalImp.getLocalizerField(fieldUid);
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    return undefinedLocalizerTypeInternalImp.getLocalizerFieldKeySet();
  }

  @Override
  public boolean isDefined() {
    return false;
  }

  @Override
  public LocalizerBundleInternal addLocalizerBundleInternal(
      LocalizerBundleInternal localizerBundleInternal) {
    return localizerBundleInternal;
  }

  @Override
  public LocalizerTypeInternal addLocalizerTypeInternal(
      LocalizerTypeInternal localizerTypeInternal) {
    return localizerTypeInternal;
  }
}
