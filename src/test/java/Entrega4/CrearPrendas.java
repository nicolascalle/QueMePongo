package Entrega4;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import database.EntityManagerHelper;
import guardarropa.Guardarropa;
import prenda.Categoria;
import prenda.Material;
import prenda.Prenda;
import prenda.Tipo;

public class CrearPrendas {
	private Prenda mocacines; 
	private Prenda ojotas;
	private Prenda llantasRetro1;
	private Prenda collar;
	private Prenda gafasDeSol1;
	private Prenda remera;
	private Prenda chomba;
	private Prenda cortos;
	private Prenda largos;
	private List<Prenda> prendas1 = new ArrayList<>();
	
		@Test
		public void AgregarPrenda() throws Exception {
			
			Prenda mocacines = new Prenda("2","mocacines cool",Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);	
			Guardarropa guardarropa1 = (Guardarropa) EntityManagerHelper.createQuery("from Guardarropa where guardarropaID = '76'").getSingleResult();
			guardarropa1.agregarPrenda(mocacines);
			mocacines.setGuardarropa(guardarropa1);
			
			Prenda llantasRetro1 = new Prenda("3","LLantas Retro", Categoria.CALZADO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
			guardarropa1.agregarPrenda(llantasRetro1);
			llantasRetro1.setGuardarropa(guardarropa1);
	        
			Prenda collar = new Prenda("2","Collar",Categoria.ACCESORIO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);	
			guardarropa1.agregarPrenda(collar);
			collar.setGuardarropa(guardarropa1);
			
			Prenda gafasDeSol1 = new Prenda("4","Gafas de Sol",Categoria.ACCESORIO , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
			guardarropa1.agregarPrenda(gafasDeSol1);
			gafasDeSol1.setGuardarropa(guardarropa1);
			Prenda remera = new Prenda("5","Remera Racing",Categoria.PARTE_SUPERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
			guardarropa1.agregarPrenda(remera);
		remera.setGuardarropa(guardarropa1);
		Prenda chomba = new Prenda("6","Chomba Job",Categoria.PARTE_SUPERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
			guardarropa1.agregarPrenda(chomba);
			chomba.setGuardarropa(guardarropa1);
			Prenda cortos = new Prenda("7","Short Racing",Categoria.PARTE_INFERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
			guardarropa1.agregarPrenda(cortos);
			cortos.setGuardarropa(guardarropa1);
			Prenda largos = new Prenda("8","Jean",Categoria.PARTE_INFERIOR , Tipo.OJOTAS, Material.ALGODON, "AZUL","VERDE", null);
			guardarropa1.agregarPrenda(largos);
			largos.setGuardarropa(guardarropa1);
			EntityManagerHelper.getEntityManager().getTransaction().begin();
	        EntityManagerHelper.getEntityManager().merge(guardarropa1);
	        EntityManagerHelper.getEntityManager().getTransaction().commit();
			
			
			//System.out.println(guardarropa1.getPrendas().size());
			
	}
}
