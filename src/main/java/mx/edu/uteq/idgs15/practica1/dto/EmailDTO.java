package mx.edu.uteq.idgs15.practica1.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmailDTO {

    private String destinatario;
    private String asunto;
    private String mensaje;

    public String toString() {
        return "EmailDTO [destinatario=" + destinatario + ", asunto=" + asunto + ", mensaje=" + mensaje + "]";
    }
}
