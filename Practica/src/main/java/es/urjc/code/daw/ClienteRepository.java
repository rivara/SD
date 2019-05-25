
package es.urjc.code.daw;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;



	@CacheConfig(cacheNames="web")
	public interface ClienteRepository extends JpaRepository<Cliente, Long>{
		
		@CacheEvict(allEntries=true)	
		Cliente save(Cliente cliente);
		
		@Cacheable
		Cliente findByEmail(String email);
		
		Cliente findByNombre(String nombre);
}

