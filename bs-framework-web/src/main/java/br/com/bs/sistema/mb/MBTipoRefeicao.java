package br.com.bs.sistema.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bs.fw.mb.MBGeneric;
import br.com.bs.sistema.business.iface.ITipoRefeicaoBO;
import br.com.bs.sistema.entity.TipoRefeicao;
import br.com.bs.sistema.wrapper.TipoRefeicaoWrapper;

@ManagedBean(name="mbTipoRefeicao")
@ViewScoped
public class MBTipoRefeicao extends MBGeneric<TipoRefeicao, ITipoRefeicaoBO> implements Serializable {

	private static final long serialVersionUID = 1L;

	@EJB
	private ITipoRefeicaoBO tipoRefeicaoBO;

	public void init() {
		this.setWrapper(new TipoRefeicaoWrapper());
		setBo(tipoRefeicaoBO);
	}
}