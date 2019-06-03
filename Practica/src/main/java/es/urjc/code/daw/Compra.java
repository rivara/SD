package es.urjc.code.daw;


import java.util.ArrayList;
import java.util.List;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;



@Entity
public class Compra{

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@ManyToMany
	private List<Articulo> articulosComprados = new ArrayList<Articulo>();
	
	@ManyToOne
	private Cliente cliente;

	public Compra () {
	}
	
	public Compra(List<Articulo> articulosComprados, Cliente cliente){
		this.articulosComprados = articulosComprados;
		this.cliente = cliente;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public List<Articulo> getArticulosComprados() {
		return articulosComprados;
	}

	public void setArticulosComprados(List<Articulo> articulosComprados) {
		this.articulosComprados = articulosComprados;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
}
