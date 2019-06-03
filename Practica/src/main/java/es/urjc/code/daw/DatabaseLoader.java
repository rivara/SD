package es.urjc.code.daw;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DatabaseLoader {
	
	@Autowired
		private ClienteRepository clientes;
	 @Autowired
		private ArticuloRepository articulo;
	
	@PostConstruct
	private void initDataBase(){
	/*	articulo.save(new Articulo ("TARJETA GRAFICA"," 8G 8GB GDDR5",100,3));
		articulo.save(new Articulo ("TECLADO INALAMBRICO","bat Litio",200,34));
		articulo.save(new Articulo ("TARJETA GRAF"," 8G 8GB GDDR5",90,12));
		articulo.save(new Articulo ("TECLADO ","bat Litio",200,22));
		articulo.save(new Articulo ("GRAFICA"," 8G 8GB GDDR5",12,1));
		articulo.save(new Articulo ("INALAMBRICO","bat Litio",200,0));*/

/*
		clientes.save(new Cliente("Dani","Dani1","Dani2","Dani@gmail.com","1234","ROLE_USER"));
		clientes.save(new Cliente("Admin","Admin1","Admin2","admin@gmail","admin","ROLE_ADMIN"));*/
	}
}
