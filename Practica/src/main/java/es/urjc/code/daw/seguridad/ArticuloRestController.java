package es.urjc.code.daw.seguridad;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import es.urjc.code.daw.Articulo;



@RestController
@RequestMapping("/api/principal")
public class ArticuloRestController {

	@Autowired
	private ArticuloService service;

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public Collection<Articulo> getArticulos() {
		return service.findAll();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Articulo> conseguirArticulo(@PathVariable long id) {

		Articulo articulo1 = service.findOne(id);
		if (articulo1 != null) {
			return new ResponseEntity<>(articulo1, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public Articulo createArticulo(@RequestBody Articulo articulo1) {

		service.save(articulo1);

		return articulo1;
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Articulo> modificarArticulo(@PathVariable long id, @RequestBody Articulo modificarArticulo) {

		Articulo articulo1 = service.findOne(id);
		if (articulo1 != null) {

			modificarArticulo.setId(id);
			service.save(modificarArticulo);

			return new ResponseEntity<>(modificarArticulo, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Articulo> borrarArticulo(@PathVariable long id) {

		try {

			service.delete(id);
			return new ResponseEntity<>(null, HttpStatus.OK);

		} catch (EmptyResultDataAccessException e) {
			return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
		}
	}

}
