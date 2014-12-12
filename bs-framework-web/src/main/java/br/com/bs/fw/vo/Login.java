package br.com.bs.fw.vo;

import br.com.bs.fw.util.ObjectUtil;

public class Login {
	private String login;

	private String senha;
	
	private Boolean rememberMe = Boolean.FALSE;

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		try {
			this.senha = ObjectUtil.toMD5(senha);
		} catch (Exception e) {
			this.senha = null;
		}
	}

	public Boolean getRememberMe() {
		return rememberMe;
	}

	public void setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
	}	
}
