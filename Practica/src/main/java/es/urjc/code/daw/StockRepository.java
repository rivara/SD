package es.urjc.code.daw;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.data.jpa.repository.JpaRepository;
	@CacheConfig(cacheNames="web")
	public interface StockRepository extends JpaRepository<Stock, Long>{
}

