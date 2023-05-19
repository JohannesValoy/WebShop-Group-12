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

    private static final String USER = "USER";

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

                            //API Endpoints for updating
                            .requestMatchers(HttpMethod.PUT).hasRole(ADMIN)
                            .requestMatchers(HttpMethod.PUT, "/api/carts/**").hasRole(USER)
                            
                            //API Endpoints for counting
                            .requestMatchers(HttpMethod.GET, "/api/users/count", "/api/carts/count", "/api/purchases/count").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/products/count", "/api/categories/count").permitAll()

                            //API Endpoints for new Objects
                            .requestMatchers(HttpMethod.POST, "/api/products", "/api/categories").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.POST, "/api/carts/product/**").hasRole(USER)
                            .requestMatchers(HttpMethod.POST, "/api/users").permitAll()

                            //API Endpoints for deleting
                            .requestMatchers(HttpMethod.DELETE, "/api/products/**", "/api/categories/**").hasRole(ADMIN)
                            
                            //User allowing to delete stuff they "own"
                            .requestMatchers(HttpMethod.DELETE, "/api/users/me", "/api/carts/product/**").hasRole(USER)

                            //API Endpoints for filtering
                            .requestMatchers(HttpMethod.GET, "/api/users", "/api/carts", "/api/purchases").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/products", "/api/categories").permitAll()

                            //API Endpoints for patching
                            .requestMatchers(HttpMethod.PATCH, "/api/carts/**").hasRole(USER)

                            //Every user can get their own stuff
                            .requestMatchers(HttpMethod.GET, "/api/purchases/me", "/api/users/me", "/api/carts/me").permitAll()

                            //Get by ID
                            .requestMatchers(HttpMethod.GET, "/api/users/**", "/api/carts/**").hasRole(ADMIN)
                            .requestMatchers(HttpMethod.GET, "/api/purchases/**").hasRole(USER)
                            .requestMatchers(HttpMethod.GET, "/api/products/**", "/api/categories/**").permitAll()

                            // Website Resources
                            .requestMatchers("/js/**").permitAll()
                            .requestMatchers("/css/**").permitAll()
                            .requestMatchers("/images/**").permitAll()

                            // Swagger
                            .requestMatchers("/swagger-ui/**", "/v3/api-docs/**").permitAll()
                            
                            //Website Endpoints
                            .requestMatchers("/products/**").permitAll()
                            .requestMatchers("/categories/**").permitAll()
                            .requestMatchers("/account").hasAnyRole(USER)
                            .requestMatchers("/cart", "/cart/**").hasAnyRole(USER)
                            .requestMatchers("/register").permitAll()
                            .requestMatchers("/", "/about", "/search", "/error", "robots.txt").permitAll()
                ).formLogin(formLogin -> formLogin.loginPage("/login").permitAll().failureUrl("/login?error=Wrong+Username+or+Password")).logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

}
