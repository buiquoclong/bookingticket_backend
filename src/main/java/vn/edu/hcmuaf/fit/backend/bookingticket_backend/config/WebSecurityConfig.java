package vn.edu.hcmuaf.fit.backend.bookingticket_backend.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.registration.ClientRegistrationRepository;
import org.springframework.security.oauth2.client.web.OAuth2AuthorizationRequestResolver;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.model.User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.oauth2.CustomOAuth2User;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.utils.JwtTokenUtils;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    private UserService userService;

//    @Autowired
//    private UserDetailServiceImpl userDetailsService;

    @Autowired
    private PasswordEncoderConfig passwordEncoderConfig;

    @Autowired
    private JwtTokenUtils jwtTokenUtil;

    @Autowired
    private ClientRegistrationRepository clientRegistrationRepository;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .cors(withDefaults())
                .authorizeHttpRequests(authorizeRequests -> authorizeRequests
                        .requestMatchers("/**", "/error", "/oauth2/**").permitAll()
                        .anyRequest().authenticated()
                )
                .oauth2Login(oauth2Login -> oauth2Login
                        .authorizationEndpoint(authorization -> authorization
                                .authorizationRequestResolver(customOAuth2AuthorizationRequestResolver()))
                        .successHandler(new AuthenticationSuccessHandler() {
                            @Override
                            public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
                                CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
                                String email = oauthUser.getAttribute("email");
                                String username = oauthUser.getAttribute("name");
                                User user = userService.processOAuthPostLogin(email, username);

                                String token = jwtTokenUtil.generateToken(user);
                                // Lưu token trong session
                                request.getSession().setAttribute("token", token);
                                response.sendRedirect("http://localhost:3000/");
                            }
                        })
                );

        return http.build();
    }
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable();
//        http
//                .cors().and().csrf().disable()
//                .authorizeRequests()
//                .requestMatchers("/**", "/error", "/oauth2/**").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .oauth2Login()
//                .successHandler(new AuthenticationSuccessHandler() {
//                    @Override
//                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
//                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
//                        String email = oauthUser.getAttribute("email");
//                        String username = oauthUser.getAttribute("name");
//                        User user = userService.processOAuthPostLogin(email, username);
//
//                        String token = jwtTokenUtil.generateToken(user);
//                        // Lưu token trong session
//                        request.getSession().setAttribute("token", token);
//                        response.sendRedirect("http://localhost:3000/");
//                    }
//                });
//
//        return http.build();
//    }
public OAuth2AuthorizationRequestResolver customOAuth2AuthorizationRequestResolver() {
    return new CustomAuthorizationRequestResolver(clientRegistrationRepository);
}

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:3000"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        configuration.setAllowCredentials(true); // Cho phép gửi cookie
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Cache-Control", "Content-Type"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}