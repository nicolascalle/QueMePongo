package entrega_2;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import prenda.Categoria;
import prenda.Material;
import prenda.Tipo;
import usuario.Posicion;
import usuario.Sensibilidad;
import usuario.Usuario;
import usuario.UsuarioGratuito;
import usuario.UsuarioPremium;
import prenda.Prenda;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;

public class GuardarropaLimitadoTest {
	
	private Prenda ojotas, llantasRetro1, collar, gafasDeSol1, remera, cortos, buzo;
	private List<Prenda> prendas;
	private List<Prenda> prendas2;
	
	List<Guardarropa> guardarropas;
	List<Guardarropa> guardarropas2;
	Guardarropa guardarropa1;
	Guardarropa guardarropa2;
	Set<Atuendo> atuendos;
	
	@Before
	public void init() {
	
		instanciarPrendas();
		instanciarListas();
		agregarPrendas();
		instanciarGuardarropas();

	}
	
	@Test(expected = Exception.class)
	public void cantidadLimitadaDeGuardarropas() throws Exception{
		Usuario usuario = new Usuario("roque", "rol", new ArrayList<Guardarropa>(),new Posicion(null, 0), new UsuarioGratuito(), Sensibilidad.NORMAL, false, "", false, "");
		usuario.agregarGuardarropa(guardarropa1);
		usuario.agregarGuardarropa(guardarropa1);
		usuario.agregarGuardarropa(guardarropa1);
		usuario.agregarGuardarropa(guardarropa1);
	}
	
	@Test 
	public void creacionUsuarioGratuito(){
		guardarropas.add(guardarropa1);
		Usuario usuario = new Usuario("roque", "rol", guardarropas,new Posicion(null, 0), new UsuarioGratuito(), Sensibilidad.NORMAL, false, "", false, "");
		ArrayList<Guardarropa> nuevoGuardarropa = (ArrayList<Guardarropa>) usuario.getGuardarropas();
		assertEquals(3,nuevoGuardarropa.get(0).getPrendas().size());
	}
	
	@Test 
	public void creacionUsuarioPremium(){
		guardarropas.add(guardarropa1);
		Usuario usuario = new Usuario("roque", "rol", guardarropas, new Posicion(null, 0), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		ArrayList<Guardarropa> nuevoGuardarropa = (ArrayList<Guardarropa>) usuario.getGuardarropas();
		assertEquals(5, nuevoGuardarropa.get(0).getPrendas().size());
	}
	
	@Test 
	public void usuarioGratuitoAgregaPrendaSinExceder(){
		guardarropas2.add(guardarropa2);
		Usuario usuario = new Usuario("roque", "rol", guardarropas2, new Posicion(null, 0), new UsuarioGratuito(), Sensibilidad.NORMAL, false, "", false, "");
		usuario.agregarPrenda(cortos, usuario.getGuardarropas().get(0));
		ArrayList<Guardarropa> nuevoGuardarropa = (ArrayList<Guardarropa>) usuario.getGuardarropas();
		assertEquals(2, nuevoGuardarropa.get(0).getPrendas().size());
	}
	
	@Test 
	public void cambioUsuarioPremiumAGratuito(){
		guardarropas.add(guardarropa1);
		Usuario usuario = new Usuario("roque", "rol", guardarropas, new Posicion(null, 0), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		usuario.cambiarTipoUsuario(new UsuarioGratuito());
		ArrayList<Guardarropa> nuevoGuardarropa = (ArrayList<Guardarropa>) usuario.getGuardarropas();
		assertEquals(3,nuevoGuardarropa.get(0).getPrendas().size());
	}
	
	private void instanciarPrendas() {
		ojotas = new Prenda("1","Ojotas", Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		llantasRetro1 = new Prenda("3","LLantas Retro", Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		collar = new Prenda("2","Collar",Categoria.ACCESORIO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);	
		gafasDeSol1 = new Prenda("4","Gafas de Sol",Categoria.ACCESORIO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		remera = new Prenda("5","Remera Racing",Categoria.PARTE_SUPERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		cortos = new Prenda("7","Short Racing",Categoria.PARTE_INFERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		buzo = new Prenda("9","buzo de lana",Categoria.PARTE_SUPERIOR , Tipo.MUSCULOSA, Material.ALGODON, "AZUL","ROJO", null);
	}
	private void instanciarListas() {
		prendas = new ArrayList<>();
		prendas2 = new ArrayList<>();
	}
	private void agregarPrendas() {
		prendas.add(collar);
		prendas.add(gafasDeSol1);
		prendas.add(ojotas);
		prendas.add(llantasRetro1);
		prendas.add(remera);
		prendas2.add(buzo);
	}
	
	private void instanciarGuardarropas() {
		guardarropas = new ArrayList<Guardarropa>();
		guardarropa1 = new Guardarropa(1,prendas);
		guardarropas2 = new ArrayList<Guardarropa>();
		guardarropa2 = new Guardarropa(2,prendas2);
	}

}