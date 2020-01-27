package algoritmo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import prenda.Prenda;
import usuario.ParteSensible;
import usuario.Usuario;


public final class Algoritmo {
	
	private static int temperaturaUsuario;
	private static int temperaturaEvento;
	private static Atuendo atuendo = new Atuendo();
	private static List<Atuendo> atuendosCalificados = new ArrayList<Atuendo>();
	private static List<ParteSensible> partesSensibles;
	private static List<Prenda> partesSuperiores = new ArrayList<Prenda>();
	private static List<Prenda> partesInferiores = new ArrayList<Prenda>();
	private static List<Prenda> calzados = new ArrayList<Prenda>();
	private static List<Prenda> accesorios = new ArrayList<Prenda>();
	private static int capa;
	
	
	public static Atuendo obtenerAtuendo(Usuario usuario, int temperaturaDelEvento,Guardarropa guardarropa) {
		prepararVariables(usuario,temperaturaDelEvento,guardarropa);
		
		if(algunAtuendoCalificadoPositivo()) {
			return atuendoPositivo();
		}

		capa = 1;
		obtenerPrenda(accesorios);
		obtenerPrenda(calzados);
		obtenerPrenda(partesInferiores);
		obtenerPrenda(partesSuperiores);		
		for( capa = 2 ; capa <= 3 ; capa++) {
			obtenerPrenda(accesorios); 		if(abrigaLoQueCorresponde())break;
			obtenerPrenda(calzados); 		if(abrigaLoQueCorresponde())break;
			obtenerPrenda(partesInferiores);if(abrigaLoQueCorresponde())break;
			obtenerPrenda(partesSuperiores);if(abrigaLoQueCorresponde())break;	
		}
		if(!abrigaLoQueCorresponde()) {
			System.out.println("No hay atuendo valido para este clima");
			return null;
		}	

		atuendo.setGuardarropa(guardarropa);
		return atuendo;
	}

	private static Atuendo atuendoPositivo() {
		return atuendosCalificados.stream().filter(atuendo -> atuendo.getPuntaje() > 8).collect(Collectors.toList()).get(0);
	}

	private static boolean algunAtuendoCalificadoPositivo() {
		return atuendosCalificados.stream().anyMatch(atuendo -> atuendo.getPuntaje() > 8);
	}

	private static void obtenerPrenda(List<Prenda> prendas) {
		try {
			Prenda prenda = prendas.stream()
				.filter(unaPrenda->unaPrenda.getTipo().getCapa()==capa)
				.collect(Collectors.toList())
				.get(0);
		
			atuendo.agregarPrenda(prenda);		
			temperaturaUsuario -= prenda.getTipo().getNivelDeCalor();
		}catch(Exception ex) {}	
	}

	private static boolean abrigaLoQueCorresponde() { return between(temperaturaUsuario-temperaturaEvento,-5,5); }
	
	public static boolean between(int i, int minValueInclusive, int maxValueInclusive) {
	    return (i >= minValueInclusive && i <= maxValueInclusive);
	}	
	
	private static void prepararVariables(Usuario usuario, int temperaturaDelEvento, Guardarropa guardarropa) {
		temperaturaEvento = temperaturaDelEvento;
		temperaturaUsuario = usuario.getSensibilidad().getNivelSensibilidad();
		partesSensibles = usuario.getPartesSensibles();
		atuendo.removerPrendas();
		setearPrendas(guardarropa);
		atuendosCalificados = guardarropa.getAtuendosCalificados();
	}
	
	public static void setearPrendas(Guardarropa guardarropa) {
		partesSuperiores = guardarropa.getPrendasSuperiores();
		partesInferiores = guardarropa.getPrendasInferiores();
		calzados = guardarropa.getCalzados();
		accesorios = guardarropa.getAccesorios();
	}

}
