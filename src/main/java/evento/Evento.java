package evento;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;

import javax.persistence.*;

import algoritmo.Algoritmo;
import auxiliares.EntidadPersistente;
import converters.LocalDateAttributeConverter;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import notificaciones.NotificationSender;
import usuario.Posicion;
import usuario.Usuario;

@Entity
@Table(name = "evento")
public class Evento extends EntidadPersistente {
	
	//ATRIBUTOS
	@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	@OneToOne(cascade = {CascadeType.ALL})
	private Posicion posicion;//Posicion(String lugar, int temperatura)
	//, columnDefinition = "Date"
	@Column(name = "fecha", columnDefinition = "DATE")
	@Convert(converter = LocalDateAttributeConverter.class)
	public LocalDate fecha;
    @Column(name = "descripcion")
	private String descripcion;
    @Transient   
	private Atuendo sugerencia;
	@ManyToOne(cascade = {CascadeType.ALL})
	public Guardarropa guardarropa;
	@OneToOne(cascade = {CascadeType.ALL})
	public Periodicidad periodicidad;

	
	public Periodicidad getPeriodicidad() {
		return periodicidad;
	}

	public void setPeriodicidad(Periodicidad periodicidad) {
		this.periodicidad = periodicidad;
	}

	//CONSTRUCTOR
	public Evento() {}
	public Evento(Posicion _posicion, LocalDate _fecha, String _descripcion, Guardarropa _guardarropa) {
		setPosicion(_posicion);
		setFecha(_fecha);
		setDescripcion(_descripcion);
		setGuardarropa(_guardarropa);
	}

	//GETTERS Y SETTERS
	public Atuendo getSugerencia() { return sugerencia; }
	public void setSugerencia(Atuendo sugerencia) { this.sugerencia = sugerencia; }	
	public Posicion getPosicion() { return posicion; }
	public void setPosicion(Posicion posicion) { this.posicion = posicion; }
	public LocalDate getFecha() { return fecha; }
	public void setFecha(LocalDate fecha) { this.fecha = fecha; }
	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
	public Guardarropa getGuardarropa() { return guardarropa; }
	private void setGuardarropa(Guardarropa guardarropa) { this.guardarropa = guardarropa; }

	//METODOS
	public void generarSugerencia(Usuario usuario) {
		if(Period.between(LocalDate.now(),this.fecha).getDays() <= 5) {
			sugerencia = Algoritmo.obtenerAtuendo(usuario, this.posicion.getTemperatura(), guardarropa);
			NotificationSender.getInstance().notificarUsuarioAnteGeneracionDeSugerenciasParaEvento(usuario, this);
		}		
	}
	
	//Crear eventos Repetitivos 
	public void crearPeriodicidad(Usuario usuario) {
		this.getPeriodicidad().crearEventos(usuario, this);
	}
	
	//Mostrar por pantalla los eventos 
	public void mostrarEvento() {
		System.out.println("El evento "+this.getDescripcion()+" " + "Tiene la fecha "+ this.getFecha());
	}
}
