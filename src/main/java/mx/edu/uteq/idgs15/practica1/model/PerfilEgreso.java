package mx.edu.uteq.idgs15.practica1.model;

import lombok.Data;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;

@Data
@Entity

public class PerfilEgreso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descripcion;
    private List<String> habilidadesTransversales;
    private List<String> habilidadesEspecificas;

    public PerfilEgreso() {
    }

    public PerfilEgreso(Long id, String descripcion, List<String> habilidadesTransversales,
            List<String> habilidadesEspecificas) {
        this.id = id;
        this.descripcion = descripcion;
        this.habilidadesTransversales = habilidadesTransversales;
        this.habilidadesEspecificas = habilidadesEspecificas;
    }
}