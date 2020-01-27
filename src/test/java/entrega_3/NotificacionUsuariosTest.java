package entrega_3;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import evento.Evento;
import evento.Scheduler;
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

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/java/applicationContextTest.xml")
public class NotificacionUsuariosTest {
	
	private Usuario usuario;
	
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
		
//		List<Guardarropa> guardarropas = new ArrayList<Guardarropa>();
//		usuario = new Usuario("Cacho","Lulu", guardarropas,new Posicion("caba",25), new UsuarioPremium(), true, "+5491169616540", true, "pastorino.facundo@gmail.com");
		
		usuario = new Usuario("roberto","pepapig",lista1Guardarropas, new Posicion("CABA", null),new UsuarioPremium(), Sensibilidad.NORMAL, true, "+5491169616540", true, "pastorino.facundo@gmail.com");
		usuario.crearEvento(new Posicion("CABA", null), 4, 9, 2019, "Ir al Super",lista1Guardarropas.get(0));
		Evento evento = usuario.crearEvento(new Posicion("CABA", 18), 4, 9, 2019, "Visita al campo con la tia Zulma",lista1Guardarropas.get(0));
		evento.setSugerencia(new Atuendo());
		usuario.crearEvento(new Posicion("CABA", 9), 17, 9, 2019, "Visita al campo con la tia Nelida",lista1Guardarropas.get(0));
	}
	
//	@Test
//	public void enviarNotificacionAUsuarioPorEmail() {
//		MailSenderAdapter mailSenderAdapter = new MailSenderAdapter();
//		mailSenderAdapter.enviarNotificacion(usuario, "mensaje de prueba de enviarNotificacionAUsuarioPorEmail()");
//	}
//	
//	@Test
//	public void enviarNotificacionAUsuarioPorWhatsApp() {
//		WhatsAppSenderAdapter whatsAppSenderAdapter = new WhatsAppSenderAdapter();
//		whatsAppSenderAdapter.enviarNotificacion(usuario, "mensaje de prueba de enviarNotificacionAUsuarioPorWhatsApp()");
//	}
//	
//	@Test
//	public void enviarNotificacionAUsuarioPorSusMediosElegidos() {
//		List<NotificationSenderAdapter> notificationSenderAdapters = usuario.getListNotificationSenderAdapters();
//		for(NotificationSenderAdapter notificationSenderAdapter : notificationSenderAdapters) {
//			notificationSenderAdapter.enviarNotificacion(usuario, "mensaje de prueba de enviarNotificacionAUsuarioPorSusMediosElegidos()");
//		}
//	}
	
	@Test
	public void notificarUusuarioAnteGeneracionDeSugerenciasParaEventoCercano() {
		List<Usuario> usuarios = new ArrayList<Usuario>();
		usuarios.add(usuario);
		Scheduler.getInstance(usuarios).revisarEventosProximosDeUsuarios();
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
