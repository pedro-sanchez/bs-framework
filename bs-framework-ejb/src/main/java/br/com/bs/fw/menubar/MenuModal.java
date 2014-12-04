package br.com.bs.fw.menubar;

public class MenuModal extends Menu{

	public MenuModal(String role, String label, String url) {
		setModal(Boolean.TRUE);
		setRole(role);
		setLabel(label);		
		setUrl(url);
	}
	
	public MenuModal(String role, String label, String icon, String url) {
		this(role, label, url);
		setIcon(icon);
	}		 
}
