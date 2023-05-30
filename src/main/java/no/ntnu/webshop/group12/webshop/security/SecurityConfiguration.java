package no.ntnu.webshop.group12.webshop.security;

import java.io.IOException;

import no.ntnu.webshop.group12.webshop.exception.APIError;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletResponse;

/**
 * This class is used to configure the security of the application
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String ADMIN = "ADMIN";

    private static final String USER = "USER";

    @Autowired
    UserDetailsService userDetailService;


    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    @Order(1)
    public SecurityFilterChain configureAuthorizationFilterChainAPI(HttpSecurity http, ObjectMapper mapper) throws Exception {
        http.csrf(csrf -> csrf.disable()).securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize
                        // API Endpoints for updating
                        .requestMatchers(HttpMethod.PUT).hasRole(ADMIN)

                        // API Endpoints for counting
                        .requestMatchers(HttpMethod.GET, "/api/users/count", "/api/purchases/count").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/products/count", "/api/categories/count").permitAll()

                        // User allowing to delete stuff they "own"
                        .requestMatchers(HttpMethod.DELETE, "/api/users/me", "/api/carts/me").hasRole(USER)

                        // API Endpoints for filtering
                        .requestMatchers(HttpMethod.GET, "/api/users", "/api/carts", "/api/purchases").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/products", "/api/categories").permitAll()

                        // API Endpoints for getting stuff they "own"
                        .requestMatchers("/api/carts/me/**").hasRole(USER)
                        .requestMatchers(HttpMethod.GET, "/api/purchases/me", "/api/users/me", "/api/carts/me")
                        .hasRole(USER)

                        // API Endpoints for new Objects
                        .requestMatchers(HttpMethod.POST, "/api/products", "/api/categories").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.POST, "/api/carts/product/**").hasRole(USER)
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()

                        // API Endpoints for deleting
                        .requestMatchers(HttpMethod.DELETE, "/api/products/**", "/api/categories/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.DELETE, "/api/users/**", "/api/purchases/**").hasRole(ADMIN)

                        // Get by ID
                        .requestMatchers(HttpMethod.GET, "/api/users/**", "/api/carts/**").hasRole(ADMIN)
                        .requestMatchers(HttpMethod.GET, "/api/purchases/**").hasAnyRole(ADMIN, USER)
                        .requestMatchers(HttpMethod.GET, "/api/products/**", "/api/categories/**").permitAll()
                        .anyRequest().denyAll())
                //Allowing very basic authentication
                .httpBasic(basic -> basic.realmName("Cyberpunk Webshop API"))
                //Handling unauthorized requests
                .exceptionHandling(handler -> handler.authenticationEntryPoint((request, response, authException) -> {
                    response.setContentType("application/json");
                    response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                    try {
                        APIError errorMessage = new APIError(
                                "You are not authorized to access this resource. Please login.");
                        response.getWriter().write(mapper.writeValueAsString(errorMessage.getErrorAttributes()));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }));
        return http.build();
    }

    @Bean
    @Order(2)
    public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                        // Website Resources
                        .requestMatchers("/js/**").permitAll()
                        .requestMatchers("/css/**").permitAll()
                        .requestMatchers("/images/**").permitAll()

                        // Swagger
                        .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()

                        // Website Endpoints
                        .requestMatchers("/products/**").permitAll()
                        .requestMatchers("/categories/**").permitAll()
                        .requestMatchers("/account").hasAnyRole(USER)
                        .requestMatchers("/cart", "/cart/**").hasAnyRole(USER)
                        .requestMatchers("/register").permitAll()
                        .requestMatchers("/", "/about", "/search", "/error", "robots.txt").permitAll()
                        .anyRequest().denyAll())
                .formLogin(formLogin -> formLogin.loginPage("/login").permitAll()
                        .failureUrl("/login?error=Wrong+Username+or+Password"))
                .logout(logout -> logout.logoutSuccessUrl("/"))
                .exceptionHandling(exception -> exception.accessDeniedPage("/page-error"));
        return http.build();
    }

}
