package mx.edu.uteq.idgs15.practica1.securiy;

import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.beans.factory.annotation.Autowired;
import mx.edu.uteq.idgs15.practica1.service.UsuarioMemoriaService;

@EnableWebSecurity
@Configuration
public class WebSecurityConfig {

    @Autowired
    private UsuarioMemoriaService usuarioMemoriaService;

    @Bean
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        return username -> {
            // Primero busca en los usuarios "fijos"
            if (username.equals("admin")) {
                return User.withUsername("admin")
                        .password(passwordEncoder().encode("admin"))
                        .roles("ADMIN", "USER")
                        .build();
            }
            if (username.equals("user")) {
                return User.withUsername("user")
                        .password(passwordEncoder().encode("12345"))
                        .roles("USER")
                        .build();
            }
            if (username.equals("cordinador")) {
                return User.withUsername("cordinador")
                        .password(passwordEncoder().encode("12345"))
                        .roles("CORDINADOR", "USER")
                        .build();
            }

            String password = usuarioMemoriaService.getPassword(username);
            if (password != null) {
                return User.withUsername(username)
                        .password(password)
                        .roles("USER")
                        .build();
            }
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        };
    }

}
