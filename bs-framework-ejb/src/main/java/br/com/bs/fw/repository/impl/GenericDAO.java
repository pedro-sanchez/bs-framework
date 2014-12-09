package br.com.bs.fw.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.fw.repository.iface.IGenericDAO;
import br.com.bs.fw.util.IGenericSearch;
import br.com.bs.fw.util.PaginationResult;
import br.com.bs.fw.util.ReflectionUtil;
import br.com.bs.fw.util.SearchUtil;

public abstract class GenericDAO<T extends IEntity> implements IGenericDAO<T> {

	private EntityManager em;

	public EntityManager getEm() {
		return em;
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T findBy(Long id) {
		return (T) em.find(ReflectionUtil.getTClass(getClass()), id);
	}
	

	@Override
	@SuppressWarnings("unchecked")
	public PaginationResult<T> findBy(PaginationResult<T> paginationResult) {
		
		IGenericSearch<T> wrapper = paginationResult.getWrapper();
		
		Query query = em.createQuery(wrapper.getSearch(Boolean.FALSE).toString());

		query.setFirstResult(paginationResult.getCurrentIndex() * paginationResult.getRowPerPage());
		query.setMaxResults(paginationResult.getRowPerPage());
		
		List<T> lista = query.getResultList();

		paginationResult.setResult(lista);
		
		query = em.createQuery(wrapper.getSearch(Boolean.TRUE).toString());
		
		paginationResult.setSize((Long)query.getSingleResult());
		
		
		return paginationResult;
	}

	private StringBuilder buildHQL(Boolean count){
		StringBuilder hql = new StringBuilder();

		if(count){
			hql.append("select count(e.id) from ");
		}
		else{
			hql.append("select e from ");			
		}
		hql.append(ReflectionUtil.getTClass(getClass()).getSimpleName());
		hql.append(" e ");
		if(!count){
			hql.append(" order by e.id ");
		}
		
		return hql;
	}

	@Override
	public List<T> findAll() {
		StringBuilder hql = buildHQL(Boolean.FALSE);
		return executeQueryList(hql);		
	}

	@Override
	public List<T> findAllReduce() {
		StringBuilder hql = SearchUtil.queryFindByReduce(ReflectionUtil.getTClass(getClass()));
		return executeQueryList(hql);
	}

	@SuppressWarnings("unchecked")
	private List<T> executeQueryList(StringBuilder hql) {
		Query query = em.createQuery(hql.toString());
		return query.getResultList();
	}

	@Deprecated
	@Override
	public List<T> searchBy(Object... parameters) {
		// TODO Auto-generated method stub
		return null;
	}

	@Deprecated
	@Override
	public List<T> searchReduceBy(Object... parameters) {
		// TODO Auto-generated method stub
		return null;
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
			em.persist(entity);
			afterInsert(entity);
		}
		else{
			beforeUpdate(entity);
			entity = em.merge(entity);
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
		em.remove(this.findBy(entityId));
		afterDelete(entityId);
	}

	@Override
	public void afterDelete(Long entityId) {}

}
