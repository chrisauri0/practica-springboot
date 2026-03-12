package mx.edu.uteq.idgs15.practica1.model;

import lombok.Data;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotEmpty;

import org.hibernate.annotations.ManyToAny;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.ManyToOne;

@Data
@Entity
public class OfertaEducativa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotEmpty
    private String nombreOferta;
    private String modalidad;
    @NotEmpty
    @Size(min = 10, max = 500)
    private String imagen;
    @OneToOne
    private PerfilDeIngreso perfilEgreso;

    @ManyToOne
    @JoinColumn(name = "id_division")
    private Division division;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombreOferta() {
        return nombreOferta;
    }

    public void setNombreOferta(String nombreOferta) {
        this.nombreOferta = nombreOferta;
    }

    public String getModalidad() {
        return modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public PerfilDeIngreso getPerfilEgreso() {
        return perfilEgreso;
    }

    public void setPerfilEgreso(PerfilDeIngreso perfilEgreso) {
        this.perfilEgreso = perfilEgreso;
    }

}
