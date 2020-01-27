package evento;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import notificaciones.NotificationSender;
import usuario.Usuario;

public class Scheduler {

	//TODO obtener como variable global o mas tarde obtener usuarios de la base de datos
	private List<Usuario> usuarios;
	
	private static Scheduler instance = new Scheduler();
	
	
	public void revisarEventosProximosDeUsuarios() {
		for(Usuario usuario : usuarios) {
//			usuario.generarSugerencias();
			for(Evento evento : usuario.getEventos()) {
				if(Period.between(LocalDate.now(),evento.getFecha()).getDays() <= 5) {
					if(evento.getPosicion().getTemperatura() == null || evento.getSugerencia() == null) {
						NotificationSender.getInstance().notificarUsuarioAnteGeneracionDeSugerenciasParaEvento(usuario, evento);
					}else {
						//TODO reemplazar por la temperatura obtenida de las APIs
						Integer nuevaTemperatura = 9;
						if(evento.getPosicion().getTemperatura() != null && evento.getPosicion().getTemperatura() != nuevaTemperatura) {
							NotificationSender.getInstance().notificarUsuarioAnteCambioDeSugerenciasParaEvento(usuario, evento);
						}
					}
				}
			}
		}
	}

	
	public static Scheduler getInstance(List<Usuario> usuarios) {
		instance.setUsuarios(usuarios);
		return instance;
	}
	
	private Scheduler() {
		super();
	}
	private void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}
	
}
