package es.urjc.code.daw;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;

@CacheConfig(cacheNames="web")
public interface ArticuloRepository extends JpaRepository<Articulo, Long>{

	@CacheEvict(allEntries=true)
	Articulo save(Articulo articulo);
	
	@Cacheable
	Articulo findByNombre(String nombre);
	
	@Cacheable
	List<Articulo> findByDescripcion(String descripcion);
	
	@Cacheable
	List<Articulo> findByArticulosEnCarrito(Carrito carrito);
	
}

