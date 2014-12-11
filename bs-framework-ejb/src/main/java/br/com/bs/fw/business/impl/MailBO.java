package br.com.bs.fw.business.impl;

import java.util.Date;
import java.util.Properties;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.bs.fw.business.iface.IMailBO;
import br.com.bs.fw.util.ObjectUtil;


@Local
@Stateless
public class MailBO  implements IMailBO {
	@Resource(name = "java:/mail/mailSession")
	private Session session;

	
	/**
	 * @param subject
	 * @param messageBody
	 * @param emails um ou mais separados por virgula sem espaço
	 */
	@Override
	@Asynchronous
	public void sendMessage(String emails, String subject, String messageBody) {

		if (ObjectUtil.hasEmpty(subject, messageBody, emails)) {
			return;
		}

		try {
			Message message = new MimeMessage(getSession());
			message.setFrom();
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails, false));
			message.setSubject(subject);
			message.setHeader("X-Mailer", "JavaMail");
			message.setText(messageBody);
			message.setSentDate(new Date());
			Transport.send(message);

		} catch (MessagingException ex) {
			ex.printStackTrace();
		}
	}
	
	public Session getSession() {
		if(session ==null){
			final String username = "systemsanchez@gmail.com";
	        final String password = "666tapanaoreia";

	        Properties props = new Properties();
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.starttls.enable", "true");
	        props.put("mail.smtp.host", "smtp.gmail.com");
	        props.put("mail.smtp.port", "587");

	        session = Session.getInstance(props,
	          new javax.mail.Authenticator() {
	            protected PasswordAuthentication getPasswordAuthentication() {
	                return new PasswordAuthentication(username, password);
	            }
	          });
		}
		return session;
	}
}
