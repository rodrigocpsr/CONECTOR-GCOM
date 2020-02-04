package br.com.autenticis.conector.enumerator;

public enum EOrigem {

	BATCH("B"),
	WEB("W"),
	WEBSERVICE("S");

	private String codigo;
	
	private EOrigem(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
