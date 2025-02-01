package org.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@SuppressWarnings("ALL")
@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {


        //Here we are tryign to authenticate for  the custom logi page that we created
/*
        http
                .csrf(csrf -> csrf.disable()) // Disable CSRF protection if not needed
                .authorizeHttpRequests(auth -> auth
                        .anyRequest().authenticated() // Require authentication for all requests
                )
                .formLogin(form -> form
                        .loginPage("/login") // Specify custom login page
                        .permitAll() // Allow everyone to see the login page
                );


/*
        http
            .authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/login", "/resources/**").permitAll()
                .anyRequest().authenticated()
                    .and()

                    .formLogin()

                    .loginPage("/login") // Custom login page
                    .loginProcessingUrl("/login") // Make sure this URL matches your form action
                    .permitAll()
                    .and()
                    .logout()
                    .permitAll();
        return http.build();
*/
        http
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/cart/add").permitAll()
                        //.anyRequest().authenticated()

                        .anyRequest().permitAll() // Allow all requests without authentication
                )
                .csrf().disable();
       return http.build();


    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authConfig) throws Exception {
        return authConfig.getAuthenticationManager();
    }

}
