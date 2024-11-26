package zev.plagiarismdetectorserver.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    // user response
    USER_ALREADY_EXISTS(1, HttpStatus.BAD_REQUEST, new String[] {"user already exists"}),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, new String[]{"user not found"}),


    // auth response
    NOT_ALLOWED(3, HttpStatus.NOT_ACCEPTABLE, new String[] {"user aren't allowed"}),


    CLASS_NOT_FOUND(4, HttpStatus.NOT_FOUND, new String[] {"class not found"}),
    CLASS_EXITED(5, HttpStatus.INTERNAL_SERVER_ERROR, new String[] {"class exited"}),
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String[] errors;
}
