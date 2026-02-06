package mx.edu.uteq.idgs15.practica1.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class InicioController {

    @GetMapping("/")
    public String inicio(Model model) {

        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"));

        model.addAttribute("rutas", rutas);

        return "index";
    }

}
