package es.urjc.code.daw;
import java.util.List;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ArticuloRepository extends JpaRepository<Articulo, Long>{


	Articulo save(Articulo articulo);
	

	Articulo findByNombre(String nombre);
	

	List<Articulo> findByDescripcion(String descripcion);
	

	List<Articulo> findByArticulosEnCarrito(Carrito carrito);
	
}

