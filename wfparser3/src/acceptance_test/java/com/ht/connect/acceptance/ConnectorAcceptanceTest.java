package com.ht.connect.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.ht.connect.Connector;
import com.ht.connect.ConnectorFactory;
import com.ht.localizer.LocalizerFactory;
import com.ht.localizer.StubLocalizerFactory;
import com.ht.localizer.TestableLocalizerFactory;
import com.ht.uid.TestableUidFactory;
import com.ht.uid.UidFactory;
import com.ht.wrap.StubWrapperFactory;
import com.ht.wrap.WrapperFactory;

public class ConnectorAcceptanceTest {
  private ConnectorFactory connectorFactory;
  private Connector connector;
  private UidFactory expectedProductionUidFactory = UidFactory.createUidFactory();
  private WrapperFactory expectedProductionWrapperFactory = WrapperFactory.createWrapperFactory();

  @Before
  public void setup() {
    connectorFactory = ConnectorFactory.createConnectorFactory();
    connector = connectorFactory.createConnector();
  }

  @Test
  public void Connector_setUpTestingAssets_testingAssetsSetUp() {
    assertNotNull(connectorFactory);
    assertNotNull(connector);
  }

  @Test(expected = NullPointerException.class)
  public void Connector_setUidFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    connector.setUidFactory(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Connector_getUidFactoryWithoutFirstSettingUidFactory_unsupportedOperationExceptionIsThrown() {
    connector.getUidFactory();
  }

  @Test
  public void Connector_setProductionUidFactory_productionUidFactoryIsSet() {
    UidFactory expectedProductionUidFactory = UidFactory.createUidFactory();

    connector.setUidFactory(expectedProductionUidFactory);
    UidFactory uidFactory = connector.getUidFactory();

    assertNotNull(uidFactory);
    assertEquals(expectedProductionUidFactory, uidFactory);
  }

  @Test
  public void Connector_setTestableUidFactory_testableUidFactoryIsSet() {
    UidFactory expectedTestableUidFactory = TestableUidFactory.getTestableUidFactory();

    connector.setUidFactory(expectedTestableUidFactory);
    UidFactory uidFactory = connector.getUidFactory();

    assertNotNull(uidFactory);
    assertEquals(expectedTestableUidFactory, uidFactory);
  }

  @Test
  public void Connector_setProductionUidFactoryThenregisterTestableUidFactory_productionUidFactoryIsSetThenTestableUidFactoryIsSet() {
    UidFactory expectedProductionUidFactory = UidFactory.createUidFactory();
    UidFactory expectedTestableUidFactory = TestableUidFactory.getTestableUidFactory();

    connector.setUidFactory(expectedProductionUidFactory);
    UidFactory uidFactory = connector.getUidFactory();

    assertNotNull(uidFactory);
    assertEquals(expectedProductionUidFactory, uidFactory);

    connector.setUidFactory(expectedTestableUidFactory);
    uidFactory = connector.getUidFactory();

    assertNotNull(uidFactory);
    assertEquals(expectedTestableUidFactory, uidFactory);
  }

  @Test(expected = NullPointerException.class)
  public void Connector_setWrapperFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    connector.setWrapperFactory(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Connector_getWrapperFactoryWithoutFirstSettingWrapperFactory_unsupportedOperationExceptionIsThrown() {
    connector.getWrapperFactory();
  }

  @Test
  public void Connector_setProductionWrapperFactory_productionWrapperFactoryIsSet() {
    WrapperFactory expectedProductionWrapperFactory = WrapperFactory.createWrapperFactory();
    connector.setWrapperFactory(expectedProductionWrapperFactory);

    WrapperFactory wrapperFactory = connector.getWrapperFactory();

    assertNotNull(wrapperFactory);
    assertEquals(expectedProductionWrapperFactory, wrapperFactory);
  }

  @Test
  public void Connector_setStubWrapperFactory_stubWrapperFactoryIsSet() {
    WrapperFactory expectedStubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();
    connector.setWrapperFactory(expectedStubWrapperFactory);

    WrapperFactory wrapperFactory = connector.getWrapperFactory();

    assertNotNull(wrapperFactory);
    assertEquals(expectedStubWrapperFactory, wrapperFactory);
  }

  @Test
  public void Connector_setProductionWrapperFactoryThenSetStubWrapperFactory_productionWrapperFactoryIsSetThenStubWrapperFactoryIsSet() {
    WrapperFactory expectedProductionWrapperFactory = WrapperFactory.createWrapperFactory();
    WrapperFactory expectedStubWrapperFactory = StubWrapperFactory.createStubWrapperFactory();

    connector.setWrapperFactory(expectedProductionWrapperFactory);
    WrapperFactory wrapperFactory = connector.getWrapperFactory();

    assertNotNull(wrapperFactory);
    assertEquals(expectedProductionWrapperFactory, wrapperFactory);

    connector.setWrapperFactory(expectedStubWrapperFactory);
    wrapperFactory = connector.getWrapperFactory();

    assertNotNull(wrapperFactory);
    assertEquals(expectedStubWrapperFactory, wrapperFactory);
  }

  @Test(expected = NullPointerException.class)
  public void Connector_setLocalizerFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    connector.setLocalizerFactory(null);
  }

  @Test(expected = UnsupportedOperationException.class)
  public void Connector_getLocalizerFactoryWithoutFirstSettingLocalizerFactory_unsupportedOperationExceptionIsThrown() {
    connector.getLocalizerFactory();
  }

  @Test
  public void Connector_setProductionLocalizerFactory_productionLocalizerFactoryIsSet() {
    LocalizerFactory expectedProductionLocalizerFactory = LocalizerFactory
        .createLocalizerFactory(expectedProductionWrapperFactory, expectedProductionUidFactory);

    connector.setLocalizerFactory(expectedProductionLocalizerFactory);
    LocalizerFactory localizerFactory = connector.getLocalizerFactory();

    assertNotNull(localizerFactory);
    assertEquals(expectedProductionLocalizerFactory, localizerFactory);
  }

  @Test
  public void Connector_setTestableLocalizerFactory_testableLocalizerFactoryIsSet() {
    LocalizerFactory expectedTestableLocalizerFactory =
        TestableLocalizerFactory.getTestableLocalizerFactory(expectedProductionWrapperFactory,
            expectedProductionUidFactory);

    connector.setLocalizerFactory(expectedTestableLocalizerFactory);
    LocalizerFactory localizerFactory = connector.getLocalizerFactory();

    assertNotNull(localizerFactory);
    assertEquals(expectedTestableLocalizerFactory, localizerFactory);
  }

  @Test
  public void Connector_setStubLocalizerFactory_stubLocalizerFactoryIsSet() {
    LocalizerFactory expectedStubLocalizerFactory =
        StubLocalizerFactory.createStubLocalizerFactory();

    connector.setLocalizerFactory(expectedStubLocalizerFactory);
    LocalizerFactory localizeractory = connector.getLocalizerFactory();

    assertNotNull(localizeractory);
    assertEquals(expectedStubLocalizerFactory, localizeractory);
  }

  @Test
  public void Connector_setProductionLocalizerFactoryThenTestableLocalizerFactory_productionLocalizerFactoryIsSetThenTestableLocalizerFactoryIsSet() {
    LocalizerFactory expectedProductionLocalizerFactory = LocalizerFactory
        .createLocalizerFactory(expectedProductionWrapperFactory, expectedProductionUidFactory);
    LocalizerFactory expectedTestableLocalizerFactory =
        StubLocalizerFactory.createStubLocalizerFactory();

    connector.setLocalizerFactory(expectedProductionLocalizerFactory);
    LocalizerFactory localizerFactory = connector.getLocalizerFactory();

    assertNotNull(localizerFactory);
    assertEquals(expectedProductionLocalizerFactory, localizerFactory);

    connector.setLocalizerFactory(expectedTestableLocalizerFactory);
    localizerFactory = connector.getLocalizerFactory();

    assertNotNull(localizerFactory);
    assertEquals(expectedTestableLocalizerFactory, localizerFactory);
  }
}
