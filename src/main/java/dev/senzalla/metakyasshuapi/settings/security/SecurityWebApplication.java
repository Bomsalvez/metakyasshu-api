package dev.senzalla.metakyasshuapi.settings.security;

import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.bean.AuthenticationManagerBean;
import dev.senzalla.metakyasshuapi.settings.exception.ErrorDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.ExceptionHandlingConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityWebApplication {

    private final AuthenticationFilter authenticationFilter;
    private final AuthenticationManagerBean managerBean;
    private final MessageDecode messageDecode;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable);
        http.sessionManagement(httpSession -> httpSession.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authorizeHttpRequests(this::requestRegistry);
        http.exceptionHandling(this::exceptionHandling);
        http.authenticationManager(managerBean.authenticationManager());
        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

  private void exceptionHandling(ExceptionHandlingConfigurer<HttpSecurity> exceptionHandling) {
    exceptionHandling
        .authenticationEntryPoint((request, response, e) -> {
            String message = messageDecode.info("error.access");
            ErrorDto errorDto = new ErrorDto(message, HttpStatus.UNAUTHORIZED);
            response.getWriter().write(errorDto.toString());
        })
        .accessDeniedHandler((request, response, e) -> {
            String message = messageDecode.info("error.access");
            ErrorDto errorDto = new ErrorDto(message, HttpStatus.FORBIDDEN);
            response.getWriter().write(errorDto.toString());
        });
}

    private void requestRegistry(AuthorizeHttpRequestsConfigurer<HttpSecurity>.AuthorizationManagerRequestMatcherRegistry requestRegistry) {
        requestRegistry
                .requestMatchers("/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html").permitAll()
                .requestMatchers(HttpMethod.POST, "/user").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/user/confirm/**").permitAll()
                .requestMatchers(HttpMethod.POST, "/user/recover").permitAll()
                .requestMatchers(HttpMethod.PATCH, "/user/reset").permitAll()
                .requestMatchers(HttpMethod.POST, "/authenticate").permitAll()
                .anyRequest().authenticated();
    }
}
