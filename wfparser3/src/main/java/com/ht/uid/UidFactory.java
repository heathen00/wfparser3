package com.ht.uid;

import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;

final class UidFactory {
  private static final UidFactory UID_FACTORY_SINGLETON = new UidFactory();

  static UidFactory getUidFactory() {
    return UID_FACTORY_SINGLETON;
  }

  private final Map<String, UidInternalImp<? extends Object>> uidMap;

  private UidFactory() {
    uidMap = new HashMap<>();
  }

  private void guardNotNull(String parameterName, Object parameter) {
    if (null == parameter) {
      throw new NullPointerException(parameterName + " cannot be null");
    }
  }

  private void guardNamingConvention(String parameterName, String parameter) {
    if (StringUtils.isBlank(parameter)) {
      throw new UnsupportedOperationException(parameterName + " cannot be empty");
    }
  }

  private void guardImplementsUniqueComponent(String parameterName, Object parameter) {
    if (!(parameter instanceof UniqueComponent)) {
      throw new UnsupportedOperationException(
          parameterName + " must implement " + UniqueComponent.class.getCanonicalName());
    }
  }

  @SuppressWarnings("unchecked")
  <T> UID<T> createUid(String key, T component) {
    guardNotNull("key", key);
    guardNotNull("component", component);
    guardNamingConvention("key", key);
    guardImplementsUniqueComponent("component", component);
    UidInternalImp<? extends Object> existingUid = uidMap.get(key);
    System.out.println("component class: " + component.getClass());
    System.out.println("existingUid component class: "
        + (null == existingUid ? null : existingUid.getComponent().getClass()));
    if (existingUid != null) {
      System.out.println("equals: " + component.equals(existingUid.getComponent()));
    }
    UidInternalImp<T> newUidImp = null;
    if (null != existingUid) {
      if (!existingUid.getComponent().getClass().equals(component.getClass())
          || !existingUid.getComponent().equals(component)) {
        throw new UnsupportedOperationException(
            "UniqueComponent with key " + key + " already exists but with different component");
      }
      newUidImp = (UidInternalImp<T>) existingUid;
      System.out.println("here");
    } else {
      newUidImp = new UidInternalImp<T>(key, component);
      uidMap.put(key, newUidImp);
    }
    return newUidImp;
  }

  void resetAll() {
    internalResetAll();
  }

  private void internalResetAll() {
    uidMap.clear();
  }
}
