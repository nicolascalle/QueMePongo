package usuario;

import guardarropa.Atuendo;
import guardarropa.Guardarropa;

public class DeshacerAceptado implements CommandAceptador{

	@Override
	public Atuendo execute(Atuendo atuendo, int temperatura) {
		Guardarropa guardarropa = Aceptador.getUltimoAtuendo().getGuardarropa();
		guardarropa.cambiarDisponibiladadPrendas(Aceptador.getUltimoAtuendo().getPrendas(), true);
		return atuendo;
	}

}
