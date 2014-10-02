package br.com.bs.sistema.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import br.com.bs.fw.entity.iface.IEntity;

@Entity
@Table(name = "tipo_refeicao", schema = "public")
public class TipoRefeicao implements IEntity, Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator(name = "seq_tipo_refeicao", sequenceName = "public.seq_tipo_refeicao", allocationSize = 1, initialValue = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_tipo_refeicao")
	private Long id;

	@NotNull
	@Column(name = "descricao", length = 40)
	private String descricao;

	public TipoRefeicao() {

	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
