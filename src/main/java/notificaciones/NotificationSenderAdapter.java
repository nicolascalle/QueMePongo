package notificaciones;

import usuario.Usuario;

public interface NotificationSenderAdapter {

	public void enviarNotificacion(Usuario usuario, String notificacion);
	
}
