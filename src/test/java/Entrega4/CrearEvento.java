package Entrega4;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

import database.EntityManagerHelper;
import evento.Evento;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import usuario.Posicion;
import usuario.Sensibilidad;
import usuario.Usuario;

public class CrearEvento {

	@Test
	public void CrearEventos() {
		Posicion posicion = new Posicion("Lomasita", 10);
		Usuario usuarioBBDD = (Usuario) EntityManagerHelper.createQuery("from Usuario where User = 'Facu'").getSingleResult();
		List<Guardarropa> lista1Guardarropas = usuarioBBDD.getGuardarropas();
		Evento evento = usuarioBBDD.crearEvento(posicion, 10, 10, 2019, "Pileta455",lista1Guardarropas.get(0));
		evento.setUsuario(usuarioBBDD);
		usuarioBBDD.setSensibilidad(Sensibilidad.CALUROSO);
		EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().persist(evento);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        usuarioBBDD.generarSugerencias();
        EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(usuarioBBDD);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
		mostrarAtuendo(usuarioBBDD.getEventos().get(0).getSugerencia());
		assertEquals((usuarioBBDD.getEventos()).size(),1);
		}
	
private void mostrarAtuendo(Atuendo atuendo) {
		
		for(int i=0 ;i<atuendo.getPrendas().size();i++) {
			System.out.println(atuendo.getPrendas().get(i).getDescripcion());
		}
		System.out.println();
	}
}
