package br.com.bs.sistema.business.iface;

import br.com.bs.fw.business.iface.IGenericBO;
import br.com.bs.sistema.entity.User;

public interface IUserBO extends IGenericBO<User> {	
	User findByLoginAndSenha(String login, String senha);

	User findByEmail(String email);

	void forgot(String email);
}
