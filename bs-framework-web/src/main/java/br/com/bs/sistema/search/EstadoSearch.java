package br.com.bs.sistema.search;

import br.com.bs.fw.enumeration.FilterType;
import br.com.bs.fw.util.GenericSearch;
import br.com.bs.fw.util.IGenericSearch;
import br.com.bs.sistema.entity.Estado;

public class EstadoSearch extends GenericSearch<Estado> implements IGenericSearch<Estado> {
	
	public EstadoSearch(){
		addSelectFilter("id", "Código", FilterType.Long);
		addSelectFilter("nome", "Nome", FilterType.String);
	}
}
