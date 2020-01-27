package Entrega4;

import org.junit.Test;

import database.EntityManagerHelper;
import guardarropa.Guardarropa;

public class ConsultarGuardarropa {
	@Test
	public void AgregarGuardarropa() throws Exception {
		Guardarropa guardarropa1 = (Guardarropa) EntityManagerHelper.createQuery("from Guardarropa where guardarropaID = '76'").getSingleResult();
		System.out.println ("El Usuario de este Guardarropa es"+" "+ guardarropa1.getUsuario().getUser());
        
		}
}
