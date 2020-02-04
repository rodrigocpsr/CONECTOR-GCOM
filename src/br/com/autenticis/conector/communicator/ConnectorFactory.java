package br.com.autenticis.conector.communicator;

public class ConnectorFactory {

	public static IConnector getConnectorFactory(String codigoConnector) {
		return new ConnectorICOM();
	}
	
}
