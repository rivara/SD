package es.urjc.code.daw;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


@Entity
public class Articulo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	private String nombre;
	private String descripcion;
	private int precio;
	private int cantidad;
	
	
	// relacion 1 to 1
	
	@OneToOne (cascade=CascadeType.ALL)
	private Stock stock;
	@ManyToMany(cascade=CascadeType.ALL)
	private List<Compra> articulosEnCompra = new ArrayList<Compra>();
	
	@ManyToMany(cascade = CascadeType.ALL)
	private List<Carrito> articulosEnCarrito = new ArrayList<Carrito>();

	public Articulo () {
	}

	public Articulo (String nombre, String descripcion, int precio, Stock stock, int cantidad) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.precio = precio;
		this.stock = stock;
		this.cantidad = 0;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion (String descripcion) {
		this.descripcion = descripcion;
	}
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio (int precio) {
		this.precio = precio;
	}
	
	public List<Compra> getarticulosEnCompra() {
		return articulosEnCompra;
	}

	public void setArticulosEnPedidos(List<Compra> articulosEnCompra) {
		this.articulosEnCompra = articulosEnCompra;
	}

	public List<Carrito> getArticulosEnCarrito() {
		return articulosEnCarrito;
	}

	public void setArticulosEnCarrito(List<Carrito> articulosEnCarrito) {
		this.articulosEnCarrito = articulosEnCarrito;
	}

	@Override
	public String toString() {
		return "Articulo [id=" + id + ", nombre=" + nombre + ", descripcion=" + descripcion + ", precio=" + precio + 
				",+ stock=" + stock +"]";
	}


}
