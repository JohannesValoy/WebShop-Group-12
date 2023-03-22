package no.ntnu.webshop.group12.webshop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.SecurityFilterChain;

/**
 * This class is used to configure the security of the application
 */
@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    UserDetailsService userDetailService;

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService);
    }

    @Bean
    public SecurityFilterChain configureAuthorizationFilterChain(HttpSecurity http) throws Exception {
        // Set up the authorization requests, starting from most restrictive at the top,
        // to least restrictive on bottom
        http.csrf().disable()
                .authorizeHttpRequests()
                .requestMatchers("/").permitAll()
                .requestMatchers("/login").permitAll()
                .requestMatchers("/register").permitAll()
                .requestMatchers("/products/**").permitAll()
                .requestMatchers("/category/**").permitAll()
                .requestMatchers("/cart").permitAll()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/users/**").hasAnyRole("USER", "ADMIN")
                .and().formLogin().loginPage("/login")
                .and().logout().logoutSuccessUrl("/");
        return http.build();
    }

}
