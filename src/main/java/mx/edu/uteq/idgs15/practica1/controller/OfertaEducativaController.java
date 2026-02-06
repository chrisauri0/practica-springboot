package mx.edu.uteq.idgs15.practica1.controller;

import java.util.List;
import java.util.Map;

import mx.edu.uteq.idgs15.practica1.model.OfertaEducativa;
import mx.edu.uteq.idgs15.practica1.model.PerfilEgreso;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;

import org.springframework.ui.Model;
import java.util.ArrayList;
import org.springframework.validation.Errors;

@Controller
public class OfertaEducativaController {
        List<OfertaEducativa> ofertas = new ArrayList<>();

        @GetMapping("/oferta-educativa")
        public String getOfertaEducativa(Model model) {

                List<Map<String, String>> rutas = List.of(
                                Map.of("nombre", "Home", "url", "/"),
                                Map.of("nombre", "Oferta Educativa", "url", "/oferta-educativa"));
                model.addAttribute("rutas", rutas);

                // if (ofertas.isEmpty()) {
                // OfertaEducativa oferta1 = new OfertaEducativa();
                // oferta1.setId("1");
                // oferta1.setNombreOferta("Ingeniería Mecatrónica");
                // oferta1.setModalidad("Modalidad intensiva y mixta");
                // oferta1.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/ME.png");
                // oferta1.setPerfilEgreso(new PerfilEgreso());

                // ofertas.add(oferta1);

                // OfertaEducativa oferta2 = new OfertaEducativa();
                // oferta2.setId("2");
                // oferta2.setNombreOferta("Ingeniería en Tecnologías de la Información e
                // Innovación Digital");
                // oferta2.setModalidad("");
                // oferta2.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LTII.png");
                // oferta2.setPerfilEgreso(new PerfilEgreso());

                // ofertas.add(oferta2);

                // OfertaEducativa oferta3 = new OfertaEducativa();
                // oferta3.setId("3");
                // oferta3.setNombreOferta("Ingeniería en Energía y Desarrollo Sostenible");
                // oferta3.setModalidad("");
                // oferta3.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIEDS2.png");
                // oferta3.setPerfilEgreso(new PerfilEgreso());

                // ofertas.add(oferta3);

                // OfertaEducativa oferta4 = new OfertaEducativa();
                // oferta4.setId("4");
                // oferta4.setNombreOferta("Ingeniería Ambiental y Sustentabilidad");
                // oferta4.setModalidad("");
                // oferta4.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIAS.png");
                // oferta4.setPerfilEgreso(new PerfilEgreso());

                // ofertas.add(oferta4);

                // OfertaEducativa oferta5 = new OfertaEducativa();
                // oferta5.setId("5");
                // oferta5.setNombreOferta("Agricultura Sustentable y Protegida");
                // oferta5.setModalidad("");
                // oferta5.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LA.png");
                // oferta5.setPerfilEgreso(new PerfilEgreso());
                // ofertas.add(oferta5);

                // OfertaEducativa oferta6 = new OfertaEducativa();
                // oferta6.setId("6");
                // oferta6.setNombreOferta("Licenciatura en Administración");
                // oferta6.setModalidad("");
                // oferta6.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIL.png");
                // oferta6.setPerfilEgreso(new PerfilEgreso());
                // ofertas.add(oferta6);

                // OfertaEducativa oferta7 = new OfertaEducativa();
                // oferta7.setId("");
                // oferta7.setNombreOferta("Licenciatura en Negocios y Mercadotecnia");
                // oferta7.setModalidad("");
                // oferta7.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/contaduria.png");
                // oferta7.setPerfilEgreso(new PerfilEgreso());
                // ofertas.add(oferta7);

                // // OfertaEducativa oferta8 = new OfertaEducativa();
                // // oferta8.setNombreOferta("Ingeniería en Logística");
                // // oferta8.setModalidad("");
                // //
                // oferta8.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIMI.png");
                // // ofertas.add(oferta8);

                // // OfertaEducativa oferta9 = new OfertaEducativa();
                // // oferta9.setNombreOferta("Licenciatura en Contaduría");
                // // oferta9.setModalidad("Modalidad vespertina y mixta");
                // //
                // oferta9.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIN.png");
                // // ofertas.add(oferta9);

                // // OfertaEducativa oferta10 = new OfertaEducativa();
                // // oferta10.setNombreOferta("Ingeniería en Mantenimiento Industrial");
                // // oferta10.setModalidad("");
                // //
                // oferta10.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LII.png");
                // // ofertas.add(oferta10);

                // // OfertaEducativa oferta11 = new OfertaEducativa();
                // // oferta11.setNombreOferta("Ingeniería en Nanotecnología");
                // // oferta11.setModalidad("");
                // //
                // oferta11.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/semiconductores.png");
                // // ofertas.add(oferta11);

                // // OfertaEducativa oferta12 = new OfertaEducativa();
                // // oferta12.setNombreOferta("Ingeniería Industrial");
                // // oferta12.setModalidad("");
                // //
                // oferta12.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIM.png");
                // // ofertas.add(oferta12);

                // // OfertaEducativa oferta13 = new OfertaEducativa();
                // // oferta13.setNombreOferta("Ingeniería Mecánica");
                // // oferta13.setModalidad("");
                // //
                // oferta13.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIM.png");
                // // ofertas.add(oferta13);

                // // OfertaEducativa oferta14 = new OfertaEducativa();
                // // oferta14.setNombreOferta("Ingeniería Mecánica Automotríz");
                // // oferta14.setModalidad("");
                // //
                // oferta14.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIM.png");
                // // ofertas.add(oferta14);

                // // OfertaEducativa oferta15 = new OfertaEducativa();
                // // oferta15.setNombreOferta("Ingeniería en Microelectrónica y
                // Semiconductores");
                // // oferta15.setModalidad("");
                // //
                // oferta15.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIM.png");
                // // ofertas.add(oferta15);

                // // OfertaEducativa oferta16 = new OfertaEducativa();
                // // oferta16.setNombreOferta("Licenciatura en Educación en Enseñanza del
                // Idioma
                // // Inglés");
                // // oferta16.setModalidad("");
                // //
                // oferta16.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/LIM.png");
                // // ofertas.add(oferta16);

                // // OfertaEducativa oferta17 = new OfertaEducativa();
                // // oferta17.setNombreOferta(
                // // "Maestría en Ingeniería para la Manufactura Inteligente en Competencias
                // // Profesionales");
                // // oferta17.setModalidad("");
                // //
                // oferta17.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/MIMI.png");
                // // ofertas.add(oferta17);

                // // OfertaEducativa oferta18 = new OfertaEducativa();
                // // oferta18.setNombreOferta(
                // // "Maestría en Dirección Logística y Cadena de Suministro Sostenible en
                // // Competencias Profesionales");
                // // oferta18.setModalidad("");
                // //
                // oferta18.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/MDICSG.png");
                // // ofertas.add(oferta18);

                // // OfertaEducativa oferta19 = new OfertaEducativa();
                // // oferta19.setNombreOferta("Maestría en Economía Circular");
                // // oferta19.setModalidad("");
                // //
                // oferta19.setImagen("https://www.uteq.edu.mx/Images/OfertaEducativa/MEC.png");
                // // ofertas.add(oferta19);

                // }

                // else {
                // System.out.println("La lista de ofertas educativas está vacía.");

                // }

                model.addAttribute("ofertas", ofertas);
                return "oferta-educativa";

        }

        @PostMapping("/oferta-educativa/crear")
        public String crearOfertaEducativa(@Valid OfertaEducativa ofertaEducativa, Model model, Errors errors) {
                if (errors.hasErrors()) {
                        model.addAttribute("ofertas", ofertas);
                        return "oferta-educativa";
                }

                ofertas.add(ofertaEducativa);
                return "redirect:/oferta-educativa";
        }
}
