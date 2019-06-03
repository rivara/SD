package es.urjc.code.daw;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


import es.urjc.code.daw.seguridad.ArticuloService;

@Controller
public class MainController {
	
	/*INSTANCIA LAS CLASES*/
	@Autowired
	private  ArticuloRepository articulo;
	@Autowired
	private  ClienteRepository cliente;
	@Autowired
	private  CarritoRepository carrito;
	@Autowired
	private  CompraRepository compra;
	@Autowired
	private ArticuloService service;	

		
	@GetMapping("/")
	public String principal() {
		
		return "principal";
	}
	
	@GetMapping("/loggin")
	public String logginCliente(){
		return "loggin_cliente";
	}
	
	
	@GetMapping("/tienda")
	public String tienda (Model model,HttpServletRequest request, HttpSession sesion){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		sesion = request.getSession();
		sesion.setAttribute("name", currentPrincipalName);
		model.addAttribute("articulos", articulo.findAll());
		model.addAttribute("admin",request.isUserInRole("ADMIN"));
		return "tienda";
	}
	
	@PostMapping("/registrar_cliente")
	public String registrarCliente(Model model){
		return "nuevoCliente";
	}
	
	@PostMapping("/cliente/nuevo")
	public String ClienteNuevo (Model model, Cliente cliente1, HttpSession sesion){

		//Guardo el cliente creado
		sesion.setAttribute("name", cliente1.getName());
		List<String> rol = new ArrayList<String>();
		rol.add("ROLE_USER");
		cliente1.setRol(rol);
		
		//Puente ya que el constructor de la clase no me lo hace
		Carrito carrito = new Carrito();
		cliente1.setCarrito(carrito);
		String password = cliente1.getPasswordHash();
		//cliente1.setPasswordHash(new BCryptPasswordEncoder().encode(password));
		cliente1.setPasswordHash(password);
		cliente.save(cliente1);
		return "cliente_registrado";
	}
		
		@PostMapping("/nuevo_articulo")
		public String añadirArticulo(){
			return "nuevoArticulo";
		}
		
		@PostMapping("/articulo/nuevo")
		public String nuevoArticulo(Model model, Articulo articulo1) {
		
			Articulo articuloEncontrado = articulo.findByNombre(articulo1.getNombre());
			if(articuloEncontrado!=null){
				int cantidad = articuloEncontrado.getCantidad();
				cantidad++;
				articuloEncontrado.setCantidad(cantidad);
				articulo.save(articuloEncontrado);
			}else{
				articulo1.setCantidad(1);
				articulo.save(articulo1);
			}
			
			return "articulo_guardado";
		}
	
		
		@RequestMapping("/articulos/{id}")
		public String verArticulo (Model model, @PathVariable long id,HttpServletRequest request){
			
			Articulo articulo1 = articulo.findOne(id);
			model.addAttribute("articulo", articulo1);
			model.addAttribute("USER",request.isUserInRole("USER"));
			
			return "verArticulo";
		}
		
		@RequestMapping("/eliminar_articulo")
		public String eliminarArticulo(){
			return "eliminarArticulo";
		}

		@RequestMapping("/articulosEliminar/{id}")
		public String verArticulos (Model model, @PathVariable long id,HttpServletRequest request){
			
			Articulo articulo1 = articulo.findOne(id);
			model.addAttribute("articulo", articulo1);
			model.addAttribute("admin",request.isUserInRole("ADMIN"));
			
			return "verArticulo";
		}
		
		@RequestMapping("/articulo/{id}/eliminar")
		public String eliminarArticulo (Model model, @PathVariable long id){
			
			Articulo articulo1 = articulo.findOne(id);
			articulo.delete(articulo1);
			model.addAttribute("articulos", articulo.findAll());
			return "articulo_eliminado";
		}
		
		
		@GetMapping("/carrito")
		public String verCarrito (Model model, HttpSession sesion){
			
			Cliente cliente1 = cliente.findByName((String) sesion.getAttribute("name"));
			model.addAttribute("articulos_carrito",  cliente1.getCarrito().getArticulosCarrito());
			return "carrito";
		}
		
		@GetMapping("/carrito/{num}")
		public String verArticuloCarrito (Model model, @PathVariable int num, HttpSession sesion){
			
			Cliente cliente1 = cliente.findByName((String) sesion.getAttribute("name"));
			model.addAttribute("articulo_carrito", cliente1.getCarrito().getArticulosCarrito().get(num-1));
			
			return "ver_articuloCarrito";
		}
		
		@GetMapping("/articulo/{id}/añadido")
		public String añadirArticulo (Model model, @PathVariable long id, HttpSession sesion){
			
			String resultado = "";
			Articulo articulo1 = articulo.findOne(id);

			Cliente cliente1 = cliente.findByName((String) sesion.getAttribute("name"));
			Carrito carritoCliente = cliente1.getCarrito();
			articulo1.getArticulosEnCarrito().add(carritoCliente);
			int cantidad = articulo1.getCantidad();
			if (cantidad == 0){
				resultado = "producto_no_disponible";
			}else{
				cantidad--;
				articulo1.setCantidad(cantidad);
				articulo.save(articulo1);
				resultado = "articulo_añadido";
			}
			return resultado;
		}
		
		@GetMapping("/carrito/{num}/eliminado")
		public String eliminarArticuloCarrito (Model model, @PathVariable int num, HttpSession sesion){

			Cliente clienteBuscado = cliente.findByName((String) sesion.getAttribute("name"));
			Carrito carrito1 = clienteBuscado.getCarrito();
			Articulo articulo1 = carrito1.getArticulosCarrito().get(num-1);
			articulo1.getArticulosEnCarrito().remove(carrito1);

			int cantidad = articulo1.getCantidad();
			cantidad++;
			articulo1.setCantidad(cantidad);
			articulo.save(articulo1);
			return "articuloCarritoEliminado";
		}
			
		
		@PostMapping("/carrito/comprar")
		public String comprarArticulos(Model model, HttpSession sesion){
			
			Cliente cliente1 =  cliente.findByName((String) sesion.getAttribute("name"));
			Carrito carrito1 = cliente1.getCarrito();
			model.addAttribute("cliente",cliente1);
			model.addAttribute("articulos_carrito", carrito1.getArticulosCarrito());
			return "compra";
		}
			
		
		@PostMapping("/compra")
		public String compra(Model model, HttpSession sesion){
			
			Cliente cliente1 =  cliente.findByName((String) sesion.getAttribute("name"));
			List<Articulo> articulosPedido = articulo.findByArticulosEnCarrito(cliente1.getCarrito());
			Compra compra1 = new Compra();
			compra1.setCliente(cliente1);	
			for(Articulo articulo2: articulosPedido){
				compra1.getArticulosComprados().add(articulo2);
			}
			compra.save(compra1);
				
			Carrito carrito1 = cliente1.getCarrito();
			for(Articulo articulo1: articulosPedido){
				articulo1.getArticulosEnCarrito().remove(carrito1);
				articulo.save(articulo1);
			}
		//	new UsoEmail().enviar(cliente1.getEmail(), "Confirmacion de pedido", "Gracias por comprar en mas que productos informaticos");
				
			return "compra_realizada";
		}
		
		

}
