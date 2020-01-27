package Entrega4;

import org.junit.Test;

import database.EntityManagerHelper;
import guardarropa.Guardarropa;
import usuario.Usuario;

public class CrearGuardarropa {
	
	@Test
	public void AgregarGuardarropa() throws Exception {
		Guardarropa guardarropa1 = new Guardarropa();
		guardarropa1.setGuardarropaID(76);
		guardarropa1.setCapacidad(20);
		Usuario usuarioBBDD = (Usuario) EntityManagerHelper.createQuery("from Usuario where User = 'Facu'").getSingleResult();
		usuarioBBDD.agregarGuardarropa(guardarropa1);
		guardarropa1.setUsuario(usuarioBBDD);
		EntityManagerHelper.getEntityManager().getTransaction().begin();
        EntityManagerHelper.getEntityManager().merge(usuarioBBDD);
        EntityManagerHelper.getEntityManager().getTransaction().commit();
        
		}
}
