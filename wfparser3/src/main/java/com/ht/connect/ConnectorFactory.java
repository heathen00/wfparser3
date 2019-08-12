package com.ht.connect;

public interface ConnectorFactory {
  static ConnectorFactory createConnectorFactory() {
    return new ConnectorFactoryImp();
  }

  Connector createConnector();
}
