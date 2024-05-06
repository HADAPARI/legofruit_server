package mg.legofruit.server.config;

import lombok.AllArgsConstructor;
import mg.legofruit.server.security.CustomAuthenticationManager;
import mg.legofruit.server.security.CustomUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
@EnableWebSecurity
@AllArgsConstructor
public class SecurityConfig {
    private final CustomUserDetailsService userDetailsService;


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) ->
                        auth
                                .requestMatchers("/initialize", "/country", "/region/*", "/user/activate/*", "user/isconnected", "user/signout", "/user/profile", "user/delete", "/product/all","/product/ismine/*", "review/average-ratings/*", "review/comment/*").permitAll()
                                .requestMatchers(HttpMethod.POST, "/user/signup", "/user/signin", "/product/ajout/*", "/review/{userId}/reviews", "/user/subscribe", "order/makeorder", "purchase/buy").permitAll()
                                .requestMatchers(HttpMethod.PUT, "/user/update").permitAll()
                                .anyRequest().authenticated()
                );
        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowCredentials(true);
        config.addAllowedOrigin("http://localhost:5173");
        config.addAllowedHeader("*");
        config.addAllowedMethod("*");

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);

        return new CorsFilter(source);
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager() throws Exception {
        return new CustomAuthenticationManager(userDetailsService, passwordEncoder());
    }
}
