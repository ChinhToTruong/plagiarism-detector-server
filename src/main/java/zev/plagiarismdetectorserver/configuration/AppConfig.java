package zev.plagiarismdetectorserver.configuration;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.http.SessionCreationPolicy;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import zev.plagiarismdetectorserver.service.UserService;


@Configuration
@RequiredArgsConstructor
public class AppConfig {

  private final UserService userService;


  private final String[] WHITE_LIST = {
      "/auth/**",
      "/user/**"
  };

  @Bean
  public WebMvcConfigurer corsConfigurer() {

    return new WebMvcConfigurer() {
      @Override
      public void addCorsMappings(@NonNull CorsRegistry registry) {
        registry.addMapping("**")
            .allowedOrigins("*")
            .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
            .allowedHeaders("*")
            .allowCredentials(false)
            .maxAge(1000)
        ;
      }
    };
  }

//  @Bean
//  public SecurityFilterChain springSecurityFilterChain(@NonNull HttpSecurity http)
//      throws Exception {
//
//    http.csrf(AbstractHttpConfigurer::disable)
//        .authorizeHttpRequests(request -> request
//            .requestMatchers(WHITE_LIST).permitAll()
//            .anyRequest().authenticated());
//
//    return http.build();
//  }
//
//    @Bean
//    public AuthenticationProvider providerManager() {
//        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
//        provider.setPasswordEncoder(passwordEncoder());
//        provider.setUserDetailsService(userDetailsService());
//
//        return provider;
//    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//        return userService::getUserByEmail;
//    }
//
//    @Bean
//    public WebSecurityCustomizer webSecurityCustomizer(){
//        return webSecurity -> webSecurity.ignoring()
//                .requestMatchers("/v3/**", "/swagger-ui/**", "/swagger-ui.html", "/webjars/**");
//    }

//  @Bean
//  public PasswordEncoder passwordEncoder() {
//    return new BCryptPasswordEncoder(10);
//  }

}
