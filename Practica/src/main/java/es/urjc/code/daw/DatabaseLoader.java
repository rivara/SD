package es.urjc.code.daw;

import javax.annotation.PostConstruct;

//import org.apache.catalina.startup.ClassLoaderFactory.Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
	public class DatabaseLoader {
	
 @Autowired
 	private ClienteRepository clientes;
 
 @Autowired
	private ArticuloRepository articulo;
 
 @PostConstruct
 	private void init() {
	 
		articulo.save(new Articulo ("TARJETA GRAFICA"," 8G 8GB GDDR5",100,new Stock(18),0));
		articulo.save(new Articulo ("TECLADO INALAMBRICO","bat Litio",200,new Stock(112),0));
		articulo.save(new Articulo ("TARJETA GRAF"," 8G 8GB GDDR5",90,new Stock(23),0));
		articulo.save(new Articulo ("TECLADO ","bat Litio",200,new Stock(12),0));
		articulo.save(new Articulo ("GRAFICA"," 8G 8GB GDDR5",100,new Stock(1),0));
		articulo.save(new Articulo ("INALAMBRICO","bat Litio",200,new Stock(22),0));

		clientes.save(new Cliente("Miguel","Miguel","Miguel","miguel@gmail","1234","ROLE_USER"));
		clientes.save(new Cliente("Dani","Dani","Dani","Dani@gmail.com","1234","ROLE_USER"));
		clientes.save(new Cliente("Admin","Admin","Admin","admin@gmail","1234","ROLE_ADMIN"));
		clientes.save(new Cliente("Ricardo","Ricardo","Ricardo","ricardo@gmail","1234","ROLE_ADMIN"));
	 }
}


