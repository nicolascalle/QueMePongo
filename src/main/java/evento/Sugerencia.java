package evento;

import guardarropa.Atuendo;

public class Sugerencia {

	//Atributos 
	public  Evento evento;
	public  Atuendo atuendo;
	
	//Getters y Setters
	public  Evento getEvento() { return evento; }
	public  void setEvento(Evento evento) { this.evento = evento; }
	public  Atuendo getAtuendo() { return atuendo; }
	public  void setAtuendo(Atuendo atuendo) { this.atuendo = atuendo; }
	
	//Constructor
	public Sugerencia(Evento evento,Atuendo atuendo) {
		setEvento(evento);
		setAtuendo(atuendo);
	}
	
	
}
