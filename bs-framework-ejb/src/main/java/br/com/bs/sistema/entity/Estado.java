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
@Table(name = "estado", schema = "public")
public class Estado implements IEntity, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator (name = "seq_estado", sequenceName = "public.seq_estado", allocationSize = 1, initialValue = 1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estado")
	private Long id;
	
	@NotNull
	@Column(name="NOME", length = 40)
	private String nome;
	
	
	public Estado(){
		
	}
	
	public Estado(Long id, String nome){
		this.id = id;
		this.nome = nome;
	}
	
	@Override
	public Long getId() {
		return id;
	}
	
	@Override
	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	
}
