package usuario;

import java.util.List;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

import auxiliares.EntidadPersistente;
import guardarropa.Guardarropa;

@Embeddable
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class TipoUsuario {

	public abstract void limitarGuardarropas(List<Guardarropa> guardarropas);
	public abstract void limitarGuardarropa(Guardarropa guardarropa);	
}
