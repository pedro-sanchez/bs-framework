package br.com.bs.fw.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;

import br.com.bs.fw.business.iface.IGenericBO;
import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.fw.enumeration.WindowModeEnum;
import br.com.bs.fw.util.IGenericSearch;
import br.com.bs.fw.util.PaginationResult;
import br.com.bs.fw.util.ReflectionUtil;

public abstract class MBGeneric<T extends IEntity, S extends IGenericBO<T>> extends MBUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	private S bo;

	private PaginationResult<T> paginationResult;

	private Long selectedID =-1L;
	
	private T entity;
	
	private WindowModeEnum mode;
	
	private IGenericSearch<T> wrapper;
	
	@SuppressWarnings("unchecked")
	private T newEntity() {
		return ((T) ReflectionUtil.getTNewInstance(this.getClass()));
	}
	
    public abstract void init();
    
    @PostConstruct
    public void initGeneric() {
            init();    
            paginationResult = new PaginationResult<>();
            list();
    }
		
	public void create() {
		System.out.println("criando...");
		this.entity = newEntity();
		mode = WindowModeEnum.NEW;
	}
	
	public void edit() {		
		System.out.println("editando...");
		this.entity = findBy(selectedID);
		mode = WindowModeEnum.EDIT;
	}
	
	public void view() {
		System.out.println("visualizando...");
		mode = WindowModeEnum.VIEW;
	}
	
	public void list() {
		System.out.println("listando...");
		this.entity = null;
		this.setSelectedID(-1L);
		mode = WindowModeEnum.LIST;
		
		findAll();
	}
	
	public void cancel(){
		System.out.println("cancelando...");
		this.entity = null;
		mode = WindowModeEnum.LIST;
	}
	
	/**
	 * 
	 * EJB CALL
	 * 
	 * */
	

	protected void findAll(){
		paginationResult.setWrapper(wrapper);
		paginationResult = bo.findBy(paginationResult);
	}
	
	protected T findBy(Long id){
		return bo.findBy(id);
	}
	
	protected void saveBO(){
		bo.save(entity);

		if(WindowModeEnum.NEW.equals(this.mode)){
			addSuccessMessage("fw.insert.success");
		}
		else if(WindowModeEnum.EDIT.equals(this.mode)){
			addSuccessMessage("fw.update.success");
		}
	}
	
	public void save(){
		saveBO();
		list();
	}
	
	public void savePlus(){
		saveBO();
		create();
	}

	public void delete(){
		bo.delete(selectedID);
		addSuccessMessage("fw.delete.success");
		list();
	}
	

	/**
	 * 
	 * GETTERS N SETTERS
	 * 
	 * */


	public PaginationResult<T> getPaginationResult() {
		return paginationResult;
	}

	public void setPaginationResult(PaginationResult<T> paginationResult) {
		this.paginationResult = paginationResult;
	}
	
	public T getEntity() {
		return entity;
	}


	public void setEntity(T entity) {
		this.entity = entity;
	}

	public S getBo() {
		return bo;
	}

	public void setBo(S bo) {
		this.bo = bo;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public WindowModeEnum getMode() {
		return mode;
	}

	public Long getSelectedID() {
		return selectedID;
	}

	public void setSelectedID(Long selectedID) {
		this.selectedID = selectedID;
	}
		
	public IGenericSearch<T> getWrapper() {
		return wrapper;
	}

	public void setWrapper(IGenericSearch<T> wrapper) {
		this.wrapper = wrapper;
	}

}