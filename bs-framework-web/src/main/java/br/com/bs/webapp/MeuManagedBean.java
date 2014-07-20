package br.com.bs.webapp;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.view.ViewScoped;

@ManagedBean
@ViewScoped
public class MeuManagedBean implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nome;

    public String getNome() {
        return "Olá "+nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}
