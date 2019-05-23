package com.ht.wfp3.api.statement;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import java.lang.reflect.Method;
import java.util.Map;

/**
 * Tester class to check if the class being tested performs defensive copying of its data members
 * when created or copied if the data members are unknown potentially immutable implementations. The
 * purpose is to test classes such as BigDecimal or classes that are accessible by extensible
 * interfaces that may have been subclassed to allow them to be modified external to the system.
 * This would break the immutability contract. This class does NOT check whether the internal data
 * members have actually been declared as final.
 * 
 * @author nickl
 *
 * @param <T>
 */
public final class MutabilityTester<T> {
  public static abstract class Creator<T> {
    private boolean isMutable;

    public abstract T create();

    public abstract T copy(T o);

    public abstract Map<String, Object> getExpectedMemberData();

    public <S> S mutable(S o) {
      if (isMutable) {
        return spy(o);
      } else {
        return o;
      }
    }

    boolean isMutable() {
      return isMutable;
    }

    void setMutable(boolean isMutable) {
      this.isMutable = isMutable;
    }
  }

  private final Creator<T> creator;

  public MutabilityTester(Creator<T> creator) {
    this.creator = creator;
  }

  private <S extends Object> void assertImmutableMemberData(Map<String, S> methodDataMap, T o)
      throws Exception {
    Class<? extends Object> clazz = o.getClass();
    for (String methodName : methodDataMap.keySet()) {
      Method method = clazz.getMethod(methodName);
      method.setAccessible(true);
      assertEquals(methodName, methodDataMap.get(methodName).getClass(),
          method.invoke(o).getClass());
    }
  }

  private <S extends Object> void assertCopyImmutability(T expected, T actual) throws Exception {
    assertEquals(expected.getClass(), actual.getClass());
  }

  public <S extends Object> void assertImmutability() throws Exception {
    creator.setMutable(true);
    T o = creator.create();
    Map<String, Object> expectedMethodDataMap = creator.getExpectedMemberData();

    assertImmutableMemberData(expectedMethodDataMap, o);

    creator.setMutable(true);
    T mutableO = creator.mutable(o);
    T copiedO = creator.copy(mutableO);

    assertCopyImmutability(o, copiedO);
    assertImmutableMemberData(expectedMethodDataMap, copiedO);
  }

  // TODO you should also test to ensure that the objects are ONLY copied if they have been
  // identified as not being the correct type. That is not copied every time.

}
