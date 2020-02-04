package br.com.autenticis.conector.test;

import br.com.autenticis.conector.communicator.ConnectorICOM;

/**
 * Classe para realizar teste de um pedido para o sistema central do SERPRO
 * 
 * @author rrodrigues
 *
 */
public class Teste {

	private static final String PEDIDO = "210151233584010000000FNFNBR1049000043JIB65180016371752455115802600   MERSON DE SOUSA M&NDONCA                20092010MG009145805526201110240108657810095104227604630   JOAO FABIANO PEREIRA MENEZES            QUADRA 2 MR 6                    12                    SETOR OESTE         9595GO73750036540720140212220206140000128UNAI CARTORIO 2 OFICIO E NOTAS          RUA RUA AVENIDA JOSE LUIZ ADJU  240                    CENTRO              5407MG3861000038036761380310432101511100000000000000";
	
	private static final String AMBIENTE = "TESTE_AMBIENTE";
	
	public static void main(String[] args) {
		ConnectorICOM connector = new ConnectorICOM();
		try {
			connector.SendRequestToSistemaCentral(PEDIDO, "T", AMBIENTE);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
