package mx.edu.uteq.idgs15.practica1.model;

import lombok.Data;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GenerationType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;

@Data
@Entity

public class PerfilDeIngreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private List<String> habilidadesTransversales;
    private List<String> habilidadesEspecificas;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "perfil_id")
    private List<CapacidadTransversal> capacidadesTransversales;

    @OneToOne()
    @JoinColumn(name = "oferta_id")
    private OfertaEducativa ofertaEducativa;

    public PerfilDeIngreso() {
    }

    public PerfilDeIngreso(Long id, String descripcion, List<String> habilidadesTransversales,
            List<String> habilidadesEspecificas, List<CapacidadTransversal> capacidadesTransversales,
            OfertaEducativa ofertaEducativa) {
        this.id = id;
        this.descripcion = descripcion;
        this.habilidadesTransversales = habilidadesTransversales;
        this.habilidadesEspecificas = habilidadesEspecificas;
        this.capacidadesTransversales = capacidadesTransversales;
        this.ofertaEducativa = ofertaEducativa;
    }
}