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
        // Set up the authorization requests, starting from most restrictive at the top,
        // to least restrictive on bottom
        http.csrf().disable()
                .authorizeHttpRequests()

                // Admin endpoints
                .requestMatchers("/admin/**").hasRole(ADMIN)

                // API endpoints
                .requestMatchers("/api/**/me").hasAnyRole("USER", ADMIN)
                .requestMatchers("/api/carts/**").hasAnyRole("USER", ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/user").permitAll()
                .requestMatchers(HttpMethod.DELETE, "/api/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.POST, "/api/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.PUT, "/api/**").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/purchases").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/purchases/**").hasRole("USER")
                .requestMatchers(HttpMethod.GET, "/api/user").hasRole(ADMIN)
                .requestMatchers(HttpMethod.GET, "/api/**").permitAll()


                // Account endpoints
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
                .requestMatchers("/", "/about", "/search","/error").permitAll()
                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutSuccessUrl("/");
        return http.build();
    }

}
