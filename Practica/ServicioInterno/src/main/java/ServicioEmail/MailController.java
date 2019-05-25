package ServicioEmail;



import java.security.Security;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.catalina.Host;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//Anotaciones
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.sun.mail.smtp.SMTPTransport;





@RestController
public class MailController {
	
	@Autowired
	
	private JavaMailSender javaMailSender;
	
	
	//Email y contraseña para usuario
	private final String username = "masqueproductosinformaticos.com"; //Ejemplo
	//password = 1234
	
	//Get mapping para indicar la url donde se envia el correo
	@PostMapping(value = "/mail/")
	public ResponseEntity <Boolean> sendMail (@RequestBody Email mail){
		
		String name = mail.getName();
		String email = mail.getEmail();
		
		System.out.println("Datos correctamente recibidos!");
		System.out.println("Nombre: " + name + "  Email: " + email);
		
		try {
//			Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
//			final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
			
			
//			Properties prop_pass  = System.getProperties(); //Coge la contraseña de app properties
			
//			Session new_session = Session.getInstance(prop_pass, null); //Crea la sesion con la contraseña que hemos cogido de las properties
			//Creamos mensaje de tipo MIME, formato para mensajes en SMTP
			MimeMessage message = javaMailSender.createMimeMessage();
			MimeMessageHelper helper;
			
			helper = new MimeMessageHelper(message,true);
			
//			message.setFrom(new InternetAddress(username));
			helper.setTo(email); //Indicamos donde se dirige el email (TO)
			helper.setSubject("Mas que productos inforamticos");
			helper.setText("Su cuenta se ha realizado con exito para el correo " + email + " podra acceder a nuestros servicios de compra con el usuario y contraseña que haya metido, que lo disfrute " +name+ "!");
			
			
			
			//Uso de SMTP para enviar el correo
		//	SMTPTransport sending = (SMTPTransport) new_session.getTransport("smtps");
//			sending.connect(System.getProperties(Host),spring.mail.username, "password");
	//		sending.sendMessage(message,  message.getAllRecipients());
//			sending.close();
			
			javaMailSender.send(message);
			
		}
		
		catch  (MessagingException exception) {
			System.out.println(exception);
		}	
		
		return new ResponseEntity <Boolean> (true, HttpStatus.OK);
	}

}
