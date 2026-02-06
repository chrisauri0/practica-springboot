package mx.edu.uteq.idgs15.practica1.controller;

import org.springframework.ui.Model;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class sitemapController {
    @GetMapping("/sitemap")
    public String getSitemap(Model model) {
        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"),
                Map.of("nombre", "Segundo", "url", "/segundo"),
                Map.of("nombre", "Tercero", "url", "/sitemap    "));

        model.addAttribute("rutas", rutas);
        return "sitemap";
    }
}
