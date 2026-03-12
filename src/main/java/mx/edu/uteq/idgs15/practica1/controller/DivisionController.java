package mx.edu.uteq.idgs15.practica1.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import mx.edu.uteq.idgs15.practica1.model.Division;
import mx.edu.uteq.idgs15.practica1.model.OfertaEducativa;
import mx.edu.uteq.idgs15.practica1.repository.DivisionesRepository;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import java.util.Map;

@Controller
public class DivisionController {
    // Endpoint para editar division temporalmente (solo en memoria, para DOM)

    List<Map<String, String>> rutas = List.of(
            Map.of("nombre", "Home", "url", "/"),
            Map.of("nombre", "Division", "url", "/consola/division"),
            Map.of("nombre", "Crud", "url", "/consola/division/form"));

    @Autowired
    private DivisionesRepository repo;

    List<Division> divisiones = new ArrayList<>();

    @GetMapping("/consola/division/form")
    public String listarDivisiones(Model model) {
        if (divisiones.isEmpty()) {
            divisiones.addAll(repo.findAll()); // cargar solo una vez
        }

        model.addAttribute("rutas", rutas);
        model.addAttribute("divisiones", repo.findAll());
        model.addAttribute("division", new Division());
        return "division-form";
    }

    // @PostMapping("/consola/division/editar-temporal/{id}")
    // public String editarDivisionTemporal(@Valid Division division, Model model,
    // Errors errors,
    // @PathVariable("id") Long id) {
    // if (errors.hasErrors()) {
    // model.addAttribute("divisiones", repo.findAll());
    // return "division-form";
    // }
    // // Buscar la división en la lista temporal
    // Division original = null;
    // for (Division d : divisiones) {
    // if (d.getId().equals(id)) {
    // original = d;
    // break;
    // }
    // }
    // if (original != null) {
    // original.setNombre(division.getNombre());
    // original.setDescripcion(division.getDescripcion());
    // original.setClave(division.getClave());
    // original.setActivo(division.isActivo());
    // // Guardar el cambio en la base de datos
    // repo.save(original);
    // }
    // // Sincronizar la lista temporal con la base de datos
    // divisiones.clear();
    // divisiones.addAll(repo.findAll());
    // model.addAttribute("divisiones", divisiones);
    // return "division-form";
    // }

    @PostMapping("/consola/division/editar-temporal/{id}")
    public String editarDivisionTemporal(
            @PathVariable Long id,
            @Valid Division division,
            Model model,
            Errors errors) {

        if (errors.hasErrors()) {
            model.addAttribute("divisiones", divisiones);
            return "division-form";
        }

        for (Division d : divisiones) {
            if (d.getId().equals(id)) {
                d.setNombre(division.getNombre());
                d.setClave(division.getClave());
                d.setActivo(division.isActivo());
                break;
            }
        }

        model.addAttribute("divisiones", divisiones);
        model.addAttribute("division", new Division());

        return "division-form";
    }

    @PostMapping("/consola/division/form")
    public String crearDivision(@Valid Division division, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("divisiones", repo.findAll());
            return "division-form";
        }
        repo.save(division);
        return "redirect:/consola/division/form";
    }

    @GetMapping("/consola/division/editar/{id}")
    public String mostrarEditarDivision(@PathVariable("id") Long id, Model model) {
        Division division = repo.findById(id).orElse(null);
        model.addAttribute("rutas", rutas);
        model.addAttribute("divisiones", repo.findAll());
        model.addAttribute("division", division);
        return "division-form";
    }

    @PostMapping("/consola/division/editar/{id}")
    public String editarDivision(@Valid Division division, Model model, Errors errors, @PathVariable("id") Long id) {
        if (errors.hasErrors()) {
            model.addAttribute("divisiones", repo.findAll());
            return "division-form";
        }
        // Recuperar la división original para conservar las ofertas educativas
        // asociadas
        Division original = repo.findById(id).orElse(null);
        if (original != null) {
            division.setProgramasEducativos(original.getProgramasEducativos());
        }
        repo.save(division);
        return "redirect:/consola/division/form";
    }

    @PostMapping("/consola/division/eliminar/{id}")
    public String eliminarDivision(@org.springframework.web.bind.annotation.PathVariable("id") Long id) {
        repo.deleteById(id);
        return "redirect:/consola/division/form";
    }

    public String eliminarDivision(@Valid Division division, Model model, Errors errors) {
        if (errors.hasErrors()) {
            model.addAttribute("divisiones", repo.findAll());
            return "division-form";
        }
        repo.delete(division);
        return "redirect:/consola/division/form";
    }

}
