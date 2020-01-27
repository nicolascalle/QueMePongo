package prenda;

public enum Tipo {

	// PARTE SUPERIOR
	REMERA(2,1),
	MUSCULOSA(1,1),
	CAMISA(3,1),
	BUZO(6,2),
	CAMPERA(9,3),
	
	//PARTE INFERIOR
	JEAN(4,1),
	SHORT(2,1),
	JOGGING(5,1),
	
	//CALZADO
	ZAPATOS(2,1),
	ZAPATILLAS(3,1),
	SANDALIAS(1,1),
	OJOTAS(1,1),
	
	//ACCESORIO
	PULSERA(0,1),
	GORRO(4,1),
	ANTEOJOS_DE_SOL(0,1);
	
	
	private final int nivelDeCalor; 
	private final int capa;
	  
    // getter method 
    public int getNivelDeCalor(){ 
        return this.nivelDeCalor; 
    } 
    public int getCapa() {
		return capa;
	}
    // enum constructor - cannot be public or protected 
    private Tipo(int nivelDeCalor,int capa){ 
        this.nivelDeCalor = nivelDeCalor; 
        this.capa = capa;
    }

	 
}
