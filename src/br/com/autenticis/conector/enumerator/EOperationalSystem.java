package br.com.autenticis.conector.enumerator;

public enum EOperationalSystem {

	LINUX("L"),
	WINDOWS("W");

	private String codigo;
	
	private EOperationalSystem(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
	
}
