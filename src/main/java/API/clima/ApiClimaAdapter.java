package API.clima;

import java.net.URISyntaxException;
import java.util.Date;

import clima.Clima;

public interface ApiClimaAdapter {

	public Clima getClimaActual(String area) throws URISyntaxException;

	public Clima getPronostico(String area, Date date) throws URISyntaxException;
	
}
