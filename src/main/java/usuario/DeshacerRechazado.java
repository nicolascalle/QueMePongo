package usuario;

import guardarropa.Atuendo;

public class DeshacerRechazado implements CommandAceptador{

	@Override
	public Atuendo execute(Atuendo atuendo, int temperatura) {
		return Aceptador.getUltimoAtuendo();
	}
	
}
