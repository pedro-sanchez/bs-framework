package br.com.bs.fw.repository.iface;

import java.util.List;

import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.fw.util.PaginationResult;


public interface IGenericDAO<T extends IEntity> {

	T findBy(Long id);
	
	PaginationResult<T> findBy(PaginationResult<T> paginationResult);
	
	List<T> findAll();
	
	List<T> findAllReduce();	

	@Deprecated
	List<T> searchBy(Object... parameters);

	@Deprecated
	List<T> searchReduceBy(Object... parameters);

	void beforeInsert(T entity);
	
	void beforeUpdate(T entity);
	
	T save(T entity);

	void afterInsert(T entity);
	
	void afterUpdate(T entity);
		
	void beforeDelete(Long entityId);
	
	void delete(Long entityId);

	void afterDelete(Long entityId);
		
}
