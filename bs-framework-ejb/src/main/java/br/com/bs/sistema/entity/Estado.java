package br.com.bs.sistema.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import br.com.bs.fw.annotation.Name;
import br.com.bs.fw.entity.iface.IEntity;

@Entity
@Table(name = "estado", schema = "public")
public class Estado implements IEntity, Serializable{
	private static final long serialVersionUID = 1L;

	@Id
	@SequenceGenerator (name = "seq_estado", sequenceName = "public.seq_estado", allocationSize = 1, initialValue = 1) 
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="seq_estado")
	private Long id;
	
	@Name
	@NotNull
	@Column(name="NOME", length = 40)
	private String nome;

	@Transient
	private Date date;
	
	@Transient
	private Estado estado;
	
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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		System.out.println("input date");
		System.out.println(date);
		this.date = date;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
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
		Estado other = (Estado) obj;
		if (getId() == null) {
			if (other.getId() != null)
				return false;
		} else if (!getId().equals(other.getId()))
			return false;
		return true;
	}


	
	
	
}
