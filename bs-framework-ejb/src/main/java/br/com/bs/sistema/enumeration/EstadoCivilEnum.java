package br.com.bs.sistema.enumeration;

public enum EstadoCivilEnum {

	SOLTEIRO("Fundamental Incompleto", 0), 
	CASADO("Fundamental", 1), 
	AMASIADO_IMPOMPLETO("Médio Incompleto", 2), 
	VIUVO("Médio", 3);

	public int id;
	public String descricao;

	EstadoCivilEnum(String descricao, int id) {
		this.descricao = descricao;
		this.id = id;
	}
		
}
