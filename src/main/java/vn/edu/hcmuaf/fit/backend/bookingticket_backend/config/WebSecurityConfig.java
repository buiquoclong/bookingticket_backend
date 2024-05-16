package vn.edu.hcmuaf.fit.backend.bookingticket_backend.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import vn.edu.hcmuaf.fit.backend.bookingticket_backend.service.UserService;

@Configuration
public class WebSecurityConfig {
    @Autowired
    private UserService userService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();
//        http.csrf().disable()
//                .oauth2Login()
//                .loginPage("/dang-nhap") // Nếu cần, có thể chỉ định trang đăng nhập tùy chỉnh
//                .userInfoEndpoint().userService(oAuth2UserService)
//                .and().successHandler(new AuthenticationSuccessHandler() {
//                    @Override
////                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
////                        CustomOAuth2User oauthUser = (CustomOAuth2User) authentication.getPrincipal();
////                        userService.processOAuthPostLogin(oauthUser.getAttribute("email"));
////                        response.sendRedirect("/");
////                    }
//                });
        return http.build();
    }
}