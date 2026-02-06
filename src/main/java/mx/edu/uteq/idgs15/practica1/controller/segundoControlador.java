package mx.edu.uteq.idgs15.practica1.controller;

import java.util.List;
import java.util.Map;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class segundoControlador {
    @GetMapping("/segundo")
    public String segundo(Model model) {

        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"),
                Map.of("nombre", "Segundo", "url", "/segundo"));

        model.addAttribute("rutas", rutas);
        return "segundo";
    }
}
