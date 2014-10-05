package br.com.bs.sistema.wrapper;

import br.com.bs.fw.enumeration.FilterType;
import br.com.bs.fw.util.GenericWrapper;
import br.com.bs.fw.util.IGenericWrapper;
import br.com.bs.sistema.entity.Estado;

public class EstadoWrapper extends GenericWrapper<Estado> implements IGenericWrapper<Estado> {
	
	public EstadoWrapper(){
		addSelectFilter("id", "Código", FilterType.Long);
		addSelectFilter("nome", "Nome", FilterType.String);
	}
}
