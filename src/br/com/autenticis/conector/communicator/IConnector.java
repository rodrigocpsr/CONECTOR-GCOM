package br.com.autenticis.conector.communicator;

public interface IConnector {
	
	// envia uma transação para o sistema central
	public String SendRequestToSistemaCentral(String pedido, String origem, String ambiente) throws Exception;
	
	// recebe um pedido do sistema central
	public int ReceiveRequestFromSistemaCentral(byte[] areaPedido, int tamAreaPedido, byte[] idPedido, byte[] conectorStat) throws Exception;
	
	// retorna para o sistema central o processamento do sistema central
	public int SendResponseToSistemaCentral(byte[] resposta, int tamResposta, byte[] idPedido, byte[] conectorStat) throws Exception;

}
