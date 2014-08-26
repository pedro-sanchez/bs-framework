package br.com.bs.sistema.repository.impl;

import javax.persistence.Query;

import br.com.bs.fw.repository.impl.GenericDAO;
import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.sistema.entity.User;

public class UserDAO extends GenericDAO<User> {

	public User findByLoginAndSenha(String login, String senha) {

		if (ObjectUtil.hasEmpty(login, senha)) {
			return null;
		}

		StringBuilder hql = new StringBuilder();

		hql.append("Select u from User u ");
		hql.append(" where u.login = :login ");
		hql.append(" and u.senha = :senha ");
		hql.append(" and u.ativo = :ativo ");

		Query query = getEm().createQuery(hql.toString());

		query.setParameter("login", login);
		query.setParameter("senha", senha);
		query.setParameter("ativo", Boolean.TRUE);


		User user = (User) query.getSingleResult();

		return user;

	}
	public User findByEmail(String email) {

		if (ObjectUtil.isEmpty(email)) {
			return null;
		}

		StringBuilder hql = new StringBuilder();

		hql.append("Select u from User u ");
		hql.append(" where u.email = :email ");
		hql.append(" and u.ativo = :ativo ");

		Query query = getEm().createQuery(hql.toString());

		query.setParameter("email", email);
		query.setParameter("ativo", Boolean.TRUE);

		User user = (User) query.getSingleResult();

		return user;

	}
}
