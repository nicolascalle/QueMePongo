package guardarropa;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;


import auxiliares.EntidadPersistente;
import prenda.Prenda;
import usuario.Usuario;

@Entity
@Table (name = "guardarropa")
public class Guardarropa extends EntidadPersistente{
	
	//ATRIBUTOS
	@Column(name = "guardarropaID")
	private int guardarropaID;
	
	@ManyToOne
    @JoinColumn(name = "usuario_id", referencedColumnName = "id")
    private Usuario usuario;
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	@OneToMany(mappedBy="guardarropa", cascade = {CascadeType.ALL})
	@Column(name="prendas")
	private List<Prenda> prendas = new ArrayList<>();
	
	@OneToMany(mappedBy = "id", cascade = {CascadeType.ALL})
	private List<Atuendo> atuendosRechazados = new ArrayList<>();
	
	@OneToMany(mappedBy = "id", cascade = {CascadeType.ALL})
	private List<Atuendo> atuendosCalificados = new ArrayList<>();
	
	@Column(name = "capacidad")
	int capacidad;

	//CONSTRUCTOR
	public Guardarropa() {}
	public Guardarropa(int _guardarropaID,List<Prenda> _prendas) {
		setGuardarropaID(_guardarropaID);
		setPrendas(_prendas);
	}
	

	//GETTERS AND SETTERS	
	public int getGUardarropaID() { return guardarropaID; }
	public void setGuardarropaID(int guardarropaID) { this.guardarropaID = guardarropaID; }
	public List<Prenda> getPrendas() { return prendas; }
	public List<Prenda> getPrendasDisponibles() { return prendas.stream().filter(prenda -> prenda.estaDisponible()).collect(Collectors.toList()); }
	public void setPrendas(List<Prenda> prendas) { this.prendas = prendas; }

	public List<Atuendo> getAtuendosCalificados() { return atuendosCalificados; }


	//METODOS	
	public void agregarPrenda(Prenda prenda) { if(espacioDisponible())prendas.add(prenda); }
	public void agregarPrendas(List<Prenda> prendas) { prendas.forEach(prenda -> this.agregarPrenda(prenda)); }
	
	public List<Prenda> getPrendasSuperiores() { 
		return (List<Prenda>) prendas.stream().filter(prenda->prenda.esParteSuperior()).collect(Collectors.toList());
	}
	
	public List<Prenda> getPrendasInferiores() { 
		return (List<Prenda>) this.getPrendasDisponibles().stream().filter(prenda->prenda.esParteInferior()).collect(Collectors.toList());
	}
	
	public List<Prenda> getCalzados() { 
		return (List<Prenda>) this.getPrendasDisponibles().stream().filter(prenda->prenda.esCalzado()).collect(Collectors.toList());
	}
	
	public List<Prenda> getAccesorios() { 
		return (List<Prenda>) this.getPrendasDisponibles().stream().filter(prenda->prenda.esAccesorio()).collect(Collectors.toList());
	}
	
	
	public void limitarCapacidad(int limiteGuardarropas) {
		this.capacidad = limiteGuardarropas;
		if(prendas.size() > capacidad)removerPrendasQueExcedenCapacidad();
	}

	private boolean espacioDisponible() {
		if((capacidad - prendas.size()) > 0)return true;
		return false;
	}

	private void removerPrendasQueExcedenCapacidad() {
		int prendasAEliminar = prendas.size() - capacidad;
		for(int i = 0; i < prendasAEliminar; i++)prendas.remove(i);
	}
	
	public void sinLimiteCapacidad() { this.capacidad = 999; }
	
	public void agregarAtuendoCalificado(Atuendo atuendo) { atuendosCalificados.add(atuendo); }
	
	public void agregarAtuendoRechazado(Atuendo atuendo) { atuendosRechazados.add(atuendo); }

	public void cambiarDisponibiladadPrendas(List<Prenda> prendas, boolean disponibilidad) {
		this.prendas.forEach(prenda -> prenda.setDisponible(disponibilidad));	
	}
	public List<Atuendo> getAtuendosRechazados() {
		return atuendosRechazados;
	}
	public void setAtuendosRechazados(List<Atuendo> atuendosRechazados) {
		this.atuendosRechazados = atuendosRechazados;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getGuardarropaID() {
		return guardarropaID;
	}
	public void setAtuendosCalificados(List<Atuendo> atuendosCalificados) {
		this.atuendosCalificados = atuendosCalificados;
	}

}