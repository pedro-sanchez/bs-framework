package br.com.bs.sistema.enumeration;

public enum EscolaridadeEnum {

	FUNDAMENTAL_IMCOMPLETO("Fundamental Incompleto", 0), 
	FUNDAMENTAL("Fundamental", 1), 
	MEDIO_IMPOMPLETO("Médio Incompleto", 2), 
	MEDIO("Médio", 3), 
	SUPERIOR_IMCOMPLETO("Superior Incompleto", 4), 
	SUPERIOR("Superior", 5), 
	POS_GRADUACAO("Pós-Graduação", 6);

	public int id;
	public String descricao;

	EscolaridadeEnum(String descricao, int id) {
		this.descricao = descricao;
		this.id = id;
	}

}
