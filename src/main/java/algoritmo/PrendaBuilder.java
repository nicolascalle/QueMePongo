package algoritmo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import prenda.Categoria;
import prenda.Material;
import prenda.Prenda;
import prenda.Tipo;

public class PrendaBuilder {

	private Map<Categoria, List<Tipo>> tiposPosiblesParaCategoria;
	private Map<Tipo, List<Material>> materialesPosiblesParaTipo;
	
	
	public PrendaBuilder() {
		super();
		
		this.tiposPosiblesParaCategoria = new HashMap<Categoria, List<Tipo>>();
		
		List<Tipo> tiposParteSuperior = new ArrayList<Tipo>();
		tiposParteSuperior.add(Tipo.REMERA);
		tiposParteSuperior.add(Tipo.MUSCULOSA);
		tiposParteSuperior.add(Tipo.CAMISA);
		this.tiposPosiblesParaCategoria.put(Categoria.PARTE_SUPERIOR, tiposParteSuperior);
		
		List<Tipo> tiposParteInferior = new ArrayList<Tipo>();
		tiposParteInferior.add(Tipo.JEAN);
		tiposParteInferior.add(Tipo.SHORT);
		tiposParteInferior.add(Tipo.JOGGING);
		this.tiposPosiblesParaCategoria.put(Categoria.PARTE_INFERIOR, tiposParteInferior);
		
		List<Tipo> tiposCalzado = new ArrayList<Tipo>();
		tiposCalzado.add(Tipo.ZAPATOS);
		tiposCalzado.add(Tipo.ZAPATILLAS);
		tiposCalzado.add(Tipo.SANDALIAS);
		tiposCalzado.add(Tipo.OJOTAS);
		this.tiposPosiblesParaCategoria.put(Categoria.CALZADO, tiposCalzado);
		
		List<Tipo> tiposAccesorio = new ArrayList<Tipo>();
		tiposParteSuperior.add(Tipo.PULSERA);
		tiposParteSuperior.add(Tipo.GORRO);
		tiposParteSuperior.add(Tipo.ANTEOJOS_DE_SOL);
		this.tiposPosiblesParaCategoria.put(Categoria.ACCESORIO, tiposAccesorio);
		
		
		this.materialesPosiblesParaTipo = new HashMap<Tipo, List<Material>>();
		List<Material> materialesRemera = new ArrayList<Material>();
		materialesRemera.add(Material.ALGODON);
		materialesRemera.add(Material.LYCRA);
		materialesRemera.add(Material.SEDA);
		materialesPosiblesParaTipo.put(Tipo.REMERA, materialesRemera);
		List<Material> materialesMusculosa = new ArrayList<Material>();
		materialesMusculosa.add(Material.ALGODON);
		materialesMusculosa.add(Material.LYCRA);
		materialesPosiblesParaTipo.put(Tipo.MUSCULOSA, materialesMusculosa);
		List<Material> materialesCamisa = new ArrayList<Material>();
		materialesCamisa.add(Material.ALGODON);
		materialesPosiblesParaTipo.put(Tipo.CAMISA, materialesCamisa);
		List<Material> materialesJean = new ArrayList<Material>();
		materialesJean.add(Material.JEAN);
		materialesPosiblesParaTipo.put(Tipo.JEAN, materialesJean);
		List<Material> materialesShort = new ArrayList<Material>();
		materialesShort.add(Material.ALGODON);
		materialesShort.add(Material.LYCRA);
		materialesShort.add(Material.JEAN);
		materialesPosiblesParaTipo.put(Tipo.SHORT, materialesShort);
		List<Material> materialesJogging = new ArrayList<Material>();
		materialesJogging.add(Material.ALGODON);
		materialesJogging.add(Material.LYCRA);
		materialesPosiblesParaTipo.put(Tipo.JOGGING, materialesJogging);
		List<Material> materialesZapato = new ArrayList<Material>();
		materialesZapato.add(Material.CUERO);
		materialesPosiblesParaTipo.put(Tipo.ZAPATOS, materialesZapato);
		List<Material> materialesZapatillas = new ArrayList<Material>();
		materialesZapatillas.add(Material.CUERO);
		materialesPosiblesParaTipo.put(Tipo.ZAPATILLAS, materialesZapatillas);
		List<Material> materialesSandalia = new ArrayList<Material>();
		materialesSandalia.add(Material.CUERO);
		materialesPosiblesParaTipo.put(Tipo.SANDALIAS, materialesSandalia);
		List<Material> materialesOjotas = new ArrayList<Material>();
		materialesOjotas.add(Material.GOMA);
		materialesPosiblesParaTipo.put(Tipo.OJOTAS, materialesOjotas);
		List<Material> materialesPulsera = new ArrayList<Material>();
		materialesPulsera.add(Material.PLASTICO);
		materialesPosiblesParaTipo.put(Tipo.PULSERA, materialesPulsera);
		List<Material> materialesGorro = new ArrayList<Material>();
		materialesGorro.add(Material.PLASTICO);
		materialesPosiblesParaTipo.put(Tipo.PULSERA, materialesGorro);
		List<Material> materialesAnteojosDeSol = new ArrayList<Material>();
		materialesAnteojosDeSol.add(Material.PLASTICO);
		materialesPosiblesParaTipo.put(Tipo.ANTEOJOS_DE_SOL, materialesAnteojosDeSol);
	}
	
	public void validarPrenda(Prenda prenda) {
		Categoria categoria = prenda.getCategoria();
		Tipo tipo = prenda.getTipo();
		List<Tipo> tiposCategoria = tiposPosiblesParaCategoria.get(categoria);
		if(! tiposCategoria.contains(prenda.getTipo())){
			throw new RuntimeException("El tipo de prenda no corresponde "
					+ "a la categoria seleccionada para la misma");
		}
		List<Material> materialesTipo = materialesPosiblesParaTipo.get(tipo);
		if(! materialesTipo.contains(prenda.getMaterial())) {
			throw new RuntimeException("La tela de la prenda no corresponde "
					+ "al tipo seleccionada para la misma");
		}
	}
	
	public Prenda validarCaracteristicasDePrenda(String prendaId, String descripcion, Categoria categoria, Tipo tipo, Material material,
			String colorPrimario, String colorSecundario) {
		List<Tipo> tiposCategoria = tiposPosiblesParaCategoria.get(categoria);
		if(! tiposCategoria.contains(tipo)){
			throw new RuntimeException("El tipo de prenda no corresponde "
					+ "a la categoria seleccionada para la misma");
		}
		List<Material> materialesTipo = materialesPosiblesParaTipo.get(tipo);
		if(! materialesTipo.contains(material)) {
			throw new RuntimeException("La tela de la prenda no corresponde "
					+ "al tipo seleccionada para la misma");
		}
		if(colorPrimario == null || colorPrimario.isEmpty()) {
			throw new RuntimeException("El color primario de la prenda no puede quedar vacio");
		}
		if(colorSecundario != null && colorPrimario == colorSecundario) {
			throw new RuntimeException("El color secundario debe ser diferente al primario");
		}
		// TODO validar que los colores sean hexadecimales

		Prenda prenda = new Prenda(prendaId, descripcion, categoria, tipo, material, colorPrimario, colorSecundario, null);
		return prenda;		
	}
	
	
	
}
