package usuario;

import guardarropa.Atuendo;
import guardarropa.Guardarropa;

public class Aceptar implements CommandAceptador{
	
	@Override
	public Atuendo execute(Atuendo atuendo, int temperatura) {
		Guardarropa guardarropa = atuendo.getGuardarropa();
		guardarropa.cambiarDisponibiladadPrendas(atuendo.getPrendas(), false);
		return null;
	}

}
