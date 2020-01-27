package evento;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import usuario.Usuario;

@Entity
@DiscriminatorValue("Mensual")
public class Mensual extends Periodicidad {
	public Mensual(LocalDate fechaDesde, LocalDate fechaHasta) {
		this.fechaDesde =fechaDesde;
		this.fechaHasta = fechaHasta;
		}
	
	@Override 
	public void crearEventos( Usuario usuario,Evento evento) {
		int i = 1;
		LocalDate fechaAux = this.fechaDesde;
		while (fechaAux.isBefore(this.fechaHasta) || fechaAux.isEqual(this.fechaHasta)) {
			Evento eventoAux = new Evento(evento.getPosicion(),this.fechaDesde.plusMonths(i) ,evento.getDescripcion(), evento.getGuardarropa());
			usuario.getEventos().add(eventoAux); 
			i =  i+1;
			fechaAux = this.fechaDesde.plusMonths(i);
		}
		
	}

}
