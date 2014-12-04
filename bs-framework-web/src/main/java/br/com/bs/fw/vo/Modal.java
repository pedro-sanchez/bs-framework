package br.com.bs.fw.vo;


public class Modal {

	private String url;

	private String data;

	private String elementFocus;

	public Modal(String url) {
		this.url = url;
	}
	

	public Modal(String url, String data, String elementFocus) {
		this(url);
		this.data = data;
		this.elementFocus = elementFocus;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getElementFocus() {
		return elementFocus;
	}

	public void setElementFocus(String elementFocus) {
		this.elementFocus = elementFocus;
	}
}
