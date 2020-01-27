package guardarropa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import auxiliares.EntidadPersistente;
import prenda.Prenda;
import usuario.Usuario;

@Entity
@Table(name = "atuendo")
public class Atuendo extends EntidadPersistente{
	
	//ATRIBUTOS
	@OneToMany(mappedBy = "prendaID", cascade = {CascadeType.ALL})
	private List<Prenda> prendas = new ArrayList<Prenda>();
	
	@OneToOne(cascade = {CascadeType.ALL})
	private Guardarropa guardarropa;
	
	@Column(name="puntaje")
	private int puntaje;
	
	//CONSTRUCTOR
	public Atuendo() {}
	public Atuendo(List<Prenda> prendas) { this.prendas = prendas;}
	
	//GETTERS AND SETTERS
	public List<Prenda> getPrendas() { return prendas; }
	public void setPrendas(List<Prenda> prendas) { this.prendas = prendas; }
	public Guardarropa getGuardarropa() { return guardarropa; }
	public void setGuardarropa(Guardarropa guardarropa) { this.guardarropa = guardarropa; }
	public int getPuntaje() { return puntaje; }
	public void setPuntaje(int puntaje) { this.puntaje = puntaje; }
	
	//METHODS
	public void agregarPrenda(Prenda prenda) { prendas.add(prenda); }
	public int cantidadPrendas() { return prendas.size(); }
	public void removerPrendas() { prendas.clear(); }
	public void calificar(int puntaje) {
		this.puntaje = puntaje;
		this.guardarropa.agregarAtuendoCalificado(this);
	}
	

}
