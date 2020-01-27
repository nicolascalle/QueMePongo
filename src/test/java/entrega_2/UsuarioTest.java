package entrega_2;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import evento.Evento;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import prenda.Categoria;
import prenda.Material;
import prenda.Prenda;
import prenda.Tipo;
import usuario.Posicion;
import usuario.Sensibilidad;
import usuario.Usuario;
import usuario.UsuarioGratuito;
import usuario.UsuarioPremium;

public class UsuarioTest {
	
	private Usuario usuario1;
	private Usuario usuario2;
	private Guardarropa guardarropa1;
	private Guardarropa guardarropa2;
	
	private Prenda ojotas,llantasRetro1,mocacines,collar,pulsera,gafasDeSol1,remera,musculosa,chomba,cortos,largos;
	private List<Prenda> prendas1;
	private List<Prenda> prendas2;

	
	List<Guardarropa> lista1Guardarropas;
	List<Guardarropa> lista2Guardarropas;
	
	Set<Atuendo> atuendos;
	
	@Before
	public void init() {
		
		instanciarPrendas();
		instanciarListas();
		agregarPrendasGuardarropa1();
		agregarPrendasGuardarropa2();
		instanciarGuardarropas();

	}
	

	@Test
	public void usuarioBienCreado() {
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, new Posicion("caba",10), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		assertEquals("roberto", usuario1.getUser());
	}
	
	@Test
	public void dosUsuariosBienCreados() {
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, new Posicion("caba",10), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		usuario2 = new Usuario("cacho","rmbler",lista2Guardarropas,new Posicion("caba",10), new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		assertEquals("roberto", usuario1.getUser());
		assertEquals("cacho", usuario2.getUser());
	}
	
	@Test
	public void CrearEvento() {
		Posicion posicion = new Posicion("CABA", 10);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		usuario1.crearEvento(posicion, 17, 8, 2019, "Asado con amigos",lista1Guardarropas.get(0));
		assertEquals((usuario1.getEventos()).size(),1);
	}
//	
//	@Test
//	public void CrearSugerencias() {
//		Posicion posicion = new Posicion("CABA", 20);
//		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium());
//		usuario1.crearEvento(posicion, 12, 8, 2019, "Asado con amigos");
//		usuario1.crearEvento(posicion, 10, 8, 2019, "Asado con Flia");
//		usuario1.crearEvento(posicion, 20, 8, 2019, "Asado trabajo");
//		usuario1.elegirGuardarropa(guardarropa1);
//		usuario1.generarSugerencias();
//		assertEquals((usuario1.getFiltrados()).size(),2);
//	}
	
	@Test
	public void CrearSugerenciaValida() {
		Posicion posicion1 = new Posicion("CABA", 25);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion1,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		usuario1.crearEvento(posicion1, 15, 8, 2019, "Asado con amigos",guardarropa1);
		usuario1.generarSugerencias();
		mostrarAtuendo(usuario1.getEventos().get(0).getSugerencia());
	
	}
	
	@Test
	public void CrearSugerenciaInvalida() {
		Posicion posicion = new Posicion("CABA", 5);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		//La fecha debe ser mayor a 5 dias de la fecha de hoy
		usuario1.crearEvento(posicion, 20, 8, 2019, "Asado con amigos",guardarropa1);
		usuario1.generarSugerencias();
		assertNull(usuario1.getEventos().get(0).getSugerencia());
	}
	
	private void instanciarPrendas() {
		mocacines = new Prenda("2","mocacines cool",Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);	
		ojotas = new Prenda("1","Ojotas", Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		llantasRetro1 = new Prenda("3","LLantas Retro", Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		collar = new Prenda("2","Collar",Categoria.ACCESORIO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);	
		gafasDeSol1 = new Prenda("4","Gafas de Sol",Categoria.ACCESORIO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		remera = new Prenda("5","Remera Racing",Categoria.PARTE_SUPERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		chomba = new Prenda("6","Chomba Job",Categoria.PARTE_SUPERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		cortos = new Prenda("7","Short Racing",Categoria.PARTE_INFERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
		largos = new Prenda("8","Jean",Categoria.PARTE_INFERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
	}
	
	private void instanciarListas() {
		prendas1 = new ArrayList<>();
		prendas2 = new ArrayList<>();	

	}
	
	private void agregarPrendasGuardarropa1() {
		prendas1.add(collar);
		prendas1.add(gafasDeSol1);
		prendas1.add(ojotas);
		prendas1.add(llantasRetro1);	
		prendas1.add(remera);
		prendas1.add(chomba);
		prendas1.add(cortos);
		prendas1.add(largos);
		guardarropa1 = new Guardarropa(1,prendas1);
	}
	
	private void agregarPrendasGuardarropa2() {
		prendas2.add(collar);
		prendas2.add(pulsera);
		prendas2.add(ojotas);prendas2.add(mocacines);
		prendas2.add(llantasRetro1);
		prendas2.add(remera);prendas2.add(musculosa);
		prendas2.add(chomba);
		prendas2.add(cortos);
		guardarropa2 = new Guardarropa(2,prendas2);
	}
	
	
	private void instanciarGuardarropas() {
		lista1Guardarropas = new ArrayList<Guardarropa>();
		lista2Guardarropas = new ArrayList<Guardarropa>();
		lista1Guardarropas.add(guardarropa1);
		lista2Guardarropas.add(guardarropa1);
		lista2Guardarropas.add(guardarropa2);
	}
	
private void mostrarAtuendo(Atuendo atuendo) {
		
		for(int i=0 ;i<atuendo.getPrendas().size();i++) {
			System.out.println(atuendo.getPrendas().get(i).getDescripcion());
		}
		System.out.println();
	}
}
