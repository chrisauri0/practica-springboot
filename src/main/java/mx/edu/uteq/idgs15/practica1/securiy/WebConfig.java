package mx.edu.uteq.idgs15.practica1.securiy;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((authz) -> {
                    authz.requestMatchers("/", "/home").permitAll()
                            .requestMatchers("/consola/division/**").hasAnyRole("ADMIN", "CORDINADOR")
                            .requestMatchers("/consola/oferta-educativa/**").hasRole("ADMIN")
                            .anyRequest().authenticated();
                })
                .formLogin((form) -> form
                        .permitAll())
                .logout((logout) -> logout.permitAll());

        return http.build();
    }

}
