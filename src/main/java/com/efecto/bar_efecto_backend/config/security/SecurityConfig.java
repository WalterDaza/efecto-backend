package com.efecto.bar_efecto_backend.config.security;

import com.efecto.bar_efecto_backend.utils.Permission;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authenticationProvider;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain (HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) //Cross-site request Forgery se deshabilita ya que la seguridad se va a manejar por tokens
                .sessionManagement(sessionManager ->
                        sessionManager.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Deshabilitar las sesiones Spring Security no guardará el estado del usuario en una sesión.
                .authenticationProvider(authenticationProvider) //Obtiene los detalles del usuario (UserDetailsService) y Verifica la contraseña codificada (PasswordEncoder). que fue configurado en ApplicationConfig
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests(authConfig -> { //Manejo de rutas con sus autorizaciones (privada - publica)

                    //RUTAS PUBLICAS
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/login").permitAll(); //Se indica que a la ruta Login es publica
                    authConfig.requestMatchers("/error").permitAll();
                    authConfig.requestMatchers("/auth/forgot-password", "/auth/reset-password").permitAll();

                    //RUTAS PRIVADAS
                    authConfig.requestMatchers(HttpMethod.POST, "/auth/register").hasAuthority(Permission.CREATE_ONE_USER.name()); //Se asigna el permiso que se debe tener para acceder
                    authConfig.requestMatchers(HttpMethod.GET, "/api/products").hasAuthority(Permission.READ_ALL_PRODUCTS.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/api/products/*").hasAuthority(Permission.READ_PRODUCT_BY_ID.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/api/products/category/*").hasAuthority(Permission.READ_ALL_PRODUCT_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/api/categories").hasAuthority(Permission.READ_ALL_CATEGORIES.name());
                    authConfig.requestMatchers(HttpMethod.GET, "/api/categories/*").hasAuthority(Permission.READ_CATEGORY_BY_ID.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/api/products/*").hasAuthority(Permission.DELETE_ONE_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.DELETE, "/api/categories/*").hasAuthority(Permission.DELETE_ONE_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/api/products/*").hasAuthority(Permission.UPDATE_ONE_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.PUT, "/api/categories/*").hasAuthority(Permission.UPDATE_ONE_CATEGORY.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/api/products").hasAuthority(Permission.SAVE_ONE_PRODUCT.name());
                    authConfig.requestMatchers(HttpMethod.POST, "/api/categories").hasAuthority(Permission.SAVE_ONE_CATEGORY.name());

                    // RUTAS SWAGGER
                    authConfig.requestMatchers(
                            "/v3/api-docs/**",
                            "/v3/api-docs",
                            "/swagger-ui/**",
                            "/swagger-ui.html",
                            "/swagger-resources/**",
                            "/webjars/**"
                    ).permitAll();

                    authConfig.anyRequest().denyAll(); //Cual otra ruta que no este especificada se negará el acceso
                });
        return http.build();

    }

}
