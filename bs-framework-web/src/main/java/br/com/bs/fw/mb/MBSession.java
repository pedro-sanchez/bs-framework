package br.com.bs.fw.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.bs.fw.menubar.Menu;
import br.com.bs.fw.menubar.MenuNode;
import br.com.bs.fw.menubar.MenuWindow;

@ManagedBean(name = "mbSession")
@SessionScoped
public class MBSession extends MBUserAutentication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8771305167968602628L;

	private List<Menu> menuItem = new ArrayList<>();

	private String currentStage = "login.xhtml";

	private String currentPage = "paginas/home.xhtml";

	private String currentModal = "blank.xhtml";

	@Override
	protected void onLoginSuccess() {
		createMenu();
		this.currentPage = "paginas/home.xhtml";
		this.currentStage = "commonLayout.xhtml";
		this.currentModal = "blank.xhtml";
	}

	@Override
	protected void onEndSession() {
		this.currentStage = "login.xhtml";
		this.currentModal = "blank.xhtml";
	}

	@PostConstruct
	public void init() {
		try {
			cookieAuthenticate();	
		} catch (Exception e) {
			endSession();
		}
	}

	public void abrirModal(String url, Object managedBean){
		currentModal = url;
	}
	
	
	protected void buildMenu(Menu... menuItem) {
		this.menuItem.clear();
		if (menuItem != null) {
			this.menuItem.addAll(Arrays.asList(menuItem));
		}
	}
	
	protected void createMenu() {
		buildMenu(new MenuNode("MENU_TABELA", "Tabela").add(
					new MenuNode("MENU_ENDERECO", "Endereço", "/resources/img/endereco.png").add(
							new MenuWindow("MENU_ITEM_PAIS", "País", "paginas/user/userList.xhtml"), 
							new MenuWindow("MENU_ITEM_ESTADO", "Estado","paginas/estado/estadoList.xhtml"), 
							new MenuWindow("MENU_ITEM_CIDADE", "Cidade", "paginas/home.xhtml"))),
					new MenuNode("MENU_SISTEMA", "Sistema").add(
							new MenuWindow("MENU_ITEM_USUARIO", "Usuario", "paginas/user/userList.xhtml")));
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

	public String getCurrentModal() {
		return currentModal;
	}

	public void setCurrentModal(String currentModal) {
		this.currentModal = currentModal;
	}
	
	


}
