package es.urjc.code.daw;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;




public interface CarritoRepository extends JpaRepository <Carrito, Long>{
	
	
	Carrito save(Carrito carrito);
	

	Carrito findById(long id);
	
}
