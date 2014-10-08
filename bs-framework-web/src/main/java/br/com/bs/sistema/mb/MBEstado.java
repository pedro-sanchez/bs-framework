package br.com.bs.sistema.mb;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import br.com.bs.fw.mb.MBGeneric;
import br.com.bs.sistema.business.iface.IEstadoBO;
import br.com.bs.sistema.entity.Estado;
import br.com.bs.sistema.search.EstadoSearch;

@ManagedBean(name="mbEstado")
@ViewScoped
public class MBEstado extends MBGeneric<Estado, IEstadoBO> implements Serializable{

	private static final long serialVersionUID = -2433515164743414832L;
	
	@EJB
	private IEstadoBO estadoBO;
	
	public void init() {
		System.out.println("init extendido jrebel2");
		this.setWrapper(new EstadoSearch());
		setBo(estadoBO);
	}

	
	
}