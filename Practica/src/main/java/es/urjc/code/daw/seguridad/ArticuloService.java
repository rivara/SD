package es.urjc.code.daw.seguridad;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.urjc.code.daw.Articulo;
import es.urjc.code.daw.ArticuloRepository;


@Service
public class ArticuloService {

	@Autowired
	private ArticuloRepository repository;

	public Articulo findOne(long id) {
		return repository.findOne(id);
	}

	public List<Articulo> findAll() {
		return repository.findAll();
	}

	public void save(Articulo articulo1) {
		repository.save(articulo1);
	}

	public void delete(long id) {
		repository.delete(id);
	}
}
