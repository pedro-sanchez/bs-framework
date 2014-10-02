package br.com.bs.fw.mb;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.bs.fw.menubar.Menu;
import br.com.bs.fw.menubar.MenuNode;
import br.com.bs.fw.menubar.MenuWindow;
import br.com.bs.fw.vo.Modal;

@Named("mbSession")
@SessionScoped
public class MBSession extends MBUserAutentication implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8771305167968602628L;

	private List<Menu> menuItem = new ArrayList<>();

	private String currentStage = "login.xhtml";

	private String currentPage = "paginas/home.xhtml";

	private String currentModalURL = "blank.xhtml";

	private String oldModalData = null;
	
	private String oldModalField = null;
	
	private Stack<Modal> modais = new Stack<Modal>();

	public Modal top() {
		return modais.lastElement();
	}
	
	@Override
	protected void onLoginSuccess() {
		createMenu();
		this.currentPage = "paginas/home.xhtml";
		this.currentStage = "commonLayout.xhtml";
		this.currentModalURL = "blank.xhtml";
		this.oldModalData = null;
		this.oldModalField = null;
	}

	@Override
	protected void onEndSession() {
		this.currentStage = "login.xhtml";
		this.currentModalURL = "blank.xhtml";
		this.oldModalData = null;
		this.oldModalField = null;
	}

	@PostConstruct
	public void init() {
		try {
			cookieAuthenticate();	
		} catch (Exception e) {
			endSession();
		}
	}

	public void openModal(){
		if(!modais.isEmpty()){
			top().setData(oldModalData);
			top().setElementFocus(oldModalField);
		}
		
		modais.push(new Modal(currentModalURL));
	}
	
	public void closeModal(){
		if(!modais.isEmpty()){
			modais.pop();
			if(!modais.isEmpty()){
				currentModalURL = top().getUrl();
				oldModalData = top().getData();
				oldModalField = top().getElementFocus();
			}
			else{
				this.currentModalURL = "blank.xhtml";
				this.oldModalData = null;
				this.oldModalField = null;
			}
		}
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

	public String getCurrentModalURL() {
		return currentModalURL;
	}

	public void setCurrentModalURL(String currentModalURL) {
		this.currentModalURL = currentModalURL;
	}

	public String getOldModalData() {
		return oldModalData;
	}

	public void setOldModalData(String oldModalData) {
		this.oldModalData = oldModalData;
	}

	public String getOldModalField() {
		return oldModalField;
	}

	public void setOldModalField(String oldModalField) {
		this.oldModalField = oldModalField;
	}

}
