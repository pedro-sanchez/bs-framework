package br.com.bs.fw.business.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bs.fw.business.iface.IGenericBO;
import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.fw.repository.iface.IGenericDAO;
import br.com.bs.fw.util.DaoFactory;
import br.com.bs.fw.util.PaginationResult;
import br.com.bs.fw.util.ReflectionUtil;

public class GenericBO<T extends IEntity, DAO extends IGenericDAO<T>> implements IGenericBO<T>{
	
	@PersistenceContext(unitName = "BASIC-PU")
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	protected DAO getDao(){		
		System.out.println("obtendo dao");
		return (DAO) DaoFactory.getDaoInstance((Class<? extends IGenericDAO<?>>) ReflectionUtil.getGenericClass(getClass(), 1), em);		
	}

	@Override
	public T findBy(Long id) {		
		return getDao().findBy(id);
	}
	

	public PaginationResult<T> findBy(PaginationResult<T> paginationResult){
		return getDao().findBy(paginationResult);
	}

	@Override
	public List<T> findAll() {		
		return getDao().findAll();
	}

	@Override
	public List<T> findAllReduce() {
		return getDao().findAllReduce();
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<T> searchBy(Object... parameters) {
		return getDao().searchBy(parameters);
	}

	@SuppressWarnings("deprecation")
	@Override
	public List<T> searchReduceBy(Object... parameters) {
		return getDao().searchReduceBy(parameters);
	}

	@Override
	public void beforeInsert(T entity) {}
	@Override
	public void beforeUpdate(T entity) {}

	@Override
	public T save(T entity) {
		Boolean isInsert = (entity.getId() == null || entity.getId()<=0L);
		if(isInsert){
			beforeInsert(entity);
		}
		else{
			beforeUpdate(entity);
		}
		
		entity = getDao().save(entity);
		
		if(isInsert){
			afterInsert(entity);
		}
		else{
			afterUpdate(entity);
		}
		return entity;
	}

	@Override
	public void afterInsert(T entity) {}

	@Override
	public void afterUpdate(T entity) {}

	@Override
	public void beforeDelete(Long entityId) {}

	@Override
	public void delete(Long entityId) {
		beforeDelete(entityId);
		
		getDao().delete(entityId);
		
		afterDelete(entityId);
	}

	@Override
	public void afterDelete(Long entityId) {}
	
	public void setEm(EntityManager em) {
		this.em = em;
	}
}
