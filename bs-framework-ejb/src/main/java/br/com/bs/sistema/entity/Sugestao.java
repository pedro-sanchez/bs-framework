package br.com.bs.sistema.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import br.com.bs.fw.entity.iface.IEntity;

public class Sugestao implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
    @ManyToOne(optional=false) 
	@JoinColumn(name = "INFORMANTE_ID", referencedColumnName = "ID")  
	private Informante informante;

	@NotNull
	@Column(name="DESCRICAO")
	private String descricao;
	
	@NotNull
	@Column(name="CIDADE_SUGESTAO_ID")
	private Long cidadeInformante;

	@NotNull
	@Column(name="AREA_SUGESTAO")
	private Long areaSugestao;

	public Sugestao() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Informante getInformante() {
		return informante;
	}

	public void setInformante(Informante informante) {
		this.informante = informante;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getCidadeInformante() {
		return cidadeInformante;
	}

	public void setCidadeInformante(Long cidadeInformante) {
		this.cidadeInformante = cidadeInformante;
	}

	public Long getAreaSugestao() {
		return areaSugestao;
	}

	public void setAreaSugestao(Long areaSugestao) {
		this.areaSugestao = areaSugestao;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Sugestao other = (Sugestao) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Sugestao [id=" + id + ", informante=" + informante
				+ ", cidadeInformante=" + cidadeInformante + ", areaSugestao="
				+ areaSugestao + "]";
	}
	
}
