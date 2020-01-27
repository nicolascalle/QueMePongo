package notificaciones;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;

import configuracion.AppConfig;
import usuario.Usuario;

public class WhatsAppSenderAdapter implements NotificationSenderAdapter{

	// Find your Account Sid and Token at twilio.com/console
    // DANGER! This is insecure. See http://twil.io/secure
   

	@Override
	public void enviarNotificacion(Usuario usuario, String notificacion) {
		String ACCOUNT_SID = AppConfig.getINSTANCE().getWhatsAppSender_accountSid();
		String AUTH_TOKEN = AppConfig.getINSTANCE().getWhatsAppSender_authToken();
		
		Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:" + usuario.getNumeroDeWhatsApp()),
                new com.twilio.type.PhoneNumber("whatsapp:" + AppConfig.getINSTANCE().getWhatsAppSender_number()),
                notificacion)
            .create();

//        System.out.println(message.getSid());
        System.out.println("Notificacion enviada exitosamente al usuario "+ usuario.getUser() +" por WhatsApp");
	}
	
}
