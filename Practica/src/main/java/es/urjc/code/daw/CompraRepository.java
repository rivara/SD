package es.urjc.code.daw;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="web")
	public interface CompraRepository extends JpaRepository<Compra, Long>{
		
		@CacheEvict(allEntries=true)	
		Compra save(Compra compra);

	}