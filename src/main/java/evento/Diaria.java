package evento;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import database.EntityManagerHelper;
import usuario.Usuario;

@Entity
@DiscriminatorValue("Diaria")
public class Diaria extends Periodicidad{

public Diaria() {}	
public Diaria(LocalDate fechaDesde, LocalDate fechaHasta) {
	this.fechaDesde =fechaDesde;
	this.fechaHasta = fechaHasta;
	
}
	
@Override 
public void crearEventos( Usuario usuario,Evento evento) {
	int i = 1;
	LocalDate fechaAux = this.fechaDesde;
	while (fechaAux.isBefore(this.fechaHasta) || fechaAux.isEqual(this.fechaHasta)) {
		Evento eventoAux = new Evento(evento.getPosicion(),this.fechaDesde.plusDays(i),evento.getDescripcion(), evento.getGuardarropa());
		usuario.getEventos().add(eventoAux); 
		i =  i+1;
		eventoAux.setPeriodicidad(this);
		eventoAux.setUsuario(usuario);
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().persist(eventoAux);
		EntityManagerHelper.commit();
		EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(usuario);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
		fechaAux = this.fechaDesde.plusDays(i);
	}
	
}
}
