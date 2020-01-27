package entrega_2;

import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Calendar;
import java.util.Date;

import org.apache.http.client.ClientProtocolException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import API.clima.OpenWeatherApiAdapter;
import clima.Clima;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/java/applicationContextTest.xml")
public class OpenWeatherApiAdapterTest {

private OpenWeatherApiAdapter openWeatherApiAdapter;
	

	@Before
	public void init() {
		openWeatherApiAdapter = new OpenWeatherApiAdapter();
	}
	
	@Test
	public void funcionaBienGetClimaActual() throws ClientProtocolException, IOException, URISyntaxException {
		Clima clima = openWeatherApiAdapter.getClimaActual("Banfield");
		assertEquals(13d, clima.getTemperaturaActual(), 1d);
	}
	
	@Test
	public void funcionaBiengetPronostico() throws ClientProtocolException, IOException, URISyntaxException {
		Date dt = new Date();
		Calendar c = Calendar.getInstance(); 
		c.setTime(dt); 
		c.add(Calendar.DATE, 3);
		dt = c.getTime();
		
		Clima clima = openWeatherApiAdapter.getPronostico("Banfield", dt);
		assertEquals(7d, clima.getTemperaturaMaxima(), 2d);
		assertEquals(7d, clima.getTemperaturaMinima(), 2d);
	}
	
}
