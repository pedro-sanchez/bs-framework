package br.com.bs.fw.mb;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.fw.vo.ChangePassword;
import br.com.bs.fw.vo.Login;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;

public abstract class MBUserAutentication extends MBUtil{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private IUserBO userBO;

	private Login login = new Login();

	private ChangePassword changePassword = new ChangePassword();
	
	private String email = "";

	private User currentUser = null;
	
	private Boolean firstAcess = Boolean.FALSE;
	
	public void forgot(){
		if (ObjectUtil.isEmpty(email)) {
			addErrorMessage("forgot.txtEmail.required");
			return;
		}
		
		userBO.forgot(email);
		addSuccessMessage("forgot.success");
	}
	
	public void authenticate() throws Exception {
		if (ObjectUtil.hasEmpty(login.getLogin(), login.getSenha())) {
			addErrorMessage("fw.authentic.required");
			return;
		}

		currentUser = userBO.findByLoginAndSenha(login.getLogin(),login.getSenha());
		if (!ObjectUtil.isEmpty(currentUser)) {
			addCookie();
			firstAcess = currentUser.getFirstAccess();
			onLoginSuccess();
		} else {
			login.setSenha(null);
			addErrorMessage("fw.authentic.invalid");
		}
	}
	
	public void endSession(){
		removeCookie();
		this.login = new Login();
		this.currentUser = null;
		onEndSession();
	}

	protected abstract void onLoginSuccess();
	
	protected abstract void onEndSession(); 
	
	
	protected void cookieAuthenticate() throws Exception{
		FacesContext context = FacesContext.getCurrentInstance();
		Cookie cookies[]=((HttpServletRequest)(context.getExternalContext().getRequest())).getCookies();
		
		if(cookies != null && cookies.length>0){
			Login login = new Login();
			for (int i = 0; i < cookies.length; i++) {
				Cookie cookie = cookies[i];

				if(cookie.getName().equals("login")){
					login.setLogin(cookie.getValue());
				}

				if(cookie.getName().equals("senha")){
					login.setSenha(cookie.getValue());
				}

				if(cookie.getName().equals("remember")){
					login.setRememberMe(cookie.getValue().equals("true"));
				}				
			}
			
			if(login.getRememberMe()){
				this.login = login;				
				authenticate();
			}
		}
	}

	private void addCookie() {
		if(login.getRememberMe()){
			FacesContext context = FacesContext.getCurrentInstance();
	
			Cookie loginCookie = new Cookie("login", login.getLogin());
			loginCookie.setMaxAge(1800);
			
			Cookie senhaCookie = new Cookie("senha", login.getSenha());
			senhaCookie.setMaxAge(1800);
			
			Cookie rememberCookie = new Cookie("remember", "true");
			rememberCookie.setMaxAge(1800);
	
			((HttpServletResponse) (context.getExternalContext().getResponse())).addCookie(loginCookie);
			((HttpServletResponse) (context.getExternalContext().getResponse())).addCookie(senhaCookie);
			((HttpServletResponse) (context.getExternalContext().getResponse())).addCookie(rememberCookie);
		}
		else{
			removeCookie();
		}
	}

	private void removeCookie() {
		FacesContext context = FacesContext.getCurrentInstance();

		Cookie loginCookie = new Cookie("login", "");
		loginCookie.setMaxAge(1);
		
		Cookie senhaCookie = new Cookie("senha", "");
		senhaCookie.setMaxAge(1);
		
		Cookie rememberCookie = new Cookie("remember", "false");
		rememberCookie.setMaxAge(1);

		((HttpServletResponse) (context.getExternalContext().getResponse())).addCookie(loginCookie);
		((HttpServletResponse) (context.getExternalContext().getResponse())).addCookie(senhaCookie);
		((HttpServletResponse) (context.getExternalContext().getResponse())).addCookie(rememberCookie);
	}
	
	public void startChangePassword(){
		changePassword = new ChangePassword();
	}
	
	public void doChangePassword() throws NoSuchAlgorithmException, UnsupportedEncodingException{
		String validationKeys = changePassword.validate();
		if(!validationKeys.isEmpty()){
			addErrorMessage(validationKeys);
			return ;
		}

		String currentPassword = ObjectUtil.toMD5(changePassword.getCurrentPassword());
		
		if (!ObjectUtil.isEqual(currentPassword, currentUser.getSenha())) {
			addErrorMessage("fw.passwordChange.currentPassword.error");
			return ;
		}
		
		String newPassword = ObjectUtil.toMD5(changePassword.getNewPassword());
		currentUser.setSenha(newPassword);
		
		if(currentUser.getFirstAccess()){
			currentUser.setFirstAccess(Boolean.FALSE);
		}
		
		currentUser = userBO.save(currentUser);
		
		changePassword = new ChangePassword();
		
		addSuccessMessage("fw.passwordChange.success");
	}
	
	public User getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	public Login getLogin() {
		return login;
	}

	public void setLogin(Login login) {
		this.login = login;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ChangePassword getChangePassword() {
		return changePassword;
	}

	public void setChangePassword(ChangePassword changePassword) {
		this.changePassword = changePassword;
	}

	public Boolean getFirstAcess() {
		return firstAcess;
	}

	public void setFirstAcess(Boolean firstAcess) {
		this.firstAcess = firstAcess;
	}
	
	
}