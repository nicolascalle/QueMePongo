package notificaciones;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import configuracion.AppConfig;
import usuario.Usuario;

public class MailSenderAdapter implements NotificationSenderAdapter {

	@Override
	public void enviarNotificacion(Usuario usuario, String notificacion) {

		Properties prop = new Properties();
    	prop.put("mail.smtp.auth", AppConfig.getINSTANCE().getMailSender_smtpAuth());
    	prop.put("mail.smtp.starttls.enable", AppConfig.getINSTANCE().getMailSender_smtpStartTlsEnable());
    	prop.put("mail.smtp.host", AppConfig.getINSTANCE().getMailSender_smtpServerHost());
    	prop.put("mail.smtp.port", AppConfig.getINSTANCE().getMailSender_smtpServerPort());
    	prop.put("mail.smtp.ssl.trust", AppConfig.getINSTANCE().getMailSender_smtpServerHost());
    	String username = AppConfig.getINSTANCE().getMailSender_gmailAccount();
    	String password = AppConfig.getINSTANCE().getMailSender_gmailPassword();
    	
    	Session session = Session.getInstance(prop, new Authenticator() {
    	    @Override
    	    protected PasswordAuthentication getPasswordAuthentication() {
    	        return new PasswordAuthentication(username, password);
    	    }
    	});
    	
    	Message message = new MimeMessage(session);
    	try {
			message.setFrom(new InternetAddress(username));
    	message.setRecipients(
    	  Message.RecipientType.TO, InternetAddress.parse(usuario.getEmail()));
    	message.setSubject("Notificacion desde QueMePongo");
    	 
    	String msg = notificacion;
    	 
    	MimeBodyPart mimeBodyPart = new MimeBodyPart();
    	mimeBodyPart.setContent(msg, "text/html");
    	 
    	Multipart multipart = new MimeMultipart();
    	multipart.addBodyPart(mimeBodyPart);
    	 
    	message.setContent(multipart);
    	 
    	Transport.send(message);
    	
    	} catch (MessagingException e) {
    		// TODO Auto-generated catch block
    		e.printStackTrace();
    	}
    	
    	System.out.println("Notificacion enviada exitosamente al usuario "+ usuario.getUser() +" por mail");
		
	}
	
}
