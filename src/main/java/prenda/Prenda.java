package prenda;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.codec.binary.StringUtils;

import auxiliares.EntidadPersistente;
import guardarropa.Atuendo;
import guardarropa.Guardarropa;

@Entity
@Table(name = "prenda")
public class Prenda extends EntidadPersistente {
	
	
	private String prendaID;
	@ManyToOne
    @JoinColumn(name = "guardarropa_id", referencedColumnName = "id")
	private Guardarropa guardarropa;
	public Guardarropa getGuardarropa() {
		return guardarropa;
	}
	public void setGuardarropa(Guardarropa guardarropa) {
		this.guardarropa = guardarropa;
	}
	public Boolean getDisponible() {
		return disponible;
	}
	public void setImagen(BufferedImage imagen) {
		this.imagen = imagen;
	}
	@Enumerated(EnumType.STRING)
	private Categoria categoria;
	
	@Enumerated(EnumType.STRING)
	private Tipo tipo;
	
	@Enumerated(EnumType.STRING)
	private Material material;
	
	@Column(name = "colorPrimario")
	private String colorPrimario;
	
	@Column(name = "colorSecundario")
	private String colorSecundario;
	
	@Column(name = "descripcion")
	private String descripcion;
	
	@Column(name = "disponible")
	private Boolean disponible = true;
	
	@Transient
	private BufferedImage imagen;
	
	public Prenda() {}
	public Prenda(String prendaID, String descripcion,Categoria categoria, Tipo tipo, 
			Material material, String colorPrimario, String colorSecundario, String imagenPath) {
		this.prendaID = prendaID;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tipo = tipo;
		this.material = material;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
		this.setImagen(imagenPath);
	}

	
	public void setearCaracteristicasComunes(String prendaID, String descripcion, Tipo tipo, 
			Material material, String colorPrimario, String colorSecundario) {
		this.prendaID = prendaID;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.material = material;
		this.colorPrimario = colorPrimario;
		this.colorSecundario = colorSecundario;
	}
	
//	---- GETTERS AND SETTERS	
	



	public String getPrendaID() { return prendaID; }
    public void setPrendaID(String prendaID) { this.prendaID = prendaID; }	
	public String getDescripcion() { return descripcion; }
	public void setDescripcion(String descripcion) { this.descripcion = descripcion; }	
	public Categoria getCategoria() { return categoria; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
	public Tipo getTipo() { return tipo; }
	public void setTipo(Tipo tipo) { this.tipo = tipo; }
	public Material getMaterial() { return material; }
	public void setMaterial(Material material) { this.material = material; }
	public String getColorPrimario() { return colorPrimario; }	
	public void setColorPrimario(String colorPrimario) { this.colorPrimario = colorPrimario; }
	public String getColorSecundario() { return colorSecundario; }
	public void setColorSecundario(String colorSecundario) { this.colorSecundario = colorSecundario; }
	public void setDisponible(Boolean disponible) { this.disponible = disponible; }
	public Boolean estaDisponible() { return this.disponible; }
	
	public BufferedImage getImagen() {return imagen;}
	public void setImagen(String imagenPath) {
		try {
			if(imagenPath != null) {
				//TODO configuracion
				ImageResizer.resize(imagenPath, "D:/"+ this.prendaID +".jpg", 400, 400);
			}
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("Ocurrio un error al intentar modificar el tama�o de la imagen");
		}
		
	}

	
	//METODOS

	
	public Boolean esParteSuperior() { return categoria.getCategoria()=="Parte superior"; }
	public Boolean esParteInferior() { return categoria.getCategoria()=="Parte inferior"; }
	public Boolean esCalzado() { return categoria.getCategoria()=="Calzado"; }
	public Boolean esAccesorio() { return categoria.getCategoria()=="Accesorio"; }
	
}
