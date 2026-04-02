
package mx.edu.uteq.idgs15.practica1.service;

import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UsuarioMemoriaService {
    private final Map<String, String> codigos = new ConcurrentHashMap<>();
    private final Map<String, String> usuarios = new ConcurrentHashMap<>();

    public void guardarCodigo(String email, String codigo) {
        codigos.put(email, codigo);
    }

    public boolean validarCodigo(String email, String codigo) {
        return codigo.equals(codigos.get(email));
    }

    public void guardarUsuario(String email, String password) {
        usuarios.put(email, password);
        codigos.remove(email);
    }

    public boolean validarUsuario(String email, String password) {
        return password.equals(usuarios.get(email));
    }

    public String getPassword(String email) {
        return usuarios.get(email);
    }
}
