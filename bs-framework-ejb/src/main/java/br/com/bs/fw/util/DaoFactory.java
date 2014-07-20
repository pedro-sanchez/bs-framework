package br.com.bs.fw.util;

import javax.persistence.EntityManager;

import br.com.bs.fw.repository.iface.IGenericDAO;
import br.com.bs.fw.repository.impl.GenericDAO;

public class DaoFactory {


	public static IGenericDAO<?> getDaoInstance(Class<? extends IGenericDAO<?>> clazzDAO, EntityManager entityManager){
		Object dao = null;
		try {
			dao = clazzDAO.newInstance();			
			((GenericDAO<?>)dao).setEm(entityManager);
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return (IGenericDAO<?>) dao;
	}
}
