package br.com.bs.fw.vo;

import java.io.Serializable;

import br.com.bs.fw.util.ObjectUtil;


public class ChangePassword implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String currentPassword;
	
	private String newPassword;
	
	private String confirmPassword;

	public String validate(){
		if(ObjectUtil.hasEmpty(currentPassword, newPassword, confirmPassword)){
			return "fw.generic.required";
		}
		
		if(!ObjectUtil.isEqual(newPassword, confirmPassword)){
			return "fw.passwordChange.notEquals";
		}
		
		if(ObjectUtil.isEqual(currentPassword,newPassword)){
			return "fw.passwordChange.Equals";
		}
		
		if (newPassword.length() < 6) {
			return "fw.passwordChange.minLength";
		}
		
		return "";
	}
	
	public String getCurrentPassword() {
		return currentPassword;
	}

	public void setCurrentPassword(String currentPassword) {
		this.currentPassword = currentPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
}
