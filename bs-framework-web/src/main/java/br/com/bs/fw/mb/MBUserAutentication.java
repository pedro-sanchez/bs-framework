package br.com.bs.fw.mb;

import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.fw.vo.Login;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;

public abstract class MBUserAutentication extends MBUtil{

	private static final long serialVersionUID = 1L;
	
	@EJB
	private IUserBO userBO;

	private Login login = new Login();
	
	private String email = "";
	
	private User currentUser = null;
	
	public void forgot(){
		if (ObjectUtil.isEmpty(email)) {
			addErrorMessage("Informe o email do usuário!");
			return;
		}
		
		userBO.forgot(email);
		addInfoMessage("Email de recuperação enviado com sucesso!");
	}
	
	public void authenticate() throws Exception {
		if (ObjectUtil.hasEmpty(login.getLogin(), login.getSenha())) {
			addErrorMessage("Informe o Login e Senha!!!");
			return;
		}

		currentUser = userBO.findByLoginAndSenha(login.getLogin(),login.getSenha());
		if (!ObjectUtil.isEmpty(currentUser)) {
			addCookie();
			onLoginSuccess();
		} else {
			login.setSenha(null);
			addErrorMessage("Login ou Senha  não conferem!\nVerefique os dados informados");
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
	
}
