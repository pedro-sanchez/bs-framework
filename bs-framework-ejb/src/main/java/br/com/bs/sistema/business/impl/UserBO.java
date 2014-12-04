package br.com.bs.sistema.business.impl;

import javax.ejb.Local;
import javax.ejb.Stateless;

import br.com.bs.fw.business.impl.GenericBO;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;
import br.com.bs.sistema.repository.UserDAO;

@Local
@Stateless
public class UserBO extends GenericBO<User, UserDAO> implements IUserBO {

	
	@Override
	public User findByLoginAndSenha(String login, String senha){
		return getDao().findByLoginAndSenha(login, senha);
	}
	

	@Override
	public User findByEmail(String email){
		return getDao().findByEmail(email);
	}
	
}
