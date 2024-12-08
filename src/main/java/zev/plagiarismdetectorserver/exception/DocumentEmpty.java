package zev.plagiarismdetectorserver.exception;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class DocumentEmpty extends RuntimeException {

  public DocumentEmpty(String message) {
    super(message);
  }
}
