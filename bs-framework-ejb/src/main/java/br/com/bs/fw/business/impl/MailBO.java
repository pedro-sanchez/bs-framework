package br.com.bs.fw.business.impl;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.Asynchronous;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import br.com.bs.fw.util.ObjectUtil;

public class MailBO {
	
	/*@Resource(name = "mail/mailSession")
	private Session session;

	
	*//**
	 * @param subject
	 * @param messageBody
	 * @param emails um ou mais separados por virgula sem espaço
	 *//*
	
	@Asynchronous
	public void sendMessage(String emails, String subject, String messageBody) {

		if (ObjectUtil.hasEmpty(subject, messageBody, emails)) {
			return;
		}

		try {
			Message message = new MimeMessage(session);
			message.setFrom();
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emails, false));
			message.setSubject(subject);
			message.setHeader("X-Mailer", "JavaMail");
			message.setText(messageBody);
			message.setSentDate(new Date());
			Transport.send(message);

		} catch (MessagingException ex) {
		}
	}
*/}
