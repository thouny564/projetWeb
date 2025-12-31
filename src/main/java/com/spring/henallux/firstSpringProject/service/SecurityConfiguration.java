package com.spring.henallux.firstSpringProject.service;

import org.apache.tomcat.util.http.Rfc6265CookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    private static final String LOGIN_PAGE = "/login";

    private static final String[] USER_PAGE = new String[] {"/authenticated", "/user/**", "/order", "/order/**", "/pay", "/pay/**"};


    private static final String[] PUBLIC_ROUTES = new String[]{
            "/",
            "/home",
            "/welcome",
            "/register",
            "/register/**",
            "/signup/**",
            "/css/**",
            "/images/**",
            "/login",
            "/login/**",
            "/cart",
            "/cart/**",
            "/js/**",
            "/catalog",
            "/product",
            "/product/**",
            "/category",
            "/category/**"

    };
    private static final String[] ADMIN_ROUTES = new String[]{
            "/admin/**"
    };

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.csrf(Customizer.withDefaults());

        http
                .authorizeRequests()
                .antMatchers(USER_PAGE).hasAnyRole("USER", "ADMIN")
                .antMatchers(ADMIN_ROUTES).hasRole("ADMIN")
                .antMatchers(PUBLIC_ROUTES).permitAll() // toutes les méthodes autorisées
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage(LOGIN_PAGE)          // page de login
                .loginProcessingUrl(LOGIN_PAGE) // URL POST pour authentification
                .defaultSuccessUrl("/authenticated", false)
                .failureUrl(LOGIN_PAGE + "?error=true")
                .permitAll()

                .and()
                .logout()
                .permitAll();

        http
                .headers()
                .contentSecurityPolicy(csp -> csp.policyDirectives("script-src 'self'"));

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userDetailsService);
        provider.setPasswordEncoder(passwordEncoder);
        return new ProviderManager(provider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer() {
        return factory -> factory.addContextCustomizers(context -> {
            Rfc6265CookieProcessor cookieProcessor = new Rfc6265CookieProcessor();
            cookieProcessor.setSameSiteCookies("Lax");
            context.setCookieProcessor(cookieProcessor);
        });
    }
}
