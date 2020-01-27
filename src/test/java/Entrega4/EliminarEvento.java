package Entrega4;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import database.EntityManagerHelper;
import evento.Evento;
import guardarropa.Guardarropa;
import usuario.Usuario;

public class EliminarEvento {
	List<Evento>listaEventos = new ArrayList<>();
	@Test
	public void EliminarUnEvento() {
		Usuario usuarioBBDD = (Usuario) EntityManagerHelper.createQuery("from Usuario where User = 'Bruno'").getSingleResult();
		Guardarropa guardarropaBBDD = (Guardarropa)EntityManagerHelper.createQuery("from Guardarropa where Id = '1'").getSingleResult();
		Evento eventoBBDD = (Evento) EntityManagerHelper.createQuery("from Evento where Id = '1'").getSingleResult();
		usuarioBBDD.eliminarEvento(eventoBBDD);
		EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().remove(eventoBBDD);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().merge(usuarioBBDD);
		EntityManagerHelper.commit();
		EntityManagerHelper.getEntityManager().getTransaction().commit();
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().merge(guardarropaBBDD);
		EntityManagerHelper.commit();
		}
}
