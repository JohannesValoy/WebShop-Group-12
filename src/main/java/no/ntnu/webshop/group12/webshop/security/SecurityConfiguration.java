package no.ntnu.webshop.group12.webshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;


/**
 * This class is used to configure the security of the application
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String ADMIN = "ADMIN";

    @Autowired
    UserDetailsService userDetailService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new BCryptPasswordEncoder());
    }

    @Bean
    public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
        //TODO: Clean up this mess
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(authorize -> authorize
                            .requestMatchers("/api/carts/**").hasAnyRole("USER", ADMIN)
                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.POST, "/api/**").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.PUT, "/api/**").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/purchases").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/purchases/**").hasRole("USER")
                            .requestMatchers(HttpMethod.GET, "/api/user").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/**").permitAll()

                            .requestMatchers("/account").hasAnyRole("USER", ADMIN)
                            .requestMatchers("/cart", "/cart/**").hasAnyRole("USER", ADMIN)

                            // Website endpoints
                            .requestMatchers("/js/**").permitAll()
                            .requestMatchers("/css/**").permitAll()
                            .requestMatchers("/images/**").permitAll()
                            .requestMatchers("/products/**").permitAll()
                            .requestMatchers("/categories/**").permitAll()
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            .requestMatchers("/register").permitAll()
                            .requestMatchers("/", "/about", "/search", "/error", "robots.txt").permitAll()
                ).formLogin(formLogin -> formLogin.loginPage("/login").permitAll()).logout(logout -> logout.logoutSuccessUrl("/"));

                // Admin endpoints
                
                // API endpoints
                

                // Account endpoints
               
        return http.build();
    }

}
