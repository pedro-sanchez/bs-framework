package br.com.bs.fw.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.bs.fw.util.Menu;
import br.com.bs.fw.util.ObjectUtil;
import br.com.bs.fw.vo.Login;
import br.com.bs.sistema.business.iface.IUserBO;
import br.com.bs.sistema.entity.User;

@ManagedBean(name = "mbSession")
@SessionScoped
public class MBSession extends MBUtil implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8771305167968602628L;

	private List<Menu> menuItem = new ArrayList<>();

	private String currentStage = "login.xhtml";

	private String currentPage = "paginas/home.xhtml";

	@EJB
	private IUserBO userBO;

	private Login login = new Login();

	private User currentUser = null;

	public void authenticate() throws Exception {
		if (ObjectUtil.hasEmpty(login.getLogin(), login.getSenha())) {
			addErrorMessage("Informe o Login e Senha!!!");
			return;
		}

		currentUser = userBO.findByLoginAndSenha(login.getLogin(),
				login.getSenha());
		if (!ObjectUtil.isEmpty(currentUser)) {
			addCookie();
			createMenu();
			currentStage = "commonLayout.xhtml";
		} else {
			login.setSenha(null);
			addErrorMessage("Login ou Senha  não conferem!\nVerefique os dados informados");
		}
	}
	
	public void endSession(){
		removeCookie();
		this.login = new Login();
		this.currentUser = null;
		this.currentStage = "login.xhtml";
	}
	
	private void cookieAuthenticate() throws Exception{
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

	protected void buildMenu(Menu... menuItem) {
		this.menuItem.clear();
		if (menuItem != null) {
			this.menuItem.addAll(Arrays.asList(menuItem));
		}
	}

	@PostConstruct
	public void init() {
		try {
			cookieAuthenticate();	
		} catch (Exception e) {
			endSession();
		}
	}

	protected void createMenu() {
		buildMenu(new Menu("MENU_TABELA", "Tabela").add(new Menu(
				"MENU_ENDERECO", "Endereço", "", "/resources/img/endereco.png")
				.add(new Menu("MENU_ITEM_PAIS", "País",
						"paginas/user/userList.xhtml"), new Menu(
						"MENU_ITEM_ESTADO", "Estado",
						"paginas/estado/estadoList.xhtml"), new Menu(
						"MENU_ITEM_CIDADE", "Cidade", "paginas/home.xhtml"))),
				new Menu("MENU_SISTEMA", "Sistema").add(new Menu(
						"MENU_ITEM_USUARIO", "Usuario",
						"paginas/user/userList.xhtml")));
	}

	public void openMenuItem() {
		System.out.println("abrindo");
	}

	public List<Menu> getMenuItem() {
		System.out.println(menuItem);
		System.out.println(menuItem.size());
		return menuItem;
	}

	public void setMenuItem(List<Menu> menuItem) {
		this.menuItem = menuItem;
	}

	public String getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(String currentPage) {
		this.currentPage = currentPage;
	}

	public String getCurrentStage() {
		return currentStage;
	}

	public void setCurrentStage(String currentStage) {
		this.currentStage = currentStage;
	}

}
