package zev.plagiarismdetectorserver.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseData<T> implements Serializable {

  private int statusCode = 200;
  private String[] messages;
  private String message;
  private T data;
  private LocalDateTime timestamp = LocalDateTime.now();

  public ResponseData(int statusCode, String[] messages) {
    this.statusCode = statusCode;
    this.messages = messages;
  }

  public ResponseData(int statusCode, String message) {
    this.statusCode = statusCode;
    this.message = message;
  }

  public ResponseData(int statusCode, String message, T data) {
    this.statusCode = statusCode;
    this.message = message;
    this.data = data;
  }

  public ResponseData(String message, T data) {
    this.message = message;
    this.data = data;
  }

  public ResponseData(String[] messages) {
    this.messages = messages;
  }

  public ResponseData(String message) {
    this.message = message;
  }

}
