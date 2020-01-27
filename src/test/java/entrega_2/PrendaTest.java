package entrega_2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import org.junit.Before;
import org.junit.Test;

import algoritmo.PrendaBuilder;
import prenda.Categoria;
import prenda.Material;
import prenda.Prenda;
import prenda.Tipo;

public class PrendaTest {

	PrendaBuilder prendaBuilder;
	
	@Before
	public void init() {
		prendaBuilder = new PrendaBuilder();
	}
	
	@Test
	public void creacionCorrectaDePrenda() {
		String prendaId = "1";
		String descripcion = "remera 1";
		Categoria categoria = Categoria.PARTE_SUPERIOR;
		Tipo tipo = Tipo.REMERA;
		Material material = Material.ALGODON;
		String colorPrimario = "#FFF";
		String colorSecundario = "#VVV";
		Prenda remera = prendaBuilder.validarCaracteristicasDePrenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario);
		
		assertNotEquals(remera, null);
	}
	
	@Test
	public void dosRemerasSonObjetosDiferentes() {
		String prendaId = "1";
		String descripcion = "remera 1";
		Categoria categoria = Categoria.PARTE_SUPERIOR;
		Tipo tipo = Tipo.REMERA;
		Material material = Material.ALGODON;
		String colorPrimario = "#FFF";
		String colorSecundario = "#VVV";
		Prenda remera1 = prendaBuilder.validarCaracteristicasDePrenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario);
		
		String prendaId2 = "2";
		String descripcion2 = "remera 2";
		Categoria categoria2 = Categoria.PARTE_SUPERIOR;
		Tipo tipo2 = Tipo.REMERA;
		Material material2 = Material.ALGODON;
		String colorPrimario2 = "#FFF";
		String colorSecundario2 = "#VVV";
		Prenda remera2 = prendaBuilder.validarCaracteristicasDePrenda(prendaId2, descripcion2, categoria2, tipo2, material2, colorPrimario2, colorSecundario2);
		
		assertNotEquals(remera1, remera2);
	}
	
	@Test
	public void daErrorAlTratarDeCrearUnaPrendaConCategoriaYTipoInvalidos() {
		 String mensaje = null;
		try {
			String prendaId = "1";
			String descripcion = "remera 1";
			Categoria categoria = Categoria.PARTE_INFERIOR;
			Tipo tipo = Tipo.REMERA;
			Material material = Material.ALGODON;
			String colorPrimario = "#FFF";
			String colorSecundario = "#VVV";
			Prenda remera1 = prendaBuilder.validarCaracteristicasDePrenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario);
		} catch (RuntimeException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("El tipo de prenda no corresponde "
				+ "a la categoria seleccionada para la misma", mensaje);
	}
	
	@Test
	public void daErrorAlTratarDeCrearUnaPrendaConCategoriaYMaterialInvalidos() {
		 String mensaje = null;
		try {
			String prendaId = "1";
			String descripcion = "remera 1";
			Categoria categoria = Categoria.PARTE_SUPERIOR;
			Tipo tipo = Tipo.REMERA;
			Material material = Material.PLASTICO;
			String colorPrimario = "#FFF";
			String colorSecundario = "#VVV";
			Prenda remera1 = prendaBuilder.validarCaracteristicasDePrenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario);
		} catch (RuntimeException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("La tela de la prenda no corresponde "
				+ "al tipo seleccionada para la misma", mensaje);
	}
	
	@Test
	public void daErrorAlTratarDeCrearUnaPrendaSinColorPrimario() {
		 String mensaje = null;
		try {
			String prendaId = "1";
			String descripcion = "remera 1";
			Categoria categoria = Categoria.PARTE_SUPERIOR;
			Tipo tipo = Tipo.REMERA;
			Material material = Material.ALGODON;
			String colorPrimario = "";
			String colorSecundario = "#FFF";
			Prenda remera1 = prendaBuilder.validarCaracteristicasDePrenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario);
		} catch (RuntimeException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("El color primario de la prenda no puede quedar vacio", mensaje);
	}
	
	@Test
	public void daErrorAlTratarDeCrearUnaPrendaConDosColoresIguales() {
		 String mensaje = null;
		try {
			String prendaId = "1";
			String descripcion = "remera 1";
			Categoria categoria = Categoria.PARTE_SUPERIOR;
			Tipo tipo = Tipo.REMERA;
			Material material = Material.ALGODON;
			String colorPrimario = "#FFF";
			String colorSecundario = "#FFF";
			Prenda remera1 = prendaBuilder.validarCaracteristicasDePrenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario);
		} catch (RuntimeException e) {
			mensaje = e.getMessage();
		}
		
		assertEquals("El color secundario debe ser diferente al primario", mensaje);
	}
	
	
	
	
}
