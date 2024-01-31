package ArtBridge.ArtBridgelogin.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable);
        http
                .authorizeHttpRequests(
                        authorize -> authorize
                                .requestMatchers("/members/new").permitAll()
                                .requestMatchers("/members/login").permitAll()
                                .requestMatchers("/artists/new").permitAll()
                                .requestMatchers("/artists/login").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
