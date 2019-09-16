package com.nickmlanglois.localizer;

import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import com.nickmlanglois.localizer.LocalizerFactoryInternal;
import com.nickmlanglois.localizer.LocalizerFactoryInternalImp;
import com.nickmlanglois.uid.UidFactory;
import com.nickmlanglois.wrap.StubWrapperFactory;
import com.nickmlanglois.wrap.WrapperFactory;

public class LocalizerFactoryAndStubWrapperFactoryUnitTest {

  @Rule
  public ExpectedException thrown = ExpectedException.none();

  private LocalizerFactoryInternal localizerFactoryInternal;

  @Before
  public void setup() {
    localizerFactoryInternal = new LocalizerFactoryInternalImp(
        WrapperFactory.createWrapperFactory(), UidFactory.createUidFactory());
    localizerFactoryInternal.resetAll();
  }

  @Test
  public void LocalizerFactory_setWrapperFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    thrown.expect(NullPointerException.class);
    thrown.expectMessage("wrapperFactory cannot be null");

    localizerFactoryInternal.setWrapperFactory((com.nickmlanglois.wrap.WrapperFactory) null);
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
