package usuario;

import guardarropa.Atuendo;

public class Rechazar implements CommandAceptador{

	@Override
	public Atuendo execute(Atuendo atuendo, int temperatura) {	
		atuendo.getGuardarropa().agregarAtuendoRechazado(atuendo);
		return null;
	}
}
