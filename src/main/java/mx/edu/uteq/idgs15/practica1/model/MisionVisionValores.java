package mx.edu.uteq.idgs15.practica1.model;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;

@Data
@Entity
public class MisionVisionValores {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    @Size(min = 10, max = 500)
    private String mision;
    @NotEmpty
    @Size(min = 10, max = 500)
    private String vision;
    @NotEmpty
    @Size(min = 10, max = 500)
    private String valores;

    @NotEmpty
    @Size(min = 10, max = 500)
    private String politica;

    @NotEmpty
    @Size(min = 10, max = 500)
    private String objetivosSGE;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMision() {
        return mision;
    }

    public void setMision(String mision) {
        this.mision = mision;
    }

    public String getVision() {
        return vision;
    }

    public void setVision(String vision) {
        this.vision = vision;
    }

    public String getValores() {
        return valores;
    }

    public void setValores(String valores) {
        this.valores = valores;
    }

    public String getPolitica() {
        return politica;
    }

    public void setPolitica(String politica) {
        this.politica = politica;
    }

    public String getObjetivosSGE() {
        return objetivosSGE;
    }

    public void setObjetivosSGE(String objetivosSGE) {
        this.objetivosSGE = objetivosSGE;
    }
}
