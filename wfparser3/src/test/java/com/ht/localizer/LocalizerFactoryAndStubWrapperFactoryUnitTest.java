package com.ht.localizer;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import com.ht.localizer.LocalizerFactoryInternal;
import com.ht.localizer.SystemInternal;
import com.ht.wrap.StubWrapperFactory;
import com.ht.wrap.WrapperFactory;

public class LocalizerFactoryAndStubWrapperFactoryUnitTest {

  private LocalizerFactoryInternal localizerFactoryInternal;
  private StubWrapperFactory stubWrapperFactory;

  @Before
  public void setup() {
    localizerFactoryInternal = SystemInternal.getSystemInternal().getFactoryInternal();
    localizerFactoryInternal.resetAll();
    stubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
  }


  @Test(expected = NullPointerException.class)
  public void LocalizerFactory_setWrapperFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    localizerFactoryInternal.setWrapperFactory((com.ht.wrap.WrapperFactory) null);
  }

  @Test
  public void LocalizerFactory_setWrapperFactoryWithStubWrapperFactory_stubWrapperFactoryIsSet() {
    WrapperFactory expectedWrapperFactory = StubWrapperFactory.createStubWrapperFactory();

    localizerFactoryInternal.setWrapperFactory(stubWrapperFactory);

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

  @Test
  public void LocalizerFactory_doNotSetWrapperFactory_productionWrapperFactoryIsSet() {
    WrapperFactory myWrapperFactory = localizerFactoryInternal.getWrapperFactory();

    assertEquals(WrapperFactory.createWrapperFactory(), myWrapperFactory);
  }

  @Test
  public void LocalizerFactory_resetAll_productionWrapperFactoryIsSet() {
    final WrapperFactory expectedWrapperFactory = WrapperFactory.createWrapperFactory();
    localizerFactoryInternal.setWrapperFactory(stubWrapperFactory);

    localizerFactoryInternal.resetAll();

    assertEquals(expectedWrapperFactory, localizerFactoryInternal.getWrapperFactory());
  }

}
