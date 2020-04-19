package br.com.autenticis.conector.communicator;

import org.apache.log4j.Logger;

import br.com.autenticis.conector.communicator.library.ICLibrary;
import br.com.autenticis.conector.communicator.library.LibraryFactory;
import br.com.autenticis.conector.exception.ConnectorException;
import br.com.autenticis.conector.util.Token;

public class ConnectorICOM implements IConnector {

	private static final Logger logger = Logger.getLogger(ConnectorICOM.class);
	
	/**
	 * Enviar um pedido para o sistema central do SERPRO.
	 * 
	 * @author rrodrigues / asodre
	 * @return true: pedido enviado com sucesso
	 * @throws Exception : falha ao se conectar com o sistema central
	 */
	public String SendRequestToSistemaCentral(String pedido, String origem, String ambiente) throws Exception {
			
			logger.info("");
			logger.info("---------------------------------------------");
			logger.info("NOVO PEDIDO ENCAMINHADO: " + pedido);
			logger.info("ORIGEM: " + origem);
			logger.info("CONNECTOR: ICOM");
			logger.info("---------------------------------------------");
			
		    logger.debug("1 - Entrou no SendRequestToSistemaCentral");
			
			try {
				
				// iniciando conector
				if (iniciarConector()){
					
					try{
						
						// enviando pedido ao denatran
						return comunicarTransacaoSistemaCentral(pedido);
					
					}catch(Exception e){
						throw e;
					}finally{
						fecharConector();
					}
					
				}else{
					throw new Exception("Falha ao estabelecer comunica��o com o sistema central");
				}
				
			}catch (Exception e){
			    throw e;
			}finally{
				logger.debug("2 - Saiu do SendRequestToSistemaCentral");
			}
			
	}

	public int ReceiveRequestFromSistemaCentral(byte[] areaPedido,
			int tamAreaPedido, byte[] idPedido, byte[] conectorStat) throws Exception {

		return 0;
	}

	public int SendResponseToSistemaCentral(byte[] resposta, int tamResposta,
			byte[] idPedido, byte[] conectorStat) throws Exception {
		
		return 0;
	}
	
	/**
	 * Iniciar o conectorT do ICOM para receber as chamadas seguintes.
	 *
	 * @author rrodrigues / asodre
	 * @return true: conexão aberta
	 * @throws ConnectorException : falha de abertura de conexão
	 */
	private boolean iniciarConector() throws ConnectorException{
		
		logger.info("1 - Carregando conector");
		
		String pathIniICOM = "";
		
		//carregando o connector
		byte[] conectorStatus = new byte[30];
		for (int i=0; i<30; i++)
		  conectorStatus[i] = ' ';
		
		try {
			
			// busca a biblioteca que sera utilizada pelo conector pra se comunicar com o ICOM
			ICLibrary connectorLibrary = LibraryFactory.getLibraryFactory();
			
			// busca o arquivo de configuracao utilizado pelo conector para se comunicar com o ICOM
			pathIniICOM = LibraryFactory.getIniICOMFactory();
			
			// iniciar o conector
			int rc = connectorLibrary.StartConector(conectorStatus, pathIniICOM);
			
			if (rc == 0){
				logger.info("2 - Conector carregado com sucesso");
				return true;
			}else{
				logger.error("2 - Conector carregado com erro: " + rc);
				return false;
			}
			
		}catch (UnsatisfiedLinkError unsatisfiedLinkError){
			
			// falha ao se conectar a biblioteca do conector (DLL ou Shared Library)
			logger.fatal("FALHA AO CARREGAR DLL: " + unsatisfiedLinkError.getMessage());
			throw new ConnectorException(unsatisfiedLinkError);
			
		}catch(Exception e){
			
			// falha fatal
			logger.fatal("FALHA GERAL: " + e.getMessage());
			throw new ConnectorException(e);
		}
		
	}

	/**
	 * Fecha o conectorT do ICOM liberando a memoria alocada para sua execucaoo.
	 *
	 * @author rrodrigues / asodre
	 * @return true: conexao fechada com sucesso
	 * @throws ConnectorException : falha ao fechar conexao
	 */
	private boolean fecharConector(){
		
		logger.info("1 - Fechando conector");
		
		// carregando o connector
		byte[] conectorStatus = new byte[30];
		for (int i=0; i<30; i++)
		  conectorStatus[i] = ' ';
		
		try {
			
			// busca a biblioteca que sera utilizada pelo conector pra se comunicar com o ICOM
			ICLibrary connectorLibrary = LibraryFactory.getLibraryFactory();
			
			// iniciar o conector
			int rc = connectorLibrary.EndConector(conectorStatus);
			
			if (rc == 0){
				logger.info("2 - Conector Fechado com sucesso");
				return true;
			}else{
				logger.warn("2 - Conector Fechado com erro: " + rc);
				return false;
			}
			
		}catch (UnsatisfiedLinkError unsatisfiedLinkError){
			
			// falha ao se conectar a biblioteca do conector (DLL ou Shared Library)
			logger.fatal("FALHA AO CARREGAR DLL: " + unsatisfiedLinkError.getMessage());
			return false;
			
		}catch(Exception e){
			
			// falha fatal
			logger.fatal("FALHA GERAL: " + e.getMessage());
			return false;
		}
		
	}
	
	/**
	 * Envia uma transacao para o sistema central
	 * 
	 * @author rrodrigues / asodre
	 * @param pedido : string contendo a transacao que sera enviada ao sistema central
	 * @return true : transacao realizada com sucesso
	 * @throws Exception
	 */
	private String comunicarTransacaoSistemaCentral(String pedido) throws Exception {
		
		logger.info("1 - Comunicando Transacao");
		
		// carregando o connector
		byte[] conectorStatus = new byte[30];
		for (int i=0; i<30; i++)
		  conectorStatus[i] = ' ';
		
		try {
			
			// busca a biblioteca que será utilizada pelo conector pra se comunicar com o ICOM
			ICLibrary connectorLibrary = LibraryFactory.getLibraryFactory();
			
			int tamResposta = 537;
			byte[] respostaBytes = new byte[tamResposta+1];
			
			// iniciar o conector
			logger.debug("[PARAM pedido]: " + pedido);
			logger.debug("[PARAM tamPedido]: " + pedido.length());
			logger.debug("[PARAM tamResposta]: " + tamResposta);
			logger.debug("[PARAM siglaApp]: " + Token.APP_ID);
			logger.debug("[PARAM conectorStatus]: " + conectorStatus.toString());
			logger.debug("[PARAM timeout]: " + Token.RECVTIMEOUT);
			
			int rc = connectorLibrary.SendRequestToBR(pedido.getBytes("ISO-8859-1"), pedido.length(), respostaBytes, tamResposta, Token.APP_ID, conectorStatus, Token.RECVTIMEOUT);
			
			if (rc > 0){
				
				if (rc > tamResposta){
					String retornoSistemaCentral = new String(respostaBytes, 0, tamResposta, "ISO-8859-1"); 
					logger.info("2 - Comunicação enviada com sucesso: " + retornoSistemaCentral);
					return retornoSistemaCentral;
				}else{
					String retornoSistemaCentral = new String(respostaBytes, 0, rc, "ISO-8859-1");
					logger.info("2 - Comunicação enviada com sucesso: " + retornoSistemaCentral);
					return retornoSistemaCentral;
				}
				
			}else{
				logger.error("2 - Erro ao comunicar transação ao sistema central: " + rc);
				return null;
			}
			
		}catch (UnsatisfiedLinkError unsatisfiedLinkError){
			
			// falha ao se conectar a biblioteca do conector (DLL ou Shared Library)
			logger.fatal("FALHA AO CARREGAR DLL: " + unsatisfiedLinkError.getMessage());
			throw new ConnectorException(unsatisfiedLinkError);
			
		}catch(Exception e){
			
			// falha fatal
			logger.fatal("FALHA GERAL: " + e.getMessage());
			throw new ConnectorException(e);
		}
	}
}
