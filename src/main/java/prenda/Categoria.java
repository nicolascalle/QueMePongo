package prenda;

public enum Categoria {

	PARTE_SUPERIOR("Parte superior"),
	PARTE_INFERIOR("Parte inferior"),
	CALZADO("Calzado"),
	ACCESORIO("Accesorio");
	
	private String categoria;
	
	private Categoria(String categoria) {
		this.categoria = categoria;
	}
	
//	public final Prenda lala() {
//		return new ParteInferior(); 
//	}

	
//	---- GETTERS AND SETTERS
	
	public String getCategoria() { return categoria; }
	public void setCategoria(String categoria) { this.categoria = categoria; }
	
}
