package br.com.bs.sistema.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import javax.validation.constraints.Pattern;

import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.sistema.enumeration.EscolaridadeEnum;
import br.com.bs.sistema.enumeration.EstadoCivilEnum;


public class Informante implements Serializable, IEntity {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name="ID")
	private Long id;
	
	@NotNull
	@Column(name="NOME", length=60)
	private String nome;
	
	@Column(name="RG", length = 20)
	private String rg;
	
	@NotNull
	@Temporal(TemporalType.DATE)
	@Past
	@Column(name="DATA_NASCIMENTO")
	private Date dataNascimento;
	
	@Column(name="ESTADO_CIVIL")
	private EstadoCivilEnum estadoCivil;
	
	@Column(name="ESCOLARIDADE")
	private EscolaridadeEnum escolaridade;

	@Column(name="RUA", length=200)
	private String rua;
	
	@Column(name="NUMERO", length=10)
	private String numero;
	
	@Column(name="BAIRRO", length=100)
	private String bairro;
	
	@Column(name="CEP", length=15)
	private String cbs;
	
	@NotNull
	@Column(name="CIDADE", length=100)
	private String cidade;
	
	@NotNull
	@Column(name="ESTADO", length=2)
	private String estado;
	
	@Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\."
	        +"[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@"
	        +"(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?",
	             message="E-mail inválido")
	@NotNull
	@Column(name="EMAIL", length=100)
	private String email;

	public Informante() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public EstadoCivilEnum getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(EstadoCivilEnum estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public EscolaridadeEnum getEscolaridade() {
		return escolaridade;
	}

	public void setEscolaridade(EscolaridadeEnum escolaridade) {
		this.escolaridade = escolaridade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCbs() {
		return cbs;
	}

	public void setCbs(String cbs) {
		this.cbs = cbs;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nome == null) ? 0 : nome.hashCode());
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
		Informante other = (Informante) obj;
		if (nome == null) {
			if (other.nome != null)
				return false;
		} else if (!nome.equals(other.nome))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Informante [id=" + id + ", nome=" + nome + "]";
	}	
	
}
