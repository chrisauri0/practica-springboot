package mx.edu.uteq.idgs15.practica1.controller;

import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Map;

import mx.edu.uteq.idgs15.practica1.model.OfertaEducativa;

import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.ui.Model;
import java.util.ArrayList;

@Controller
public class OfertaEducativaForm {
    List<OfertaEducativa> ofertas = new ArrayList<>();

    @GetMapping("/oferta-educativa/form")
    public String getOfertaEducativaForm(Model model) {
        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"),
                Map.of("nombre", "Oferta Educativa", "url", "/oferta-educativa-form"),
                Map.of("nombre", "Formulario", "url", "/oferta-educativa/form"));
        model.addAttribute("rutas", rutas);
        model.addAttribute("ofertas", ofertas);
        model.addAttribute("ofertaEducativa", new OfertaEducativa());
        return "oferta-educativa-form";
    }

}
