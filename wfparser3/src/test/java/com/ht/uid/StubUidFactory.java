package com.ht.uid;

public final class StubUidFactory implements UidFactory {
  public static StubUidFactory createStubUidFactory() {
    return new StubUidFactory();
  }

  private StubUidFactory() {}

  @Override
  public void resetAll() {}

  @Override
  public <T> UID<T> createUid(String key, T component) {
    return new UidInternalImp<T>(key, component);
  }
}
