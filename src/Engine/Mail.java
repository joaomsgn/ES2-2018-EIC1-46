package Engine;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;


public class Mail extends Thread{
	private String fileName;
	private String host;
	private static String user;
	private static String password;

	private Properties properties;

	private MimeMessage message;
	private BodyPart messageBodyPart;
	private Multipart multipart;

	private Authenticator authenticator;
	private boolean send = false;

	String  to, subject, messageBody;

	public Mail() {
		fileName = "quiz";
		host = "smtp.gmail.com";

		authenticator = new SMTPAuthenticator();
		properties = System.getProperties();
		properties.put("mail.smtp.host", host);
		properties.put("mail.smtp.starttls.enable", "true");

		properties.put("mail.smtp.ssl.trust", "smtp.gmail.com");

		properties.put("mail.smtp.port", "587");
		properties.put("mail.smtp.auth", "true");
	}

	private void sendMail(String from, String to, String subject, String messageBody) {
		try {
			Session session = Session.getDefaultInstance(properties, authenticator);
			message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);

			multipart = new MimeMultipart();
			messageBodyPart = new MimeBodyPart();
			messageBodyPart.setContent(messageBody, "text/html");
			multipart.addBodyPart(messageBodyPart);

			messageBodyPart = new MimeBodyPart();
			DataSource source = new FileDataSource(fileName);
			messageBodyPart.setDataHandler(new DataHandler(source));
			messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);

			Transport.send(message);
			System.out.println("Message send successfully....");
		} catch (Exception me) {
			me.printStackTrace();
		}
	}
	
	void performTask() {
		sendMail(user, to, subject, messageBody);
		user = "es2.2018.eic1.46@gmail.com";
		password = "ESIIAdmin";
		sendMail(to, user, subject, messageBody);
	}

	public void setValues(String from, String to, String subject, String messageBody, String mail, String pass) {
		this.to=to;
		this.subject=subject;
		this.messageBody=messageBody;
		if(mail.contains("@") && (mail.contains(".com") || mail.contains(".pt"))){
			user = mail;
			password=pass;
		}
		else{
			user = "es2.2018.eic1.46@gmail.com";
			password = "ESIIAdmin";
		}
	}

	/**
	 * SimpleAuthenticator is used to do simple authentication when the SMTP
	 * server requires it.
	 */

	static class SMTPAuthenticator extends Authenticator {

		private static final String SMTP_AUTH_USER = user;
		private static final String SMTP_AUTH_PASSWORD = password;

		public PasswordAuthentication getPasswordAuthentication() {
			String username = SMTP_AUTH_USER;
			String password = SMTP_AUTH_PASSWORD;

			return new PasswordAuthentication(username, password);
		}
	}

//	public static void main(String[] args) {
//		new Mail().performTask("isctee@gmail.com", "isctee@gmail.com", "isctee@gmail.com", "isctee@gmail.com");
//	}

	@Override
	public void run() {
		performTask();
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
