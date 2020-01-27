package usuario;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import auxiliares.EntidadPersistente;

@Entity
@Table(name = "posicion")
public class Posicion extends EntidadPersistente{
	@Column(name = "lugar")
	private String lugar;
	
	@Column(name = "temperatura")
	private Integer temperatura;
	
	public Posicion() {}
	public Posicion(String lugar, Integer temperatura) {
		this.lugar = lugar;
		this.temperatura = temperatura;
	}	
	
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	public Integer getTemperatura() {
		return temperatura;
	}
	public void setTemperatura(Integer temperatura) {
		this.temperatura = temperatura;
	}
	
	
}
