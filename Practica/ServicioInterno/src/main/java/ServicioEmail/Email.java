package ServicioEmail;

public class Email{
	
	public String nombre;
	public String Email;
	public Email() {};
	
	public Email(String nombre, String email) {
		super();
		this.nombre = nombre;
		Email = email;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}
	
	
	
	
}