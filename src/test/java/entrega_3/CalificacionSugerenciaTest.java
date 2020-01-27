package entrega_3;

import static org.junit.Assert.assertEquals;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import configuracion.Propiedades;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import prenda.Categoria;
import prenda.Material;
import prenda.Prenda;
import prenda.Tipo;
import usuario.Posicion;
import usuario.Sensibilidad;
import usuario.Usuario;
import usuario.UsuarioPremium;

public class CalificacionSugerenciaTest {
	private Prenda ojotas, llantasRetro1, collar, gafasDeSol1, remera, cortos, buzo;
	private Prenda chancletas, anillo, shortAllboys, camperaRollings, remeraGOT,sweterFluor,gorraPunk;
	private List<Prenda> prendas;
	List<Guardarropa> guardarropas;
	List<Guardarropa> guardarropas2;
	
	Guardarropa guardarropa1;
	Guardarropa guardarropa2;
	Set<Atuendo> atuendos;
	Usuario usuario,usuario1;
	Propiedades prop = new Propiedades();
	
	@Before
	public void init() throws FileNotFoundException {
		instanciarPrendas();
		instanciarListas();
		agregarPrendas();
		instanciarGuardarropas();
	}
	
	@Test
	public void calificarSugerenciaAceptadaCon6() {
		System.out.println("---Test 1: la calificacion de la sugerencia es 6---");
		usuario = new Usuario("roque", "rock", guardarropas, new Posicion("caba", 15), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		Atuendo atuendo = usuario.obtenerAtuendo(guardarropa1);
		usuario.aceptarSugerencia(atuendo);
		usuario.calificarSugerencia(6);
		assertEquals(6,atuendo.getPuntaje());
	}



	private void instanciarPrendas() {
		ojotas = new Prenda("1","Ojotas star wars", Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		llantasRetro1 = new Prenda("3","LLantas Retro", Categoria.CALZADO , Tipo.ZAPATILLAS, Material.ALGODON, "AZUL","VERDE", null);
		collar = new Prenda("2","Collar de perlas",Categoria.ACCESORIO , Tipo.ANTEOJOS_DE_SOL, Material.ALGODON, "AZUL","VERDE", null);	
		gafasDeSol1 = new Prenda("4","Gafas de Sol",Categoria.ACCESORIO , Tipo.ANTEOJOS_DE_SOL, Material.ALGODON, "AZUL","VERDE", null);
		remera = new Prenda("5","Remera Racing",Categoria.PARTE_SUPERIOR , Tipo.REMERA, Material.ALGODON, "AZUL","VERDE", null);
		cortos = new Prenda("7","Short Racing",Categoria.PARTE_INFERIOR , Tipo.SHORT, Material.ALGODON, "AZUL","VERDE", null);
		buzo = new Prenda("9","buzo de lana",Categoria.PARTE_SUPERIOR , Tipo.BUZO, Material.ALGODON, "AZUL","ROJO", null);
		chancletas = new Prenda("10","Chancletas toy story", Categoria.CALZADO , Tipo.OJOTAS, Material.CUERO, "AZUL","VERDE", null);
		anillo = new Prenda("11","Anillo de himan", Categoria.ACCESORIO , Tipo.PULSERA, Material.PLASTICO, "AZUL","VERDE", null);
		shortAllboys = new Prenda("12","Short de allboys",Categoria.PARTE_INFERIOR , Tipo.SHORT, Material.ALGODON, "AZUL","VERDE", null);	
		camperaRollings = new Prenda("13","Campera rollings",Categoria.PARTE_SUPERIOR , Tipo.CAMPERA, Material.CUERO, "AZUL","VERDE", null);
		remeraGOT = new Prenda("14","Remera GOT",Categoria.PARTE_SUPERIOR , Tipo.REMERA, Material.ALGODON, "AZUL","VERDE", null);
		sweterFluor = new Prenda("15","Sweter fluor",Categoria.PARTE_SUPERIOR , Tipo.BUZO, Material.SEDA, "AZUL","VERDE", null);
		gorraPunk = new Prenda("16","Gorra punk",Categoria.ACCESORIO , Tipo.GORRO, Material.LYCRA, "AZUL","ROJO", null);
	}
	private void instanciarListas() {
		prendas = new ArrayList<>();
	}
	private void agregarPrendas() {
		prendas.add(collar);
		prendas.add(gafasDeSol1);
		prendas.add(ojotas);
		prendas.add(llantasRetro1);
		prendas.add(remera);
		prendas.add(cortos);
		prendas.add(buzo);
		prendas.add(chancletas);
		prendas.add(anillo);
		prendas.add(shortAllboys);
		prendas.add(camperaRollings);
		prendas.add(remeraGOT);
		prendas.add(sweterFluor);
		prendas.add(gorraPunk);
	}
	private void instanciarGuardarropas() {
		guardarropas = new ArrayList<Guardarropa>();
		guardarropa1 = new Guardarropa(1,prendas);
		guardarropas2 = new ArrayList<Guardarropa>();
		guardarropa2 = new Guardarropa(2,prendas);
		guardarropas.add(guardarropa1);
		guardarropas2.add(guardarropa2);
		
	}
}
