package com.ht.connect;

public class ConnectorFactoryImp implements ConnectorFactory {

  @Override
  public Connector createConnector() {
    return new ConnectorImp();
  }
}
