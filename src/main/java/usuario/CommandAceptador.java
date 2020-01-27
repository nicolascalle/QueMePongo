package usuario;

import guardarropa.Atuendo;

public interface CommandAceptador {
	public Atuendo execute(Atuendo atuendo, int temperatura);
}
