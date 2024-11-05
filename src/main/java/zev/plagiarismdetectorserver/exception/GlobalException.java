package zev.plagiarismdetectorserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zev.plagiarismdetectorserver.exception.code.ErrorCode;

import java.util.List;

@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGlobalException(Exception ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(List.of(ex.getMessage()));
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setCode("C-0001");
        return errorResponse;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGlobalRuntimeException(RuntimeException ex){
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(List.of(ex.getMessage()));
        errorResponse.setStatusCode(HttpStatus.BAD_REQUEST);
        errorResponse.setCode("C-0001");
        return errorResponse;
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFound.class)
    public ErrorResponse handleUserNotFoundException(UserNotFound ex){
        return new ErrorResponse(ErrorCode.USER_NOT_FOUND);
    }
}
