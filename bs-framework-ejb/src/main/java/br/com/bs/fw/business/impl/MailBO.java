package br.com.bs.fw.business.impl;


public class MailBO {
/*	@Resource(name = "mail/mailSession")
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
	}*/
}
