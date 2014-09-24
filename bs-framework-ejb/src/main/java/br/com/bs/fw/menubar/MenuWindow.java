package br.com.bs.fw.menubar;

public class MenuWindow extends Menu{

	public MenuWindow(String role, String label, String url) {
		setRole(role);
		setLabel(label);		
		setUrl(url);
	}
	
	public MenuWindow(String role, String label, String icon, String url) {
		this(role, label, url);
		setIcon(icon);
	}		 
}
