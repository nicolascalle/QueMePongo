package evento;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import usuario.Usuario;


@Entity
@DiscriminatorValue("Semanal")
public class Semanal extends Periodicidad {
	
	public Semanal(LocalDate fechaDesde, LocalDate fechaHasta) {
		this.fechaDesde =fechaDesde;
		this.fechaHasta = fechaHasta;
		}
	
	@Override 
	public void crearEventos( Usuario usuario,Evento evento) {
		int i = 7;
		LocalDate fechaAux = this.fechaDesde;
		while (fechaAux.isBefore(this.fechaHasta) || fechaAux.isEqual(this.fechaHasta)) {
			Evento eventoAux = new Evento(evento.getPosicion(),this.fechaDesde.plusDays(i),evento.getDescripcion(), evento.getGuardarropa());
			usuario.getEventos().add(eventoAux); 
			i =  i+7;
			fechaAux = this.fechaDesde.plusDays(i);
		}
		
	}
}
	