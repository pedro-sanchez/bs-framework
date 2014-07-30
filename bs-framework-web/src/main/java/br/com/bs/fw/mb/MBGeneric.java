package br.com.bs.fw.mb;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.bs.fw.business.iface.IGenericBO;
import br.com.bs.fw.entity.iface.IEntity;
import br.com.bs.fw.enumeration.WindowModeEnum;
import br.com.bs.fw.util.GenericWrapper;
import br.com.bs.fw.util.PaginationResult;
import br.com.bs.fw.util.ReflectionUtil;

public abstract class MBGeneric<T extends IEntity, S extends IGenericBO<T>> implements Serializable {
	private static final long serialVersionUID = 1L;

	private S bo;

	private PaginationResult<T> paginationResult;

	private Long selectedID =-1L;
	
	private T entity;
	
	private WindowModeEnum mode;
	
	private GenericWrapper<T> wrapper;
	
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
	
	private void saveBO(){
		bo.save(entity);

		if(WindowModeEnum.NEW.equals(this.mode)){
			addInfoMessage("Registro criado com sucesso...");
		}
		else if(WindowModeEnum.EDIT.equals(this.mode)){
			addInfoMessage("Registro alterado com sucesso...");
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
		System.out.println("apagando...");
		bo.delete(selectedID);
		addInfoMessage("Registro removido com sucesso...");
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
		
	public GenericWrapper<T> getWrapper() {
		return wrapper;
	}

	public void setWrapper(GenericWrapper<T> wrapper) {
		this.wrapper = wrapper;
	}

	/**Messages*/

	protected void addErrorMessage(String componentId, String errorMessage){
		addMessage(componentId, errorMessage, FacesMessage.SEVERITY_ERROR);
	}

	protected void addErrorMessage(String errorMessage){
		addErrorMessage(null, errorMessage);
	}

	protected void addInfoMessage(String componentId, String infoMessage){
		addMessage(componentId, infoMessage, FacesMessage.SEVERITY_INFO);
	}

	protected void addInfoMessage(String infoMessage){
		addInfoMessage(null, infoMessage);
	}
	
	

	
	private void addMessage(String componentId, String errorMessage, Severity severity){
		
		FacesContext context = FacesContext.getCurrentInstance();  
        

		FacesMessage message =  new FacesMessage(errorMessage);
		message.setSeverity(severity);
		
        context.addMessage(componentId, message);  
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("globalMessage");
		
		
	}
	

}