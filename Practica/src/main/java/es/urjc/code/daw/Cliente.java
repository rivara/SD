package es.urjc.code.daw;


import java.util.ArrayList;

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


import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity

public class Cliente {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;	
	private String name;
	private String primerApellido;
	private String segundoApellido;
	@JsonIgnore
	private String password;
	private String email;

	@OneToMany(mappedBy="cliente", cascade = CascadeType.ALL)
	private List<Compra> listaCompras = new ArrayList<>();
	
	@OneToOne(cascade = CascadeType.ALL)
	private Carrito carrito;
	
	
	@ElementCollection(fetch = FetchType.EAGER)
	private List<String> rol;
	  
	public Cliente (){
	}
	
	public Cliente (String name,String primerApellido, String segundoApellido,String email, String password, String rol) {
		this.name = name;
		this.primerApellido = primerApellido;
		this.segundoApellido = segundoApellido;
		this.password = new BCryptPasswordEncoder().encode(password);
		this.email = email;
		this.rol = new ArrayList<String>();
		if(rol!=null){
			this.rol.add(rol);
		}
		this.carrito = new Carrito();
	}
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName () {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	

	public String getPasswordHash() {
		return password;
	}

	public void setPasswordHash(String password) {
		this.password = password;
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
	

    public List<Compra> getListaCompras() {
        return listaCompras;
    }
    
    public void setListaCompras(List<Compra> listaCompras) {
        this.listaCompras = listaCompras;
    } 
    
	public Carrito getCarrito() {
		return carrito;
	}

	public void setCarrito(Carrito carrito) {
		this.carrito = carrito;
	}
	
	public List<String> getRol() {
		return rol;
	}

	public void setRol(List<String> rol) {
		this.rol = rol;
	}

	@Override
	public String toString() {
		return "Usuario [name=" + name + ", primerApellido=" + primerApellido + ", segundoApellido="
				+ segundoApellido + ", email=" + email +  "]";
	}
	

}
