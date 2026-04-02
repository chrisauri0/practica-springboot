
package mx.edu.uteq.idgs15.practica1.securiy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                    authz.requestMatchers("/", "/home", "/recuperar-contraseña", "/recuperarContrasena",
                            "/nueva-contrasena").permitAll()
                            .requestMatchers("/consola/division/**").hasAnyRole("ADMIN", "CORDINADOR")
                            .requestMatchers("/consola/oferta-educativa/**").hasRole("ADMIN")
                            .requestMatchers("/email/**").hasAnyRole("ADMIN", "USER")
                            .anyRequest().authenticated();
                })
                .formLogin((form) -> form
                        .loginPage("/login")
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
