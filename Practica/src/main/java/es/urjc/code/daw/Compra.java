package es.urjc.code.daw;

import java.io.Serializable;
import java.sql.Timestamp;
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

import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;


@Entity
public class Compra implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	
	private long id;
	//private long idCliente;
	//private int cantidad;
	//private Articulo articulo;
	//private List<Integer> cantidades;
	//private List<Articulo> articulos;

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
