package zev.plagiarismdetectorserver.exception;

import org.springframework.security.access.AccessDeniedException;

public class Authentication extends AccessDeniedException {


  public Authentication(String msg) {
    super(msg);
  }

  public Authentication(String msg, Throwable cause) {
    super(msg, cause);
  }
}
