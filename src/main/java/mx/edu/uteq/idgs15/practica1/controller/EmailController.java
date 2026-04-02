package mx.edu.uteq.idgs15.practica1.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.Random;
import mx.edu.uteq.idgs15.practica1.dto.EmailDTO;
import mx.edu.uteq.idgs15.practica1.service.EmailService;
import org.springframework.ui.Model;
import mx.edu.uteq.idgs15.practica1.service.UsuarioMemoriaService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private UsuarioMemoriaService usuarioMemoriaService;

    @Autowired

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @GetMapping("/email/form")
    public String Form(Model model, EmailDTO emailDTO) {
        model.addAttribute("email", emailDTO);

        return "emailForm";
    }

    @Async
    @GetMapping("/email/enviar")
    public String page(@ModelAttribute EmailDTO emailDTO) {

        try {
            emailService.enviarHtmlEmail(emailDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return "redirect:/";
    }

    @GetMapping("/recuperar-contraseña")
    public String recuperarContraseña() {
        return "recuperarContrasena";
    }

    @GetMapping("/nueva-contrasena")
    public String nuevaContrasena() {
        return "nuevaContrasena";
    }

    @PostMapping("/recuperar-contraseña")
    public String enviarCodigoRecuperacion(@RequestParam("email") String email, RedirectAttributes redirectAttributes) {
        // Generar código de 6 dígitos
        String codigo = String.format("%06d", new Random().nextInt(999999));

        // Crear el mensaje
        String asunto = "Recuperación de contraseña";
        String mensaje = "Tu código de recuperación es: " + codigo;

        // Enviar email y guardar código
        try {
            emailService.enviarEmailSimple(email, asunto, mensaje);
            usuarioMemoriaService.guardarCodigo(email, codigo);
            redirectAttributes.addFlashAttribute("mensaje", "Se ha enviado un código a tu correo electrónico.");
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("error", "No se pudo enviar el correo. Intenta de nuevo.");
        }

        // Redirige de nuevo al formulario
        return "redirect:/nueva-contrasena";
    }

    @PostMapping("/nueva-contrasena")
    public String guardarNuevaContrasena(@RequestParam("email") String email,
            @RequestParam("codigo") String codigo,
            @RequestParam("nuevaContrasena") String nuevaContrasena,
            RedirectAttributes redirectAttributes) {
        if (!usuarioMemoriaService.validarCodigo(email, codigo)) {
            redirectAttributes.addFlashAttribute("error", "Código incorrecto o expirado.");
            return "redirect:/nueva-contrasena";
        }
        // Guardar usuario en memoria (contraseña cifrada)
        usuarioMemoriaService.guardarUsuario(email, passwordEncoder.encode(nuevaContrasena));
        redirectAttributes.addFlashAttribute("mensaje", "Contraseña actualizada. Ya puedes iniciar sesión.");
        return "redirect:/login";
    }

}
