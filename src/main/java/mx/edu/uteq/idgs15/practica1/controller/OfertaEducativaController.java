package mx.edu.uteq.idgs15.practica1.controller;

import mx.edu.uteq.idgs15.practica1.model.Division;
import mx.edu.uteq.idgs15.practica1.model.OfertaEducativa;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import mx.edu.uteq.idgs15.practica1.repository.DivisionesRepository;
import mx.edu.uteq.idgs15.practica1.repository.OfertaEducativaRepository;
import jakarta.validation.Valid;

import java.util.List;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import java.util.Map;

@Controller
public class OfertaEducativaController {

        List<OfertaEducativa> ofertas = new ArrayList<>();

        @Autowired
        private OfertaEducativaRepository repo;

        @Autowired
        private DivisionesRepository repoDivision;

        @GetMapping("/oferta-educativa")
        public String listarOfertas(Model model) {
                List<Map<String, String>> rutas = List.of(
                                Map.of("nombre", "Home", "url", "/"),
                                Map.of("nombre", "Oferta Educativa", "url", "/consola/oferta-educativa"));
                model.addAttribute("rutas", rutas);
                model.addAttribute("ofertas", repo.findAll());
                model.addAttribute("ofertaEducativa", new OfertaEducativa());
                // Endpoint para editar oferta educativa temporalmente (solo en memoria, para
                // DOM)

                return "oferta-educativa";
        }

        @GetMapping("/consola/oferta-educativa/form")
        public String listarOfertasForm(Model model) {
                List<Map<String, String>> rutas = List.of(
                                Map.of("nombre", "Home", "url", "/"),
                                Map.of("nombre", "Oferta Educativa", "url", "/consola/oferta-educativa"),
                                Map.of("nombre", "Crud", "url", "/consola/oferta-educativa/form"));
                model.addAttribute("rutas", rutas);
                model.addAttribute("ofertas", repo.findAll());
                model.addAttribute("divisiones", repoDivision.findAll());
                model.addAttribute("ofertaEducativa", new OfertaEducativa());
                model.addAttribute("formAction", "/consola/oferta-educativa/crear");
                return "oferta-educativa-form";
        }

        @PostMapping("/consola/oferta-educativa/form")
        public String crearOfertaEducativa(@Valid OfertaEducativa ofertaEducativa, Model model, Errors errors) {
                if (errors.hasErrors()) {
                        model.addAttribute("ofertas", repo.findAll());
                        model.addAttribute("divisiones", repoDivision.findAll());
                        return "oferta-educativa-form";
                }
                repo.save(ofertaEducativa);
                return "redirect:/consola/oferta-educativa-form";
        }

        @PostMapping("/consola/oferta-educativa/crear")
        public String crearOfertaEducativa2(@Valid OfertaEducativa ofertaEducativa, Model model, Errors errors) {
                if (errors.hasErrors()) {
                        model.addAttribute("ofertas", repo.findAll());
                        model.addAttribute("divisiones", repoDivision.findAll());
                        return "oferta-educativa-form";
                }
                repo.save(ofertaEducativa);
                return "redirect:/consola/oferta-educativa/form";
        }

        @GetMapping("/consola/oferta-educativa/editar/{id}")
        public String mostrarEditarOferta(@org.springframework.web.bind.annotation.PathVariable Long id, Model model) {
                OfertaEducativa oferta = repo.findById(id).orElse(null);
                List<Map<String, String>> rutas = List.of(
                                Map.of("nombre", "Home", "url", "/"),
                                Map.of("nombre", "Oferta Educativa", "url", "/consola/oferta-educativa"),
                                Map.of("nombre", "Editar", "url", "/consola/oferta-educativa/editar/" + id));
                model.addAttribute("rutas", rutas);
                model.addAttribute("ofertas", repo.findAll());
                model.addAttribute("ofertaEducativa", oferta);
                model.addAttribute("divisiones", repoDivision.findAll());
                model.addAttribute("titulo", "Editar Oferta Educativa");
                model.addAttribute("formAction", "/consola/oferta-educativa/editar/" + id);
                return "oferta-educativa-form";
        }

        @PostMapping("/consola/oferta-educativa/editar-temporal/{id}")
        public String editarOfertaEducativaTemporal(@org.springframework.web.bind.annotation.PathVariable Long id,
                        @Valid OfertaEducativa ofertaEducativa, Model model, Errors errors) {
                if (errors.hasErrors()) {
                        model.addAttribute("ofertas", repo.findAll());
                        model.addAttribute("divisiones", repoDivision.findAll());
                        return "oferta-educativa-form";
                }
                // Buscar la oferta en la lista temporal
                OfertaEducativa original = null;
                for (OfertaEducativa o : ofertas) {
                        if (o.getId().equals(id)) {
                                original = o;
                                break;
                        }
                }
                if (original != null) {
                        original.setNombreOferta(ofertaEducativa.getNombreOferta());
                        original.setModalidad(ofertaEducativa.getModalidad());
                        original.setImagen(ofertaEducativa.getImagen());
                        // Actualizar la división correctamente
                        if (ofertaEducativa.getDivision() != null && ofertaEducativa.getDivision().getId() != null) {
                                Division nuevaDivision = null;
                                List<Division> divisiones = repoDivision.findAll();
                                for (Division d : divisiones) {
                                        if (d.getId().equals(ofertaEducativa.getDivision().getId())) {
                                                nuevaDivision = d;
                                                break;
                                        }
                                }
                                original.setDivision(nuevaDivision);
                        }
                        // Guardar el cambio en la base de datos
                        repo.save(original);
                }
                // Sincronizar la lista temporal con la base de datos
                ofertas.clear();
                ofertas.addAll(repo.findAll());
                model.addAttribute("ofertas", ofertas);
                model.addAttribute("divisiones", repoDivision.findAll());
                return "oferta-educativa-form";
        }

        // @PostMapping("/consola/oferta-educativa/editar/{id}")
        // public String
        // editarOfertaEducativa(@org.springframework.web.bind.annotation.PathVariable
        // Long id,
        // @Valid OfertaEducativa ofertaEducativa, Model model, Errors errors) {
        // if (errors.hasErrors()) {
        // model.addAttribute("ofertas", repo.findAll());
        // model.addAttribute("divisiones", repoDivision.findAll());
        // return "oferta-educativa-form";
        // }
        // ofertaEducativa.setId(id);
        // repo.save(ofertaEducativa);
        // return "redirect:/consola/oferta-educativa-form";
        // }

        @PostMapping("/consola/oferta-educativa/eliminar/{id}")
        public String eliminarOfertaEducativa(@org.springframework.web.bind.annotation.PathVariable Long id) {
                repo.deleteById(id);
                return "redirect:/consola/oferta-educativa-form";
        }

}
