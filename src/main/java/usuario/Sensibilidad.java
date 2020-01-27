package usuario;

public enum Sensibilidad {
	
	FRIOLENTO(40),
	NORMAL(30),
	CALUROSO(20);

	private final int nivelSensibilidad; 
	 
    public int getNivelSensibilidad() {
		return nivelSensibilidad;
	}

	private Sensibilidad(int nivelSensibilidad){ 
        this.nivelSensibilidad = nivelSensibilidad; 
    }
}
