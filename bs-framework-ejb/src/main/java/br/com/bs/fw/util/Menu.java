package br.com.bs.fw.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Menu {
	private String label;
	private String role;
	private String url;
	private	String icon;
	private Boolean separator;
	
	private List<Menu> itens;

	public Menu() {
	}
	
	public Menu(Boolean separator) {
		this.separator = separator;
	}

	public Menu(String role, String label) {
		this.role = role;
		this.label = label;
	}

	public Menu(String role, String label, String url) {
		this(role, label);
		this.url=url;
	}
	
	public Menu(String role, String label, String url, String icon) {
		this(role, label, url);		
		this.icon=icon;
	}
	
	public Menu add(Menu... itens){
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

	public void setRole(String role) {
		this.role = role;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Boolean getSeparator() {
		return separator;
	}

	public void setSeparator(Boolean separator) {
		this.separator = separator;
	}

	public List<Menu> getItens() {
		return itens;
	}

	public void setItens(List<Menu> itens) {
		this.itens = itens;
	}	
}
