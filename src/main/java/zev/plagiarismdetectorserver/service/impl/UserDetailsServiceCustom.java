package zev.plagiarismdetectorserver.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import zev.plagiarismdetectorserver.exception.UserNotFound;
import zev.plagiarismdetectorserver.repository.UserRepository;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserDetailsServiceCustom {

  private final UserRepository userRepository;

  public UserDetailsService getUserDetailsService() {
    log.info("Getting user details...");
    return username -> userRepository.findByEmail(username).orElseThrow(UserNotFound::new);
  }
}
