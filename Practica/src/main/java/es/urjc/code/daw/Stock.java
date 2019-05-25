package es.urjc.code.daw;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import java.util.List;

@Entity
public class Stock {

	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	private Integer stock_producto;



	@OneToOne (mappedBy = "stock")
	private Articulo articulo;
	
	public Stock() {
	}

	public Stock (int stock_producto) {
		this.stock_producto = stock_producto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getStock_Producto() {
		return stock_producto;
	}

	public void setStock_Producto(int stock_producto) {
		this.stock_producto = stock_producto;
	}


	
/*
	@Override
	public String toString() {
		return "Stock [id=" + id + ", stock_producto=" + stock_producto + "]";
	}*/
	
}