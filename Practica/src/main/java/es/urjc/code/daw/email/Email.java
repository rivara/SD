package es.urjc.code.daw.email;

public class Email {
	
    private String remitente;
    private String destinatario;
    private String asunto;
    private String texto;

    public Email() {
    }

    public Email(String remitente, String destinatario, String asunto, String texto) {
        this.remitente = remitente;
        this.destinatario = destinatario;
        this.asunto = asunto;
        this.texto = texto;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }

    public String getDestinatario() {
        return destinatario;
    }

    public void setDestinatario(String destinatario) {
        this.destinatario = destinatario;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    @Override
    public String toString() {
        return "Email{" +
                "remitente='" + remitente + '\'' +
                ", destinatario='" + destinatario + '\'' +
                ", asunto='" + asunto + '\'' +
                ", texto='" + texto + '\'' +
                '}';
    }
}

