package clima;

import API.clima.modelAccuWeather.DailyForecast;
import API.clima.modelAccuWeather.Weather;
import API.clima.modelOpenWeather.Example;
import API.clima.modelOpenWeather.List;

public class ClimaBuilder {

	private static ClimaBuilder climaBuilder; 
	
	public Clima toModeloBaseClima(Weather weather){
		Clima clima = new Clima();
		clima.setTemperaturaActual(weather.getTemperature().getMetric().getValue());
		return clima;
	}
	
	public Clima toModeloBaseClima(DailyForecast dailyForecast){
		Clima clima = new Clima();
		clima.setTemperaturaMaxima(dailyForecast.getTemperature().getMaximum().getValue());
		clima.setTemperaturaMinima(dailyForecast.getTemperature().getMinimum().getValue());
		return clima;
	}
	
	public Clima toModeloBaseClima(Example example){
		Clima clima = new Clima();
		clima.setTemperaturaActual(example.getMain().getTemp() - 273d);
		return clima;
	}
	
	public Clima toModeloBaseClima(List list){
		Clima clima = new Clima();
		clima.setTemperaturaMaxima(list.getMain().getTempMax() - 273d);
		clima.setTemperaturaMinima(list.getMain().getTempMin() - 273d);
		return clima;
	}

	private ClimaBuilder() {
		super();
	}
	
	public static ClimaBuilder getClimaBuilder() {
		if(climaBuilder == null) {
			climaBuilder = new ClimaBuilder();
		}
		return climaBuilder;
	}
	
	
	
}
