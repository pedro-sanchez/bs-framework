package br.com.bs.fw.menubar;

public class MenuNode extends Menu{
	
	public MenuNode(String role, String label) {
		setRole(role);
		setLabel(label);
	}
	
	public MenuNode(String role, String label, Menu ... menus) {
		this(role, label);
		add(menus);
	}
	
	public MenuNode(String role, String label, String icon) {
		this(role, label);
		setIcon(icon);
	}
	
	public MenuNode(String role, String label, String icon, Menu ... menus) {
		this(role, label, icon);
		add(menus);
	}
	
	@Override
	public Menu add(Menu... itens) {
		return super.add(itens);
	}
	
}
