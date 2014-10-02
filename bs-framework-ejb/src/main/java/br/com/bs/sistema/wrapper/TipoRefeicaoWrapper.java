package br.com.bs.sistema.wrapper;

import br.com.bs.fw.enumeration.FilterType;
import br.com.bs.fw.util.GenericWrapper;
import br.com.bs.sistema.entity.TipoRefeicao;

public class TipoRefeicaoWrapper extends GenericWrapper<TipoRefeicao> {

	public TipoRefeicaoWrapper () {
		addSelectFilter("id", "Código", FilterType.Long);
	}
}