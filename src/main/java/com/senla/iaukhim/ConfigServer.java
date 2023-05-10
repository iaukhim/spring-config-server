package com.senla.iaukhim;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@EnableConfigServer
public class ConfigServer {
    public static void main(String[] args) {
        SpringApplication.run(ConfigServer.class, args);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests(requests -> {
                            try {
                                requests
                                        .requestMatchers("/actuator/health/**").permitAll()
                                        .anyRequest().authenticated()
                                        .and()
                                        .httpBasic();
                            } catch (Exception e) {
                                throw new RuntimeException(e);
                            }
                        }

                );
        return httpSecurity.build();
    }
}