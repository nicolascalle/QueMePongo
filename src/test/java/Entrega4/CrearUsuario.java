package Entrega4;

import org.junit.Test;

import database.EntityManagerHelper;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;
import usuario.Posicion;
import usuario.Sensibilidad;
import usuario.Usuario;
import usuario.UsuarioPremium;

public class CrearUsuario {

	@Test 
	public void PersistitUsuario(){
		Usuario usuario1 = new Usuario();
		usuario1.setUser("Facu");
		usuario1.setPassword("rpffa");
		usuario1.setSensibilidad(Sensibilidad.CALUROSO);
		EntityManagerHelper.beginTransaction();
		EntityManagerHelper.getEntityManager().persist(usuario1);
		EntityManagerHelper.commit();
		}
 
}
