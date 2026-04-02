package mx.edu.uteq.idgs15.practica1.controller;

import mx.edu.uteq.idgs15.practica1.model.MisionVisionValores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mx.edu.uteq.idgs15.practica1.repository.MisionRepository;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import java.util.Map;

@Controller
public class MisionController {

    @Autowired
    private MisionRepository repo;

    @GetMapping({ "/mision-vision-valores", "/mision-vision-valores/" })
    public String listarMisionVisionValores(Model model) {
        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"),
                Map.of("nombre", "Misión, Visión y Valores", "url", "/mision-vision-valores"));
        model.addAttribute("rutas", rutas);

        MisionVisionValores datos = repo.findAll().stream()
                .findFirst()
                .orElse(new MisionVisionValores());

        model.addAttribute("misionVisionValores", datos);
        return "mision-vision-valores";
    }

    @GetMapping("/consola/mision-vision-valores/form")
    public String listarMisionVisionValoresForm(Model model) {
        List<Map<String, String>> rutas = List.of(
                Map.of("nombre", "Home", "url", "/"),
                Map.of("nombre", "Misión, Visión y Valores", "url", "/consola/mision-vision-valores"),
                Map.of("nombre", "Crud", "url", "/consola/mision-vision-valores/form"));
        model.addAttribute("rutas", rutas);
        model.addAttribute("misionVisionValoresList", repo.findAll());
        // Cargar el primer registro existente o uno nuevo si no hay
        MisionVisionValores valores = repo.findAll().stream().findFirst().orElse(new MisionVisionValores());
        model.addAttribute("misionVisionValores", valores);
        model.addAttribute("formAction", "/consola/mision-vision-valores/crear");
        return "mision-vision-valores-form";
    }

    @PostMapping("/consola/mision-vision-valores/crear")
    public String crearMisionVisionValores(@Valid MisionVisionValores misionVisionValores,
            Errors errores, Model model) {
        if (errores.hasErrors()) {
            List<Map<String, String>> rutas = List.of(
                    Map.of("nombre", "Home", "url", "/"),
                    Map.of("nombre", "Misión, Visión y Valores", "url", "/consola/mision-vision-valores"),
                    Map.of("nombre", "Crud", "url", "/consola/mision-vision-valores/form"));
            model.addAttribute("rutas", rutas);
            model.addAttribute("misionVisionValoresList", repo.findAll());
            model.addAttribute("misionVisionValores", misionVisionValores);
            model.addAttribute("formAction", "/consola/mision-vision-valores/crear");
            return "mision-vision-valores-form";
        }
        repo.save(misionVisionValores);
        return "redirect:/mision-vision-valores";
    }

}
