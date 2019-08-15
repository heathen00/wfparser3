package com.ht.localizer;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.ht.uid.UidFactory;
import com.ht.wrap.StubWrapperFactory;
import com.ht.wrap.WrapperFactory;

public class LocalizerFactoryAndStubWrapperFactoryUnitTest {

  private LocalizerFactoryInternal localizerFactoryInternal;

  @Before
  public void setup() {
    localizerFactoryInternal = new LocalizerFactoryInternalImp(
        WrapperFactory.createWrapperFactory(), UidFactory.createUidFactory());
    localizerFactoryInternal.resetAll();
  }

  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_setWrapperFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    localizerFactoryInternal.setWrapperFactory((com.ht.wrap.WrapperFactory) null);
  }

  @Test
  public void LocalizerFactory_setWrapperFactoryWithStubWrapperFactory_stubWrapperFactoryIsSet() {
    WrapperFactory expectedWrapperFactory = StubWrapperFactory.createStubWrapperFactory();

    localizerFactoryInternal.setWrapperFactory(expectedWrapperFactory);

    assertEquals(expectedWrapperFactory, localizerFactoryInternal.getWrapperFactory());
  }

  @Test
  public void LocalizerFactory_setWrapperFactoryWithProductionWrapperFactory_productionWrapperFactoryIsSet() {
    WrapperFactory initialWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    localizerFactoryInternal.setWrapperFactory(initialWrapperFactory);
    assertEquals(initialWrapperFactory, localizerFactoryInternal.getWrapperFactory());
    WrapperFactory expectedWrapperFactory = WrapperFactory.createWrapperFactory();

    localizerFactoryInternal.setWrapperFactory(expectedWrapperFactory);

    assertEquals(expectedWrapperFactory, localizerFactoryInternal.getWrapperFactory());
  }
}
