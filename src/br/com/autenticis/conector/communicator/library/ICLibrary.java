package br.com.autenticis.conector.communicator.library;

public interface ICLibrary {

	int StartConector(byte[] conectorStat, String icomPath);
	int SendRequestToBR(byte[] pedido, int tamPedido, byte[] respostaBytes,int tamResposta, String siglaApp, byte[] conectorStat,int transTimeout);
	int ReceiveBRRequest(byte[] areaPedido, int tamAreaPedido, byte[] idPedido,byte[] conectorStat);
	int SendBRResponse(byte[] resposta, int tamResposta, byte[] idPedido,byte[] conectorStat);
	int EndConector(byte[] conectorStat);
}
