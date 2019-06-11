package es.urjc.code.daw;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.data.jpa.repository.JpaRepository;

	public interface CompraRepository extends JpaRepository<Compra, Long>{
		
	
		Compra save(Compra compra);

	}