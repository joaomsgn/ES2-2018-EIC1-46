package Engine;


import java.util.Properties;
import javax.mail.internet.MimeMessage;
import javax.mail.BodyPart;
import javax.mail.Multipart;
import javax.mail.Authenticator;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.Message;
import javax.mail.internet.MimeMultipart;
import javax.mail.internet.MimeBodyPart;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.activation.DataHandler;
import javax.mail.Transport;

public class MailProduct implements Cloneable {
	private String fileName;
	private Properties properties;
	private MimeMessage message;
	private BodyPart messageBodyPart;
	private Multipart multipart;
	private Authenticator authenticator;

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public Properties getProperties() {
		return properties;
	}

	public void setProperties(Properties properties) {
		this.properties = properties;
	}

	public void setAuthenticator(Authenticator authenticator) {
		this.authenticator = authenticator;
	}

	public void sendMail(String from, String to, String subject, String messageBody) {
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
			System.out.println(Mail.user + " - " + Mail.password);
			Transport.send(message);
			System.out.println("Message send successfully....");
		} catch (Exception me) {
			me.printStackTrace();
		}
	}

	public Object clone() throws CloneNotSupportedException {
		return (MailProduct) super.clone();
	}
}