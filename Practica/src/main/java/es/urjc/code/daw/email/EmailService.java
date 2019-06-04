package es.urjc.code.daw.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
	
    @Autowired
    private JavaMailSender emailSender;

    public void sendSimpleMessage(final Email email){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(email.getAsunto());
        message.setText(email.getTexto());
        message.setTo(email.getDestinatario());
        message.setFrom(email.getRemitente());

        emailSender.send(message);
    }

}