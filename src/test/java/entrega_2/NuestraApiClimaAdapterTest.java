package entrega_2;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import API.clima.NuestraApiClimaAdapter;
import clima.Clima;

public class NuestraApiClimaAdapterTest {

private NuestraApiClimaAdapter nuestraApiClimaAdapter;
	
	@Before
	public void init() {
		nuestraApiClimaAdapter = new NuestraApiClimaAdapter();
	}
	
	@Test
	public void requestTest() throws ClientProtocolException, IOException, URISyntaxException {
		for(int i= 0; i < 400; i++) {
			Clima clima = nuestraApiClimaAdapter.getClimaActual("buenos aires");
			System.out.println("La temperatura es " + clima.getTemperaturaActual());
			assertNotNull(clima);
			assertTrue(clima.getTemperaturaActual() > 0d && clima.getTemperaturaActual() < 40d);
		}
	}
	
}
