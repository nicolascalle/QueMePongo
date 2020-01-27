package API.clima;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
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

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import API.clima.modelAccuWeather.DailyForecast;
import API.clima.modelAccuWeather.ForecastResponse;
import API.clima.modelAccuWeather.Place;
import API.clima.modelAccuWeather.Weather;
import clima.Clima;
import clima.ClimaBuilder;

public class AccuWeatherApiAdapter implements ApiClimaAdapter {

	Gson gson = new Gson();
	
	@Override
	public Clima getClimaActual(String area) throws URISyntaxException {
		URI uri = new URI("file", area, null);
		List<Place> places = getPossiblePlaces(uri.toASCIIString().split(":")[1]);
		if(places.size() > 0) {
			String keyLocation = places.get(0).getKey();
			List<Weather> weathers = getPossibleWeathers(keyLocation);
			if(weathers.size() > 0) {
				return ClimaBuilder.getClimaBuilder().toModeloBaseClima(weathers.get(0));				
			} else {
				throw new RuntimeException("No se encontraron resultados posibles para "
						+ "["+ "http://apidev.accuweather.com/currentconditions/v1/"+ keyLocation +".json?language=en&apikey=hoArfRosT1215" +"]");
			}
		} else {
			throw new RuntimeException("No se encontraron resultados posibles para "
										+ "["+ "http://apidev.accuweather.com/locations/v1/search?q="+ area +"&apikey=hoArfRosT1215" +"]");
		}
	}
	
	@Override
	public Clima getPronostico(String area, Date date) throws URISyntaxException {
		URI uri = new URI("file", area, null);
		List<Place> places = getPossiblePlaces(uri.toASCIIString().split(":")[1]);
		if(places.size() > 0) {
			String keyLocation = places.get(0).getKey();
			DailyForecast dailyForecast = getForecast(keyLocation, date);
			return ClimaBuilder.getClimaBuilder().toModeloBaseClima(dailyForecast);				
		} else {
			throw new RuntimeException("No se encontraron resultados posibles de lugares en AccuWeather para " + area);
		}
	}

	private List<Weather> getPossibleWeathers(String keyLocation) {
		String jsonWeather = makeRequest("http://apidev.accuweather.com/currentconditions/v1/"+ keyLocation +".json?language=en&apikey=hoArfRosT1215");
		Type listTypeWeather = new TypeToken<ArrayList<Weather>>(){}.getType();
		List<Weather> weathers = gson.fromJson(jsonWeather, listTypeWeather);
		return weathers;
	}
	
	private DailyForecast getForecast(String keyLocation, Date eventDate) {
		String jsonWeather = makeRequest("http://dataservice.accuweather.com/forecasts/v1/daily/5day/"+ keyLocation +".json?language=en&apikey=JvbuaVTvQ9lkRFfJpHYbmgKT9o8Ms7gr&metric=true");
		ForecastResponse forecastResponse = gson.fromJson(jsonWeather, ForecastResponse.class);
		
		for(DailyForecast dailyForecast : forecastResponse.getDailyForecasts()) {
			Date forecastDate = null;
			try {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		        forecastDate = formatter.parse(dailyForecast.getDate().substring(0, 10) + " " + dailyForecast.getDate().substring(11, 19));
		    } catch (ParseException e) {
		        throw new RuntimeException("No fue posible parsear la fecha de los datos del pronostico obtenido");
		    }
			long diffInHours = TimeUnit.HOURS.convert((forecastDate.getTime() - eventDate.getTime()), TimeUnit.MILLISECONDS);;
			if(diffInHours > 0 && diffInHours < 24) {
				return dailyForecast;
			}
		}
		throw new RuntimeException("No fue posible parsear la fecha de los datos del pronostico obtenido");
	}

	private List<Place> getPossiblePlaces(String area) {
		String jsonAdministrativeArea = makeRequest("http://apidev.accuweather.com/locations/v1/search?q="+ area +"&apikey=hoArfRosT1215");
		Type listTypeAdministrativeArea = new TypeToken<ArrayList<Place>>(){}.getType();
		List<Place> administrativeAreas = gson.fromJson(jsonAdministrativeArea, listTypeAdministrativeArea);
		return administrativeAreas;
	}
	
	private String makeRequest(String targetURL) {
		try{
			CloseableHttpClient httpClient = HttpClientBuilder.create().build();
			HttpGet request = new HttpGet(targetURL);
            request.addHeader("content-type", "application/json");
            HttpResponse result = httpClient.execute(request);
            String json = EntityUtils.toString(result.getEntity(), "UTF-8");
            return json;
        } catch (IOException ex) {
        	throw new RuntimeException("No fue posible comimucarse con la url ["+ targetURL +"] ", ex);
        }
	}
	
}
