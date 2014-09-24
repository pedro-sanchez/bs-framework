package br.com.bs.fw.vo;

public class Modal {

	private String url;

	private Object managedBean;

	private String elementFocus;

	public Modal(String url, Object managedBean) {
		this.url = url;
		this.managedBean = managedBean;
	}

	public Modal(String url, Object managedBean, String elementFocus) {
		this(url, managedBean);
		this.elementFocus = elementFocus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Object getManagedBean() {
		return managedBean;
	}

	public void setManagedBean(Object managedBean) {
		this.managedBean = managedBean;
	}

	public String getElementFocus() {
		return elementFocus;
	}

	public void setElementFocus(String elementFocus) {
		this.elementFocus = elementFocus;
	}
}
