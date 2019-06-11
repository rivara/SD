
package es.urjc.code.daw;
import org.springframework.data.jpa.repository.JpaRepository;







	public interface ClienteRepository extends JpaRepository<Cliente, Long>{
		
		
		Cliente save(Cliente cliente);
		
		Cliente findByNameAndPassword(String name, String password);
		
		Cliente findByName(String name);

		Cliente findByEmail(String attribute);
}

