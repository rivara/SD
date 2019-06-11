package es.urjc.code.daw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;



@Entity
public class Carrito implements Serializable {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    //private long idCompra;
    //private long idArticulo;
    //private int cantidad =1;
    //private Articulo articulo;
    //private Compra compra;

	@ManyToMany(mappedBy="articulosEnCarrito")
	List<Articulo> articulosCarrito = new ArrayList<Articulo>();
	
	@OneToOne(mappedBy="carrito")
	private Cliente cliente;
	
	protected Carrito(){
	
	}
	
	public Carrito (Cliente cliente){
		this.cliente = cliente;
		this.articulosCarrito = new ArrayList<>();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Articulo> getArticulosCarrito() {
		return articulosCarrito;
	}

	public void setArticulosCarrito(List<Articulo> articulosCarrito) {
		this.articulosCarrito = articulosCarrito;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
