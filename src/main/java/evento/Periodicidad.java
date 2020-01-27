package evento;

import java.time.LocalDate;

import javax.persistence.Convert;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import auxiliares.EntidadPersistente;
import converters.LocalDateAttributeConverter;
import usuario.Usuario;

@Entity
@Table(name = "periodicidad")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Periodicidad extends EntidadPersistente {
	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate fechaDesde;
	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate fechaHasta;
	
	public void crearEventos(Usuario usuario,Evento evento) {
		
	}

	public LocalDate getFechaDesde() {
		return fechaDesde;
	}

	public void setFechaDesde(LocalDate fechaDesde) {
		this.fechaDesde = fechaDesde;
	}

	public LocalDate getFechaHasta() {
		return fechaHasta;
	}

	public void setFechaHasta(LocalDate fechaHasta) {
		this.fechaHasta = fechaHasta;
	}
}
