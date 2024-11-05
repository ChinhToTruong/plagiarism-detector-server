package zev.plagiarismdetectorserver.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import zev.plagiarismdetectorserver.exception.code.ErrorCode;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {
    private HttpStatus statusCode;
    private String code;
    private List<String> errors;

    public ErrorResponse(ErrorCode error) {
        this.statusCode = error.getHttpStatus();
        this.code = error.getCode();
        this.errors = error.getErrors();
    }
}
