package API.clima;

import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import API.clima.modelOpenWeather.City;
import API.clima.modelOpenWeather.Example;
import API.clima.modelOpenWeather.ForecastResponse;
import clima.Clima;
import clima.ClimaBuilder;
import configuracion.AppConfig;

public class OpenWeatherApiAdapter implements ApiClimaAdapter, EnvironmentAware{

	private Gson gson = new Gson();
	@Value("${api.clima.openWeather.cityList}")
	private String cityListFile;
	
	@Override
	public Clima getClimaActual(String area) throws URISyntaxException {
		List<City> cities = getPossiblePlaces(area);
		Example data = getPossibleWeathers(cities.get(0));
		Clima clima = ClimaBuilder.getClimaBuilder().toModeloBaseClima(data);
		return clima;
	}

	private List<City> getPossiblePlaces(String area){
//		TODO configurar en ambiente de configuracion
		try {
			String citiesString = "";
			Type listTypeCity = new TypeToken<ArrayList<City>>(){}.getType();
//			
			ApplicationContext context = 
			    	  new ClassPathXmlApplicationContext(new String[] {"applicationContextTest.xml"});
			
			String cityListFileLocation = AppConfig.getINSTANCE().getCityList_openWeather_fileLocation();
			JsonReader reader = new JsonReader(new FileReader(cityListFileLocation));
			List<City> allCities = gson.fromJson(reader, listTypeCity);
			
//			List<City> allCities = gson.fromJson(citiesString, listTypeCity);
			List<City> possibleCities = new ArrayList<City>();
			for(City city : allCities) {
				if(city.getName().toLowerCase().contains(area.toLowerCase())) {
					possibleCities.add(city);
				}
			}
			if(possibleCities.size() == 0) {
				throw new RuntimeException("No se han encontrado lugares con ese nombre para la API de OpenWeather");
			}
			return possibleCities;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("No se pudo leer el archivo de ciudades de la API de OpenWeather");
		}
		
	}
	
	@Override
	public Clima getPronostico(String area, Date eventDate) throws URISyntaxException {
		
		List<City> cities = getPossiblePlaces(area);
		ForecastResponse forecastResponse = getForecast(cities.get(0));
		for(API.clima.modelOpenWeather.List list : forecastResponse.getList()) {
			Date forecastDate = null;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        forecastDate = formatter.parse(list.getDtTxt());
		    } catch (ParseException e) {
		        throw new RuntimeException("No fue posible parsear la fecha de los datos del pronostico obtenido");
		    }
			long diffInHours = TimeUnit.HOURS.convert((forecastDate.getTime() - eventDate.getTime()), TimeUnit.MILLISECONDS);;
			if(diffInHours > 0 && diffInHours < 3) {
				Clima clima = ClimaBuilder.getClimaBuilder().toModeloBaseClima(list);
				return clima;
			}
		}
		throw new RuntimeException("La fecha del evento es mayor a 5 dias");
	}
	
	private Example getPossibleWeathers(City city) {
		//TODO mandar a configuracion
		String jsonWeather = this.makeRequest("http://api.openweathermap.org/data/2.5/weather?id="+ city.getId() +"&appid=fd01471ce17ef7af120c6127cda055a4");
//		List<Weather> weathers = gson.fromJson(jsonWeather, listTypeWeather);
		Example main = gson.fromJson(jsonWeather, Example.class);
		return main;
	}
	
	private ForecastResponse getForecast(City city) {
		//TODO mandar a configuracion
		String jsonWeather = this.makeRequest("http://api.openweathermap.org/data/2.5/forecast?id="+ city.getId() +"&appid=fd01471ce17ef7af120c6127cda055a4");
//		List<Weather> weathers = gson.fromJson(jsonWeather, listTypeWeather);
		ForecastResponse main = gson.fromJson(jsonWeather, ForecastResponse.class);
		return main;
	}
	
	private String makeRequest(String targetURL) {
		try{
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(targetURL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), StandardCharsets.UTF_8);
            return json;
        } catch (IOException ex) {
        	ex.printStackTrace();
        	throw new RuntimeException("No fue posible comimucarse con la url ["+ targetURL +"] ", ex);
        }
	}

	@Override
	public void setEnvironment(Environment environment) {
		// TODO Auto-generated method stub
		
	}

}
