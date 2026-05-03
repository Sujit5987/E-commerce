package user_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    @Order(2)
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(Customizer.withDefaults())
                .authorizeHttpRequests(auth -> auth
                        // Allow CORS preflight
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        // Allow creating/registering users and login/token endpoints without authentication
                        .requestMatchers(HttpMethod.POST, "/users").permitAll()
                        .requestMatchers(
                                "/users/login",
                                "/users/register",
                                "/oauth2/token"
                        ).permitAll()
                        .anyRequest().authenticated()

                )
                .oauth2ResourceServer(o -> o.jwt())
        ;

        return http.build();
    }

}
