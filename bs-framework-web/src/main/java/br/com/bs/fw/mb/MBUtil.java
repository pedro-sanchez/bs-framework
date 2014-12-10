package br.com.bs.fw.mb;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.application.FacesMessage.Severity;
import javax.faces.context.FacesContext;

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
	
	private void addMessage(String componentId, String message, Severity severity){
		FacesContext context = FacesContext.getCurrentInstance();  

		FacesMessage facesMessage =  new FacesMessage(message);
		facesMessage.setSeverity(severity);
		
        context.addMessage(componentId, facesMessage);  
        
        FacesContext.getCurrentInstance().getPartialViewContext().getRenderIds().add("globalMessage");
	}

}