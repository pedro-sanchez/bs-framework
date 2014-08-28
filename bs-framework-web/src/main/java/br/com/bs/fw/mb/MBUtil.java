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

public abstract class MBUtil implements Serializable {
	private static final long serialVersionUID = 1L;

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