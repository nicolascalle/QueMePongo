package entrega_2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;

import API.clima.AccuWeatherApiAdapter;
import clima.Clima;

public class AccuWeatherAdapterTest {

	private AccuWeatherApiAdapter accuWeatherAdapter;
	
	@Before
	public void init() {
		accuWeatherAdapter = new AccuWeatherApiAdapter();
	}
	
	@Test
	public void funcionaBienGetClimaActual() throws ClientProtocolException, IOException, URISyntaxException {
		Clima clima = accuWeatherAdapter.getClimaActual("banfield");
		assertEquals(12d, clima.getTemperaturaActual(), 1d);
	}
	
	@Test
	public void funcionaBienGetPronostico() throws ClientProtocolException, IOException, URISyntaxException {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 3);
		dt = c.getTime();
		
		Clima clima = accuWeatherAdapter.getPronostico("banfield", dt);
		assertEquals(13d, clima.getTemperaturaMaxima(), 2d);
		assertEquals(7d, clima.getTemperaturaMinima(), 2d);
	}
	
	@Test
	public void fallaPorqueNoEncuentraElLugar() throws ClientProtocolException, IOException, URISyntaxException {
		try {
			Clima clima = accuWeatherAdapter.getClimaActual("Gulagullan");
		} catch (Exception e) {
			assertEquals("No se encontraron resultados posibles para "
					+ "["+ "http://apidev.accuweather.com/locations/v1/search?q=Gulagullan&apikey=hoArfRosT1215" +"]", e.getMessage());
		}
	}
	
}
