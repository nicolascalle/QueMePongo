package usuario;

import guardarropa.Atuendo;

public final class Aceptador {
	
	static Aceptar aceptarCommand = new Aceptar();
	static Rechazar rechazarCommand;
	static DeshacerAceptado deshacerAceptadoCommand;
	static DeshacerRechazado deshacerRechazadoCommand;
	static Atuendo ultimoAtuendo;


	public static Atuendo getUltimoAtuendo() {
		return ultimoAtuendo;
	}

	public static Atuendo aceptarSugerencia(Atuendo atuendo) {
		ultimoAtuendo = atuendo;			
		return aceptarCommand.execute(atuendo, 0);
	}
	
	public static Atuendo rechazarSugerencia(Atuendo atuendo, int temperatura) {
		ultimoAtuendo = atuendo;
		return rechazarCommand.execute(atuendo, temperatura);
	}

	public static Atuendo deshacerAceptado(Atuendo atuendo) {
		return deshacerAceptadoCommand.execute(atuendo, 0);
	}
	
	public static Atuendo deshacerRechazado(Atuendo atuendo) {
		return deshacerRechazadoCommand.execute(atuendo, 0);
	}

}
