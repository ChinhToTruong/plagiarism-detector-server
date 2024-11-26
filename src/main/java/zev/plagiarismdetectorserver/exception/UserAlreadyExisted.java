package zev.plagiarismdetectorserver.exception;

public class UserAlreadyExisted extends RuntimeException {
  public UserAlreadyExisted() {}
  public UserAlreadyExisted(String message) {
    super(message);
  }
}
