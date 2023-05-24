package no.ntnu.webshop.group12.webshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
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
    @Order(1)
    public SecurityFilterChain configureAuthorizationFilterChainAPI(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()).securityMatcher("/api/**")
                .authorizeHttpRequests(authorize -> authorize.requestMatchers("/api/**").permitAll())
                .httpBasic(basic -> basic.realmName("Webshop API"));
        return http.build();
    }

    @Bean
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
                .logout(logout -> logout.logoutSuccessUrl("/"));
        return http.build();
    }

}
