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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import es.urjc.code.daw.seguridad.ArticuloService;

@Controller
public class MainController {
	
	/*INSTANCIA LAS CLASES*/
	@Autowired
	private  StockRepository stock;
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
	@Autowired
	private ClienteComponent userComponent;
	
	@ModelAttribute
	public void addAttributes(Model model) {
		
		boolean logged = userComponent.getLoggedUser() != null;
		
		model.addAttribute("logged", logged);
		model.addAttribute("notLogged", !logged);
		
		if(logged){
			model.addAttribute("userName",userComponent.getLoggedUser().getNombre());
			model.addAttribute("admin", userComponent.getLoggedUser().getRoles().contains("ROLE_ADMIN"));
		}
	}
	
		
	@RequestMapping("/")
	public String articulos(Model model) {

		model.addAttribute("articulos", service.findAll());
		
		return "principal";
	}
	/*
	@GetMapping("/")
	public String tienda (Model model,HttpServletRequest request, HttpSession sesion){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String currentPrincipalName = authentication.getName();
		sesion = request.getSession();
		sesion.setAttribute("email", currentPrincipalName);
		model.addAttribute("articulo", articulo.findAll());
		model.addAttribute("admin",request.isUserInRole("ADMIN"));
		return "principal";
	}*/
	/*
		@GetMapping("/login")
		public String loginCliente(Model model){
			return "login";
		}
		*/
		@PostMapping("/registrar_cliente")
		public String RegistrarCliente(Model model){
			return "nuevoCliente";
		}
		
		@PostMapping("/cliente/nuevo")
		public String ClienteNuevo (Model model, Cliente cliente1, HttpSession sesion){
			
			//Guardo el usuario creado
			sesion.setAttribute("email", cliente1.getEmail());
			List<String> rol = new ArrayList<String>();
			rol.add("ROLE_USER");
			cliente1.setRoles(rol);
			Carrito carrito = new Carrito();
			cliente1.setCarrito(carrito);
			String password = cliente1.getPasswordHash();
			cliente1.setPasswordHash(new BCryptPasswordEncoder().encode(password));
			cliente.save(cliente1);
			return "cliente_registrado";
		}
		/*
		@GetMapping("/tienda")
		public String tienda (Model model,HttpServletRequest request, HttpSession sesion){
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			String currentPrincipalName = authentication.getName();
			sesion = request.getSession();
			sesion.setAttribute("email", currentPrincipalName);
			model.addAttribute("articulo", articulo.findAll());
			model.addAttribute("admin",request.isUserInRole("ADMIN"));
			return "tienda";
		}*/
		
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
		public String verArticulos (Model model, @PathVariable long id){
			
			Articulo articulo1 = service.findOne(id);
			model.addAttribute("articulo", articulo1);

			return "verArticulo";
		}	
		/*
		@RequestMapping("/articulos/{id}")
		public String verArticulo (Model model, @PathVariable long id,HttpServletRequest request){
			
			Articulo articulo1 = articulo.findOne(id);
			model.addAttribute("articulo", articulo1);
			model.addAttribute("user",request.isUserInRole("USER"));
			
			return "articulo";
		}	*/
		
		@RequestMapping("/articulo/{id}/eliminar")
		public String eliminarArticulo (Model model, @PathVariable long id){
			
			Articulo articulo1 = articulo.findOne(id);
			articulo.delete(articulo1);
			model.addAttribute("articulos", articulo.findAll());
			return "articulo_eliminado";
		}
		
		
		@GetMapping("/carrito")
		public String verCarrito (Model model, HttpSession sesion){
			
			Cliente cliente1 = cliente.findByEmail((String) sesion.getAttribute("email"));
			model.addAttribute("articulos_carrito",  cliente1.getCarrito().getArticulosCarrito());
			return "carrito";
		}
		
		@GetMapping("/carrito/{num}")
		public String verArticuloCarrito (Model model, @PathVariable int num, HttpSession sesion){
			
			Cliente cliente1 = cliente.findByEmail((String) sesion.getAttribute("email"));
			model.addAttribute("articulo_carrito", cliente1.getCarrito().getArticulosCarrito().get(num-1));
			
			return "ver_articuloCarrito";
		}
		
		@GetMapping("/articulo/{id}/añadido")
		public String añadirArticulo (Model model, @PathVariable long id, HttpSession sesion){
			
			String resultado = "";
			Articulo articulo1 = articulo.findOne(id);

			Cliente cliente1 = cliente.findByEmail((String) sesion.getAttribute("email"));
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

			Cliente clienteBuscado = cliente.findByEmail((String) sesion.getAttribute("email"));
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
			
			Cliente cliente1 =  cliente.findByEmail((String) sesion.getAttribute("email"));
			Carrito carrito1 = cliente1.getCarrito();
			model.addAttribute("usuario",cliente1);
			model.addAttribute("articulos_carrito", carrito1.getArticulosCarrito());
			return "compra";
		}
			
		
		@PostMapping("/compra")
		public String compra(Model model, HttpSession sesion){
			
			Cliente cliente1 =  cliente.findByEmail((String) sesion.getAttribute("email"));
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
			new UsoEmail().enviar(cliente1.getEmail(), "Confirmacion web", "Gracias por comprar en la web");
				
			return "compra_realizada";
		}
		
		/*
		@PostMapping("/registrar_cliente")
		public String registrarUsuario(Model model){
			return "nuevoCliente";
		}*/
		
		/*
		@PostMapping("/nuevo_articulo")
		public String añadirArticulo(){
			return "nuevoArticulo";
		}*/


}
