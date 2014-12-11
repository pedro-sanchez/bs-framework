package br.com.bs.fw.business.iface;


public interface IMailBO {

	void sendMessage(String emails, String subject, String messageBody);	
}
