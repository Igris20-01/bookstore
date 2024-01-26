package uz.booker.bookstore.config.security;


import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import uz.booker.bookstore.filter.JwtAuthenticationFilter;

import java.util.Collections;
import java.util.List;

import static org.springframework.http.HttpMethod.GET;
import static org.springframework.security.config.http.SessionCreationPolicy.ALWAYS;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;
import static uz.booker.bookstore.enums.Role.ADMIN;
import static uz.booker.bookstore.enums.Role.USER;


@Configuration
@EnableWebSecurity
@EnableMethodSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

    private final AuthenticationProvider authenticationProvider;

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    private static final String[] WHITE_LIST_URL = {
            "/api/v1/auth/**","/v2/api-docs","/v3/api-docs","/v3/api-docs/**",
            "/swagger-ui/**", "/swagger-resources", "/swagger-resources/**",
            "/configuration/ui","/configuration/security", "/webjars/**",
            "/swagger-ui.html", "v3/api-docs/**",
            "v3/api-docs.yaml", "/api/v1/test", "/api/v1/register", "/api/v1/login/**",
            "/user/**", "/permission/**",
    } ;


    @Bean
    public SecurityFilterChain securityFilter(HttpSecurity http) throws Exception{

        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/api/v1/register","/api/v1/login/**")
                                .permitAll()
                                .requestMatchers(WHITE_LIST_URL).authenticated()
                                .requestMatchers("/api/v1/test").hasAnyRole(ADMIN.name(), USER.name())
                                .requestMatchers(GET,"/api/v1/test").hasAnyAuthority(ADMIN.name())
                                .requestMatchers(GET,"/api/v1/test").hasAnyAuthority(USER.name())
//                                .requestMatchers("").hasAnyAuthority()
//                                .requestMatchers("").hasAnyAuthority()
//                                .requestMatchers("").hasAnyAuthority()
                                .anyRequest()
                                .authenticated()
                )

                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


}
