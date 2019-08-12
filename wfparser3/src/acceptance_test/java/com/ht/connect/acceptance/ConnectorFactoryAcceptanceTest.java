package com.ht.connect.acceptance;

import static org.junit.Assert.*;
import org.junit.Test;
import com.ht.connect.Connector;
import com.ht.connect.ConnectorFactory;

public class ConnectorFactoryAcceptanceTest {

  @Test
  public void ConnectorFactory_createConnectorFactory_connectorFactoryCreated() {
    ConnectorFactory connectorFactory = ConnectorFactory.createConnectorFactory();

    assertNotNull(connectorFactory);
  }

  @Test
  public void ConnectorFactory_createConnector_connectorCreated() {
    ConnectorFactory connectorFactory = ConnectorFactory.createConnectorFactory();

    Connector connector = connectorFactory.createConnector();

    assertNotNull(connector);
  }
}
