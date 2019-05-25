package es.urjc.code.daw.seguridad;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginWebController {
	
	@RequestMapping("/login")
	public String listadoArticulos(Model model) {

		return "login";
	}

}
