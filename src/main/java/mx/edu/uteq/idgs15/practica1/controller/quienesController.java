package mx.edu.uteq.idgs15.practica1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class quienesController {
    @GetMapping("/quienes")
    public String getQuienes(Model model) {
        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"),
                Map.of("nombre", "Quienes", "url", "/quienes"));

        model.addAttribute("rutas", rutas);
        return "quienes";
    }

}
