package API.clima;

import java.net.URISyntaxException;
import java.util.Date;
import java.util.Random;

import clima.Clima;

public class NuestraApiClimaAdapter implements ApiClimaAdapter {

	@Override
	public Clima getClimaActual(String area) throws URISyntaxException {
		Random random = new Random();
		double temperatura = (double) ((random.nextInt(400)) / 10d);
		Clima clima = new Clima();
		clima.setTemperaturaActual(temperatura);
		return clima;
	}

	@Override
	public Clima getPronostico(String area, Date date) throws URISyntaxException {
		Random random = new Random();
		double temperatura = (double) ((random.nextInt(400)) / 10d);
		Clima clima = new Clima();
		clima.setTemperaturaMaxima(temperatura);
		clima.setTemperaturaMinima(temperatura);
		return clima;
	}
	
}
