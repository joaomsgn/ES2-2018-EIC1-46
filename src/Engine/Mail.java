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
	private MailProduct mailProduct = new MailProduct();
	private String host;
	public static String user;
	public static String password;

	private boolean send = false;

	String  to, subject, messageBody;

	public Mail(String mail, String pass) {
		mailProduct.setFileName("ProblemaDeOtimizaçãoDoTipoDouble.xml");
		host = "smtp.gmail.com";
		if(mail.contains("@") && (mail.contains(".com") || mail.contains(".pt"))){
			user = mail;
			password=pass;
		}
		else{
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

	void performTask() {
		mailProduct.sendMail(user, to, subject, messageBody);
		user = "es2.2018.eic1.46@gmail.com";
		password = "ESIIAdmin";
		mailProduct.sendMail(to, user, subject, messageBody);
	}

	public void setValues(String from, String to, String subject, String messageBody) {
		this.to=to;
		this.subject=subject;
		this.messageBody=messageBody;
		
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

	public Object clone() throws java.lang.CloneNotSupportedException {
		Mail clone = (Mail) super.clone();
		clone.mailProduct = (MailProduct) this.mailProduct.clone();
		return clone;
	}
	
}
