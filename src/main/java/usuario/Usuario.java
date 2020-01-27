package usuario;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


import algoritmo.Algoritmo;
import auxiliares.EntidadPersistente;
import database.EntityManagerHelper;
import evento.Anual;
import evento.Diaria;
import evento.Evento;
import evento.Mensual;
import evento.Periodicidad;
import evento.Semanal;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import notificaciones.MailSenderAdapter;
import notificaciones.NotificationSenderAdapter;
import notificaciones.WhatsAppSenderAdapter;
import prenda.Prenda;

@Entity
@Table(name ="usuario")
public class Usuario extends EntidadPersistente {
	
	//ATRIBUTOS
	@Column(name = "user")
	String user;
	
	@Column(name = "password")
	String password;
	
	@ManyToOne( cascade = {CascadeType.ALL})
	Posicion posicion;
	
	//@OneToOne(cascade = {CascadeType.ALL})
	@Embedded
	public TipoUsuario tipoUsuario;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@Column(name="guardarropas")
	private List<Guardarropa> guardarropas;
	
	@OneToMany(mappedBy="usuario", cascade = {CascadeType.ALL})
	private List<Evento> eventos = new ArrayList<>();
	
	@Column(name = "notificarViaWhatsApp")
	private boolean notificarViaWhatsApp;
	@Column(name = "numeroDeWhatsApp")
	private String numeroDeWhatsApp;
	@Column(name = "notificarViaMail")
	private boolean notificarViaMail;
	@Column(name = "email")
	private String email;
	@Enumerated(EnumType.ORDINAL)
	Sensibilidad sensibilidad;
	@Transient
	List<ParteSensible> partesSensibles;
	@OneToOne(cascade = {CascadeType.ALL})
	Atuendo sugerencia;
	

	//CONSTRUCTOR
	public Usuario() {}
	public Usuario(String user, String password, List<Guardarropa> guardarropas, Posicion posicion, TipoUsuario tipoUsuario, 
			Sensibilidad sensibilidad, boolean _notificarViaWhatsApp, String _numeroDeWhatsApp, boolean _notificarViaMail, String _email){
		this.user = user;
		this.password = password;
		this.guardarropas = guardarropas;
		this.posicion = posicion;
		this.tipoUsuario = tipoUsuario;
		tipoUsuario.limitarGuardarropas(guardarropas);	
		this.sensibilidad = sensibilidad;
		this.notificarViaWhatsApp = _notificarViaWhatsApp;
		this.notificarViaMail = _notificarViaMail;
		this.numeroDeWhatsApp = _numeroDeWhatsApp;
		this.email = _email;
	}
	
	//GETTERS AND SETTERS
	public String getUser() { return user; }
	public String getPassword() { return password; }
	public List<Guardarropa> getGuardarropas() { return guardarropas; }
	public List<Evento> getEventos() {return eventos;}
	public void setEventos(List<Evento> eventos) {this.eventos = eventos;}
	public String getNumeroDeWhatsApp() {return numeroDeWhatsApp;}
	public String getEmail() {return email;}
	public boolean getNotificarViaWhatsApp() {return this.notificarViaWhatsApp;}
	public boolean getnotificarViaMail() {return this.notificarViaMail;}
	public Sensibilidad getSensibilidad() { return sensibilidad; }
	public void setSensibilidad(Sensibilidad sensibilidad) { this.sensibilidad = sensibilidad; }
	public List<ParteSensible> getPartesSensibles() { return partesSensibles; }
	public void setPartesSensibles(List<ParteSensible> partesSensibles) { this.partesSensibles = partesSensibles; }
	

	public void agregarPrenda(Prenda prenda,Guardarropa guardarropa){
		guardarropa.agregarPrenda(prenda);
	}
	
	public void agregarGuardarropa(Guardarropa guardarropa) throws Exception{
		//tipoUsuario.limitarGuardarropa(guardarropa);
		int limiteGuardarropas = 3;
		if(guardarropas.size() < limiteGuardarropas) {
			guardarropas.add(guardarropa);
		}else {
			throw new Exception();
		}
	}
	
	public Atuendo obtenerAtuendo(Guardarropa guardarropa){	
		return Algoritmo.obtenerAtuendo(this, posicion.getTemperatura(), guardarropa);	
	}
	
	public void cambiarTipoUsuario(TipoUsuario nuevoTipoUsuario) {
		this.tipoUsuario = nuevoTipoUsuario;
		tipoUsuario.limitarGuardarropas(guardarropas);
	}
		
	//Crear un evento para un lugar determinado en un dia mes y año especifico y agregar descrpcion
	public Evento crearEvento( Posicion _posicion, int dia, int mes, int anio, String _descripcion, Guardarropa _guardarropa) {
		Evento evento = new Evento(_posicion,LocalDate.of(anio, mes , dia),_descripcion, _guardarropa);
		eventos.add(evento);

		return evento;
	}
	//Crear periodicidad Diaria para un Evento 
	public void crearPeriodicidadDiaria(Evento unEvento, int diaHasta, int mesHasta, int anioHasta) {
	Periodicidad periodicidad = new Diaria(unEvento.getFecha(), LocalDate.of(anioHasta, mesHasta, diaHasta));
	unEvento.setPeriodicidad(periodicidad);
	unEvento.crearPeriodicidad(this);
	EntityManagerHelper.beginTransaction();
	EntityManagerHelper.getEntityManager().persist(periodicidad);
	EntityManagerHelper.commit();
	EntityManagerHelper.getEntityManager().getTransaction().begin();
    EntityManagerHelper.getEntityManager().merge(unEvento);
    EntityManagerHelper.getEntityManager().getTransaction().commit();
	
	}
	
	//Crear periodicidad Semanal para un evento 
	public void crearPeriodicidadSemanal(Evento unEvento, int diaHasta, int mesHasta, int anioHasta) {
		Periodicidad periodicidad = new Semanal(unEvento.getFecha(), LocalDate.of(anioHasta, mesHasta, diaHasta));
		unEvento.setPeriodicidad(periodicidad);
		unEvento.crearPeriodicidad(this);
		}
	//Crear Periodicidad Mensual para un evento
	public void crearPeriodicidadMensual(Evento unEvento, int diaHasta, int mesHasta, int anioHasta) {
		Periodicidad periodicidad = new Mensual(unEvento.getFecha(), LocalDate.of(anioHasta, mesHasta, diaHasta));
		unEvento.setPeriodicidad(periodicidad);
		unEvento.crearPeriodicidad(this);
	}
	
	//Crear Periodicidad Anual para un evento
		public void crearPeriodicidadAnual(Evento unEvento, int diaHasta, int mesHasta, int anioHasta) {
			Periodicidad periodicidad = new Anual(unEvento.getFecha(), LocalDate.of(anioHasta, mesHasta, diaHasta));
			unEvento.setPeriodicidad(periodicidad);
			unEvento.crearPeriodicidad(this);
		}
	
	//Generacion de Sugerencias para eventos proximos a 5 dias de la fecha de consulta
	public void generarSugerencias() {
		eventos.forEach(evento->evento.generarSugerencia(this));
	}
	
	public Atuendo obtenerSugerenciaDeEvento(Evento evento) {
		return evento.getSugerencia();
	}
	
	//Aceptacion de sugerencia
	public Atuendo aceptarSugerencia(Atuendo atuendo) {	
		this.sugerencia = atuendo;
		return Aceptador.aceptarSugerencia(atuendo);
	}
	
	//Rechazo de sugerencia y devuelve una nueva
	public Atuendo rechazarSugerencia(Atuendo atuendo, int temperatura) {
		Aceptador.rechazarSugerencia(atuendo,temperatura);
		return Algoritmo.obtenerAtuendo(this,temperatura, atuendo.getGuardarropa());
	}
	
	//Deshacer ultima operacion
	public Atuendo deshacerAceptado(Atuendo atuendo) {
		this.sugerencia = null;
		return Aceptador.deshacerAceptado(atuendo);
	}
	public Atuendo deshacerRechazado(Atuendo atuendo) {
		return Aceptador.deshacerRechazado(atuendo);
	}

	
	//Calificar sugerencia
	public void calificarSugerencia(int puntaje) {
		this.sugerencia.calificar(puntaje);
	}
	
	//Mostrar Eventos 
	public void mostrarEventos(String nombreEvento ) {
		eventos.forEach(evento->evento.mostrarEvento());
	}
	public Posicion getPosicion() {
		return posicion;
	}
	public void setPosicion(Posicion posicion) {
		this.posicion = posicion;
	}
	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}
	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	public boolean isNotificarViaMail() {
		return notificarViaMail;
	}
	public void setNotificarViaMail(boolean notificarViaMail) {
		this.notificarViaMail = notificarViaMail;
	}
	public Atuendo getSugerencia() {
		return sugerencia;
	}
	public void setSugerencia(Atuendo sugerencia) {
		this.sugerencia = sugerencia;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setGuardarropas(List<Guardarropa> guardarropas) {
		this.guardarropas = guardarropas;
	}
	public void setNotificarViaWhatsApp(boolean notificarViaWhatsApp) {
		this.notificarViaWhatsApp = notificarViaWhatsApp;
	}
	public void setNumeroDeWhatsApp(String numeroDeWhatsApp) {
		this.numeroDeWhatsApp = numeroDeWhatsApp;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	//Eliminar un Evento 
	
	public void eliminarEvento(Evento unEvento) {
		this.eventos.remove(unEvento);
		
		
	}
	
}

	