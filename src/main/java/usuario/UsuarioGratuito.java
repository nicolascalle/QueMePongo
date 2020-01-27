package usuario;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import guardarropa.Guardarropa;

@Entity
@DiscriminatorValue("Gratuito")
public class UsuarioGratuito extends TipoUsuario{
	
	int capacidadGuardarropa = 3;
	
	public UsuarioGratuito() {}
	
	public void limitarGuardarropas(List<Guardarropa> guardarropas) {
		guardarropas.forEach(guardarropa->limitarGuardarropa(guardarropa));
	}
	
	public void limitarGuardarropa(Guardarropa guardarropa) {
		guardarropa.limitarCapacidad(capacidadGuardarropa);
	}
}
