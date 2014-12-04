package br.com.bs.fw.menubar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public abstract class Menu {
	private String label;
	private String role;
	private String url;
	private	String icon;
	private Boolean separator;
	private Boolean modal;
	
	private List<Menu> itens;
	
	Menu add(Menu... itens){
		if(itens!= null){
			if(this.itens== null){
				this.itens =  new ArrayList<>();
			}
			this.itens.addAll(Arrays.asList(itens));
		}
		return this;
	}

	public String getRole() {
		return role;
	}

	void setRole(String role) {
		this.role = role;
	}

	public String getLabel() {
		return label;
	}

	void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getSeparator() {
		return separator;
	}

	void setSeparator(Boolean separator) {
		this.separator = separator;
	}

	public List<Menu> getItens() {
		return itens;
	}

	public Boolean getModal() {
		return modal;
	}

	void setModal(Boolean modal) {
		this.modal = modal;
	}		
}
