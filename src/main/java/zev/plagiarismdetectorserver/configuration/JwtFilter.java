package zev.plagiarismdetectorserver.configuration;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import zev.plagiarismdetectorserver.service.JwtService;
import zev.plagiarismdetectorserver.service.impl.UserDetailsServiceCustom;

@Component
@RequiredArgsConstructor
@Slf4j
public class JwtFilter extends OncePerRequestFilter {

  private final JwtService jwtService;

  private final UserDetailsServiceCustom userDetailsServiceCustom;

  @Override
  protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
      FilterChain filterChain) throws ServletException, IOException {
    log.info("---- filter chain -----");

    // Continue the filter chain
    filterChain.doFilter(request, response);
  }
}
