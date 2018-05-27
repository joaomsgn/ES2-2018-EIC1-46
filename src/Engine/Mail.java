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

<<<<<<< HEAD
public class Mail extends Thread {
	private String fileName;
=======

public class Mail extends Thread{
	private MailProduct mailProduct = new MailProduct();
<<<<<<< HEAD
=======
>>>>>>> d27a61514374775671ff174e6810d4d9ee2b0f5b
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
	private String host;
	public static String user;
	public static String password;

<<<<<<< HEAD
=======
<<<<<<< HEAD
	private Properties properties;

	private MimeMessage message;
	private BodyPart messageBodyPart;
	private Multipart multipart;
	private int t;
	private Authenticator authenticator;
=======
>>>>>>> d27a61514374775671ff174e6810d4d9ee2b0f5b
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
	private boolean send = false;

	String to, subject, messageBody;

<<<<<<< HEAD
	public Mail(String mail, String pass) {
		mailProduct.setFileName("ProblemaDeOtimizaçãoDoTipoDouble.xml");
=======
<<<<<<< HEAD
	public Mail(String mail, String pass, int i) {
		t=i;
		fileName = "ProblemaDeOtimizaçãoDoTipoDouble.xml";
=======
	public Mail(String mail, String pass) {
		mailProduct.setFileName("ProblemaDeOtimizaçãoDoTipoDouble.xml");
>>>>>>> d27a61514374775671ff174e6810d4d9ee2b0f5b
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
		host = "smtp.gmail.com";
		if (mail.contains("@") && (mail.contains(".com") || mail.contains(".pt"))) {
			user = mail;
			password = pass;
		} else {
			user = "es2.2018.eic1.46@gmail.com";
			password = "ESIIAdmin";
		}
		mailProduct.setAuthenticator(new SMTPAuthenticator());
		mailProduct.setProperties(System.getProperties());
		mailProduct.getProperties().put("mail.smtp.host", host);
		mailProduct.getProperties().put("mail.smtp.starttls.enable", "true");

		mailProduct.getProperties().put("mail.smtp.ssl.trust", "smtp.gmail.com");

		mailProduct.getProperties().put("mail.smtp.port", "587");
		mailProduct.getProperties().put("mail.smtp.auth", "true");
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
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
			if (to.compareTo("isctee@gmail.com") != 0)
				messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			System.out.println(user + " - " + password);
			Transport.send(message);
			System.out.println("Message send successfully....");
		} catch (Exception me) {
			me.printStackTrace();
		}
	}
	private void sendMail2(String from, String to, String subject, String messageBody) {
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
			DataSource source = new FileDataSource("quiz");
			messageBodyPart.setDataHandler(new DataHandler(source));
			if (to.compareTo("isctee@gmail.com") != 0)
				messageBodyPart.setFileName(fileName);
			multipart.addBodyPart(messageBodyPart);

			message.setContent(multipart);
			System.out.println(user + " - " + password);
			Transport.send(message);
			System.out.println("Message send successfully....");
		} catch (Exception me) {
			me.printStackTrace();
		}
	}
	
=======
>>>>>>> d27a61514374775671ff174e6810d4d9ee2b0f5b
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
	void performTask() {
		mailProduct.sendMail(user, to, subject, messageBody);
		user = "es2.2018.eic1.46@gmail.com";
		password = "ESIIAdmin";
		mailProduct.sendMail(to, user, subject, messageBody);
	}

	void performTask2() {
		sendMail2(user, to, subject, messageBody);
		user = "es2.2018.eic1.46@gmail.com";
		password = "ESIIAdmin";
		sendMail2(to, user, subject, messageBody);
	}
	public void setValues(String from, String to, String subject, String messageBody) {
		this.to = to;
		this.subject = subject;
		this.messageBody = messageBody;

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

	// public static void main(String[] args) {
	// new Mail().performTask("isctee@gmail.com", "isctee@gmail.com",
	// "isctee@gmail.com", "isctee@gmail.com");
	// }

	@Override
	public void run() {
		if(t==1)
		performTask2();
		else
			performTask();
		try {
			this.finalize();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

<<<<<<< HEAD
=======
<<<<<<< HEAD
=======
>>>>>>> 7c27176b63ec5090a680220f52788d19d57889df
	public Object clone() throws java.lang.CloneNotSupportedException {
		Mail clone = (Mail) super.clone();
		clone.mailProduct = (MailProduct) this.mailProduct.clone();
		return clone;
	}
	
>>>>>>> d27a61514374775671ff174e6810d4d9ee2b0f5b
}
