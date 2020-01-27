package entrega_3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

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


public class GuardarropasCompartidosTest {
	private Prenda ojotas, llantasRetro1, collar, gafasDeSol1, remera, cortos, buzo;
	private Prenda chancletas, anillo, shortAllboys, camperaRollings, remeraGOT,sweterFluor,gorraPunk;
	private List<Prenda> prendas;
	List<Guardarropa> guardarropas;
	List<Guardarropa> guardarropas2;
	
	Guardarropa guardarropa1;
	Guardarropa guardarropa2;
	Set<Atuendo> atuendos;
	Usuario usuario1,usuario2;
	Propiedades prop = new Propiedades();
	
	@Before
	public void init() throws FileNotFoundException {	
		instanciarPrendas();
		instanciarListas();
		agregarPrendas();
		instanciarGuardarropas();
	}
	
	@Test
	public void usuario2NoPuedeAccederAPrendasQueUsaUsuario1(){
		usuario1 = new Usuario("cacho","lulu",guardarropas,new Posicion("caba",25), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		usuario2 = new Usuario("jose","pepa",guardarropas,new Posicion("caba",20), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		Atuendo atuendo = usuario1.obtenerAtuendo(guardarropas.get(0));
		mostrarAtuendo(atuendo);
		usuario1.aceptarSugerencia(atuendo);
		Atuendo atuendo2 = usuario2.obtenerAtuendo(guardarropas.get(0));
		mostrarAtuendo(atuendo2);
		assertNotEquals(atuendo2,null);
	}
	
	private void mostrarAtuendo(Atuendo atuendo) {	
		for(int i=0 ;i<atuendo.getPrendas().size();i++) {
			System.out.println(atuendo.getPrendas().get(i).getDescripcion());
		}
		System.out.println();
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
