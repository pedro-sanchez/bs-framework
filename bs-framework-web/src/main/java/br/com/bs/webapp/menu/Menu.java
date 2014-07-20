package br.com.bs.webapp.menu;

public class Menu {
	
	private Menu parent;

	private String label;

	private String page;

	public Menu(Menu parent, String label, String page){
		this(label, page);
		this.parent = parent;
	}
	public Menu(String label, String page){
		this.label = label;
		this.page =  page;
	}
	
	public Menu getParent() {
		return parent;
	}

	public void setParent(Menu parent) {
		this.parent = parent;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getPage() {
		return page;
	}

	public void setPage(String page) {
		this.page = page;
	}
	
	
	
}
