package notificaciones;

import java.util.ArrayList;
import java.util.List;

import evento.Evento;
import usuario.Usuario;

public class NotificationSender {

	private static NotificationSender instance = new NotificationSender();

	public static NotificationSender getInstance() {
		return instance;
	}
	
	private NotificationSender() {
		super();
	}
	
	public void notificarUsuarioAnteGeneracionDeSugerenciasParaEvento(Usuario usuario, Evento evento) {
		List<NotificationSenderAdapter> notificationSenderAdapters = getListNotificationSenderAdapters(usuario);
		for(NotificationSenderAdapter notificationSenderAdapter : notificationSenderAdapters) {
			notificationSenderAdapter.enviarNotificacion(usuario, 
					"Hola " + usuario.getUser() + "! Se han generado nuevas sugerencias para el evento '" + evento.getDescripcion() + "'." );
		}
	}
	
	public void notificarUsuarioAnteCambioDeSugerenciasParaEvento(Usuario usuario, Evento evento) {
		List<NotificationSenderAdapter> notificationSenderAdapters = getListNotificationSenderAdapters(usuario);
		for(NotificationSenderAdapter notificationSenderAdapter : notificationSenderAdapters) {
			notificationSenderAdapter.enviarNotificacion(usuario, 
					"Hola " + usuario.getUser() + "! Se han generado nuevas sugerencias para el evento '" + evento.getDescripcion() + "' debido a un cambio en el pronostico del clima." );
		}
	}
	
	private List<NotificationSenderAdapter> getListNotificationSenderAdapters(Usuario usuario){
		List<NotificationSenderAdapter> notificationSenderAdapters = new ArrayList<NotificationSenderAdapter>();
		if(usuario.getNotificarViaWhatsApp()) notificationSenderAdapters.add(new WhatsAppSenderAdapter());
		if(usuario.getnotificarViaMail()) notificationSenderAdapters.add(new MailSenderAdapter());
		return notificationSenderAdapters;
	}
	
}
