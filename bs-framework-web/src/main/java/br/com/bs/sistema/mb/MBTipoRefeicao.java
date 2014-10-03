package br.com.bs.sistema.mb;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.bs.fw.mb.MBGeneric;
import br.com.bs.sistema.business.iface.ITipoRefeicaoBO;
import br.com.bs.sistema.entity.TipoRefeicao;
import br.com.bs.sistema.wrapper.TipoRefeicaoWrapper;

@Named("mbTipoRefeicao")
@ViewScoped
public class MBTipoRefeicao extends MBGeneric<TipoRefeicao, ITipoRefeicaoBO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private ITipoRefeicaoBO tipoRefeicaoBO;

	public void init() {
		this.setWrapper(new TipoRefeicaoWrapper());
		setBo(tipoRefeicaoBO);
	}
}