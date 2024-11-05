package zev.plagiarismdetectorserver.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

import java.util.List;

@Getter
@AllArgsConstructor
public enum ErrorCode {

    // user response
    USER_ALREADY_EXISTS("U-0001", HttpStatus.BAD_REQUEST, List.of("user already exists")),
    USER_NOT_FOUND("U-0002", HttpStatus.NOT_FOUND, List.of("user not found")),


    // auth response
    NOT_ALLOWED("A-0001", HttpStatus.NOT_ACCEPTABLE, List.of("user aren't allowed")),

    ;

    private final String code;
    private final HttpStatus httpStatus;
    private final List<String> errors;
}
