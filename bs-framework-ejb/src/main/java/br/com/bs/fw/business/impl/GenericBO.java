package br.com.bs.fw.business.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.bs.fw.business.iface.IGenericBO;
import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.fw.repository.iface.IGenericDAO;
import br.com.bs.fw.util.DaoFactory;
import br.com.bs.fw.util.PaginationResult;

public class GenericBO<T extends IEntity> implements IGenericBO<T>{
	
	@PersistenceContext(unitName = "BASIC-PU")
	private EntityManager em;
	
	private Class<? extends IGenericDAO<T>> clazzDAO;

	public GenericBO(Class<? extends IGenericDAO<T>> clazzDAO) {
		this.clazzDAO = clazzDAO;
	}
	
	@SuppressWarnings("unchecked")
	protected IGenericDAO<T> getDao(){		
		return (IGenericDAO<T>) DaoFactory.getDaoInstance(clazzDAO, em);
	}

	@Override
	public T findBy(Long id) {		
		return getDao().findBy(id);
	}
	

	public PaginationResult<T> findBy(PaginationResult<T> paginationResult){
		return getDao().findBy(paginationResult);
	}

	@Override
	public List<T> findReduceBy(Long id) {
		return getDao().findReduceBy(id);
	}

	@Override
	public List<T> findAll() {		
		return getDao().findAll();
	}

	@Override
	public List<T> findReduceAll() {
		return getDao().findReduceAll();
	}

	@Override
	public List<T> searchBy(Object... parameters) {
		return getDao().searchBy(parameters);
	}

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
