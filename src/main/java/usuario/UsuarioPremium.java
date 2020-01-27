package usuario;

import java.util.List;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import guardarropa.Guardarropa;

@Entity
@DiscriminatorValue("Premium")
public class UsuarioPremium extends TipoUsuario{
	
	public UsuarioPremium() {}
	
	public void limitarGuardarropas(List<Guardarropa> guardarropas) {
		guardarropas.forEach(guardarropa->guardarropa.sinLimiteCapacidad());
	}
	
	public void limitarGuardarropa(Guardarropa guardarropa) {}
	
}
