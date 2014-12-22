package br.com.bs.fw.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

import br.com.bs.fw.util.JSFUtils;

public abstract class MBUtil implements Serializable {
	private static final long serialVersionUID = 1L;

	/**Messages*/

	protected void addErrorMessage(String componentId, String messageErrorKey){
		addMessage(componentId, messageErrorKey, FacesMessage.SEVERITY_ERROR);
	}

	protected void addErrorMessage(String messageErrorKey){
		addErrorMessage(null, messageErrorKey);
	}

	protected void addSuccessMessage(String componentId, String messageSuccessKey){
		addMessage(componentId, messageSuccessKey, FacesMessage.SEVERITY_INFO);
	}

	protected void addSuccessMessage(String messageSuccessKey){
		addSuccessMessage(null, messageSuccessKey);
	}
	
	protected void addInfoMessage(String componentId, String messageInfoKey){
		addMessage(componentId, messageInfoKey, FacesMessage.SEVERITY_WARN);
	}

	protected void addInfoMessage(String messageInfoKey){
		addSuccessMessage(null, messageInfoKey);
	}
	
	private void addMessage(String componentId, String key, Severity severity){
		FacesContext context = FacesContext.getCurrentInstance();  

		FacesMessage facesMessage =  new FacesMessage(JSFUtils.getMessage(key));
		facesMessage.setSeverity(severity);
		
        context.addMessage(componentId, facesMessage);  
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("globalMessage");
	}

}