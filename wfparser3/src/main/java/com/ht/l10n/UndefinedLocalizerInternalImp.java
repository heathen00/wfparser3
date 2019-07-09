package com.ht.l10n;

import com.ht.common.UID;

import java.util.Collections;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

final class UndefinedLocalizerInternalImp implements LocalizerInternal {
  private final Locale undefinedLocale;
  private final UndefinedLocalizerTypeInternalImp undefinedLocalizerType;
  private final Set<UID<LocalizerType>> undefinedLocalizerTypeUidSet;
  private final Set<LocalizerBundle> undefinedLocalizerBundleSet;

  UndefinedLocalizerInternalImp() {
    final String undefinedLanguage = "xx";
    final String undefinedRegion = "ZZ";
    undefinedLocale =
        new Locale.Builder().setLanguage(undefinedLanguage).setRegion(undefinedRegion).build();
    undefinedLocalizerType = new UndefinedLocalizerTypeInternalImp(this);
    undefinedLocalizerTypeUidSet = new HashSet<>();
    undefinedLocalizerTypeUidSet.add(undefinedLocalizerType.getUid());
    undefinedLocalizerBundleSet = new HashSet<>();
    undefinedLocalizerBundleSet.add(new UndefinedLocalizerBundleInternalImp(this));
  }

  @Override
  public Locale getLocale() {
    return undefinedLocale;
  }

  @Override
  public void setLocale(Locale locale) {}

  @Override
  public Set<LocalizerBundle> getLocalizerBundleSet() {
    return Collections.unmodifiableSet(undefinedLocalizerBundleSet);
  }

  @Override
  public LocalizerType getLocalizerType(UID<LocalizerType> typeUid) {
    return undefinedLocalizerType;
  }

  @Override
  public Set<UID<LocalizerType>> getLocalizerTypeKeySet() {
    return Collections.unmodifiableSet(undefinedLocalizerTypeUidSet);
  }

  @Override
  public LocalizerField getLocalizerField(UID<LocalizerField> fieldUid) {
    return undefinedLocalizerType.getLocalizerField(fieldUid);
  }

  @Override
  public Set<UID<LocalizerField>> getLocalizerFieldKeySet() {
    return undefinedLocalizerType.getLocalizerFieldKeySet();
  }

  @Override
  public boolean isDefined() {
    return false;
  }
}
