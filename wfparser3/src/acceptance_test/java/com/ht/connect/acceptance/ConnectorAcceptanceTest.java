package com.ht.connect.acceptance;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import com.ht.connect.Connector;
import com.ht.connect.ConnectorFactory;
import com.ht.uid.TestableUidFactory;
import com.ht.uid.UidFactory;

public class ConnectorAcceptanceTest {

  // TODO You will need to add some functionality to some of these factories to access the connector
  // to get access to the factories since some
  // subsystems are used by multiple other subsystems.

  private ConnectorFactory connectorFactory;
  private Connector connector;

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

    assertEquals(expectedProductionUidFactory, uidFactory);
  }

  @Test
  public void Connector_setTestableUidFactory_testableUidFactoryIsSet() {
    UidFactory expectedTestableUidFactory = TestableUidFactory.getTestableUidFactory();

    connector.setUidFactory(expectedTestableUidFactory);
    UidFactory uidFactory = connector.getUidFactory();

    assertEquals(expectedTestableUidFactory, uidFactory);
  }

  @Test
  public void Connector_setProductionUidFactoryThenregisterTestableUidFactory_productionUidFactoryIsSetThenTestableUidFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setWrapperFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_getWrapperFactoryWithoutFirstSettingWrapperFactory_unsupportedOperationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setProductionWrapperFactory_productionWrapperFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setStubWrapperFactory_stubWrapperFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setProductionWrapperFactoryThenSetStubWrapperFactory_productionWrapperFactoryIsSetThenStubWrapperFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setLocalizerFactoryWithNullParameter_nullPointerExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_getLocalizerFactoryWithoutFirstSettingLocalizerFactory_unsupportedOperationExceptionIsThrown() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setProductionLocalizerFactory_productionLocalizerFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setTestableLocalizerFactory_testableLocalizerFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setStubLocalizerFactory_stubLocalizerFactoryIsSet() {
    fail("Not yet implemented");
  }

  @Test
  @Ignore("Not worked on yet")
  public void Connector_setProductionLocalizerFactoryThenTestableLocalizerFactory_productionLocalizerFactoryIsSetThenTestableLocalizerFactoryIsSet() {
    fail("Not yet implemented");
  }
}
