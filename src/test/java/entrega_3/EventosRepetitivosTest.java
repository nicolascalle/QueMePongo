package entrega_3;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import evento.Diaria;
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
import usuario.UsuarioPremium;

public class EventosRepetitivosTest {
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
	public void CrearEventosDiarios() {
		Posicion posicion = new Posicion("CABA", 10);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		Evento evento = usuario1.crearEvento(posicion, 20, 8, 2019, "Ir al Trabajo",lista1Guardarropas.get(0));
		usuario1.crearPeriodicidadDiaria(evento, 24, 8, 2019);
		System.out.println("se crearon los siguientes Eventos Diarios");
		usuario1.mostrarEventos("Ir al Trabajo");
		assertEquals((usuario1.getEventos()).size(),5);
	}
	
	@Test
	public void CrearEventosSemanales() {
		Posicion posicion = new Posicion("CABA", 10);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		Evento evento = usuario1.crearEvento(posicion, 20, 8, 2019, "Ir al Super",lista1Guardarropas.get(0));
		usuario1.crearPeriodicidadSemanal(evento, 20, 9, 2019);
		System.out.println("se crearon los siguientes Eventos Semanales");
		usuario1.mostrarEventos("Ir al Trabajo");
		assertEquals((usuario1.getEventos()).size(),5);
	}
	
	@Test
	public void CrearEventosMensuales() {
		Posicion posicion = new Posicion("CABA", 10);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		Evento evento = usuario1.crearEvento(posicion, 31, 1, 2019, "Ir al Super",lista1Guardarropas.get(0));
		usuario1.crearPeriodicidadMensual(evento, 20, 5, 2019);
		System.out.println("se crearon los siguientes Eventos Mensuales");
		usuario1.mostrarEventos("Ir al Trabajo");
		assertEquals((usuario1.getEventos()).size(),4);
	}
	
	@Test
	public void CrearEventosAnuales() {
		Posicion posicion = new Posicion("CABA", 10);
		usuario1 = new Usuario("roberto","pepapig",lista1Guardarropas, posicion,new UsuarioPremium(), Sensibilidad.NORMAL, false, "", false, "");
		Evento evento = usuario1.crearEvento(posicion, 31, 1, 2019, "Ir al Super",lista1Guardarropas.get(0));
		usuario1.crearPeriodicidadAnual(evento, 20, 5, 2021);
		System.out.println("se crearon los siguientes Eventos Anual");
		usuario1.mostrarEventos("Ir al Trabajo");
		assertEquals((usuario1.getEventos()).size(),3);
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
}