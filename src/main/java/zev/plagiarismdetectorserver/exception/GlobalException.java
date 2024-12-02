package zev.plagiarismdetectorserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import zev.plagiarismdetectorserver.dto.response.ErrorResponse;
import zev.plagiarismdetectorserver.exception.code.ErrorCode;

import java.util.ArrayList;
import java.util.List;


@RestControllerAdvice
public class GlobalException {
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGlobalException(Exception ex){
        String[] errors = ex.getMessage().split(":");
        String[] messages = {errors[errors.length-1]};
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), messages);
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handleGlobalRuntimeException(RuntimeException ex){
        String[] errors = ex.getMessage().split(":");
        String[] messages = {errors[errors.length-1]};
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), messages);
    }


    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserNotFound.class)
    public ErrorResponse handleUserNotFoundException(UserNotFound ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCode.USER_NOT_FOUND.getErrors());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserAlreadyExisted.class)
    public ErrorResponse handleUserAlreadyExistedException(UserAlreadyExisted ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCode.USER_ALREADY_EXISTS.getErrors());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex){
        List<String> messages = new ArrayList<>();

        ex.getBindingResult().getAllErrors().forEach(error -> messages.add(error.getDefaultMessage()));
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), messages.toArray(new String[0]));
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClassNotFound.class)
    public ErrorResponse handleClassNotFound(ClassNotFound ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCode.CLASS_NOT_FOUND.getErrors());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ClassRoomExited.class)
    public ErrorResponse handleClassRoomExited(ClassRoomExited ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCode.CLASS_EXITED.getErrors());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(UserInClassRoom.class)
    public ErrorResponse handleUserInClass(UserInClassRoom ex){
        return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCode.USER_IN_CLASS.getErrors());
    }

}
