package br.com.bs.sistema.mb;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

import br.com.bs.sistema.entity.Informante;


@ManagedBean(name = "mbInformante")
@ViewScoped
public class MBInformante implements Serializable {
	private static final long serialVersionUID = 1L;

	private Informante entity = new Informante();

	public Informante getEntity() {
		return entity;
	}

	public void setEntity(Informante entity) {
		this.entity = entity;
	}
	
	
}