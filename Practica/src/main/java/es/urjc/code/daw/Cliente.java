package es.urjc.code.daw;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import com.fasterxml.jackson.annotation.JsonIgnore;




@Entity

public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String nombre;
	private String primerApellido;
	private String segundoApellido;
	@JsonIgnore
	private String passwordHash;
	private String email;

	// relacion 1 to n
	@OneToOne(cascade = CascadeType.ALL)
	private Carrito carrito;
	
	@OneToMany (cascade = CascadeType.ALL)
	private List<Compra> listaCompras = new ArrayList<>();

	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> roles;
	  
	public Cliente (){
	}
	
	public Cliente (String nombre,String primerApellido, String segundoApellido,String email, String password, String... roles) {
		this.nombre = nombre;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.passwordHash = new BCryptPasswordEncoder().encode(password);
		this.email = email;
		this.roles = new ArrayList<>(Arrays.asList(roles));
		this.carrito = new Carrito();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

	public String getPasswordHash() {
		return passwordHash;
	}

	public void setPasswordHash(String passwordHash) {
		this.passwordHash = passwordHash;
	}
	
	public String getPrimerApellido() {
		return primerApellido;
	}
	
	public void setPrimerApellido(String primerApellido) {
		this.primerApellido = primerApellido;
	}
	
	public String getSegundoApellido() {
		return segundoApellido;
	}
	
	public void setSegundoApellido(String segundoApellido) {
		this.segundoApellido = segundoApellido;
	}

	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	

    public List<Compra> getListaCompra() {
        return listaCompras;
    }
    
    public void setListaCompra(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    } 
    
	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "Usuario [nombre=" + nombre + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", email=" + email +  "]";
	}
	

}
