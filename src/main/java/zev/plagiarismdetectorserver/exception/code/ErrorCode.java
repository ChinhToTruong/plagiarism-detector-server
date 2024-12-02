package zev.plagiarismdetectorserver.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;


@Getter
@AllArgsConstructor
public enum ErrorCode {

    // user response code
    USER_ALREADY_EXISTS(1, HttpStatus.BAD_REQUEST, new String[] {"user already exists"}),
    USER_NOT_FOUND(2, HttpStatus.NOT_FOUND, new String[]{"user not found"}),

    USER_IN_CLASS(6, HttpStatus.BAD_REQUEST, new String[] {"user in class"}),
    USER_NOT_IN_CLASS(7, HttpStatus.BAD_REQUEST, new String[] {"user not in class"}),

    // auth response code
    NOT_ALLOWED(3, HttpStatus.NOT_ACCEPTABLE, new String[] {"user aren't allowed"}),


    // class response code
    CLASS_NOT_FOUND(4, HttpStatus.NOT_FOUND, new String[] {"class not found"}),
    CLASS_EXITED(5, HttpStatus.INTERNAL_SERVER_ERROR, new String[] {"class exited"}),

    // document response code
    DOCUMENT_NOT_FOUND(8, HttpStatus.NOT_FOUND, new String[] {"document not found"}),
    DOCUMENT_EXITED(9, HttpStatus.INTERNAL_SERVER_ERROR, new String[] {"document exited"}),
    ;

    private final int code;
    private final HttpStatus httpStatus;
    private final String[] errors;
}
