package mx.edu.uteq.idgs15.practica1.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;

import org.thymeleaf.context.Context;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import mx.edu.uteq.idgs15.practica1.dto.EmailDTO;

import org.springframework.beans.factory.annotation.Value;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${spring.mail.username}")
    private String sender;

    public void enviarHtmlEmail(EmailDTO dto) throws MessagingException {

        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        Context context = new Context();
        context.setVariable("nombre", dto.getAsunto());
        context.setVariable("mensaje", dto.getMensaje());
        context.setVariable("codigo", (int) (Math.random() * 9000) + 1000);

        String htmlContent = templateEngine.process("emailTemplate", context);

        helper.setTo(dto.getDestinatario());
        helper.setSubject("Notificación especial!");
        helper.setText(htmlContent, true);

        mailSender.send(message);
    }

    public void enviarEmailSimple(String destinatario, String asunto, String mensaje) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);

        helper.setTo(destinatario);
        helper.setSubject(asunto);
        helper.setText(mensaje, false);

        mailSender.send(message);
    }

}
