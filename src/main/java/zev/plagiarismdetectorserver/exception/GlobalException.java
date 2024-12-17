package zev.plagiarismdetectorserver.exception;

import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import zev.plagiarismdetectorserver.dto.response.ErrorResponse;
import zev.plagiarismdetectorserver.exception.code.ErrorCode;


@RestControllerAdvice
public class GlobalException {

  // global exception handler

  @ExceptionHandler(Exception.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleGlobalException(Exception ex) {
    String[] errors = ex.getMessage().split(":");
    String[] messages = {errors[errors.length - 1]};
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), messages);
  }

  @ExceptionHandler(RuntimeException.class)
  @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
  public ErrorResponse handleGlobalRuntimeException(RuntimeException ex) {
    String[] errors = ex.getMessage().split(":");
    String[] messages = {errors[errors.length - 1]};
    return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage());
  }

  // validate handler exception

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ErrorResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
    List<String> messages = new ArrayList<>();

    ex.getBindingResult().getAllErrors().forEach(error -> messages.add(error.getDefaultMessage()));
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), messages.toArray(new String[0]));
  }

  // authentication handler exception

  @ResponseStatus(HttpStatus.UNAUTHORIZED)
  @ExceptionHandler(SecurityException.class)
  public ErrorResponse handleSecurityException(SecurityException ex) {
    return new ErrorResponse(HttpStatus.UNAUTHORIZED.value(), ErrorCode.NOT_ALLOWED.getErrors());
  }

  // user handler exception

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(UserNotFound.class)
  public ErrorResponse handleUserNotFoundException(UserNotFound ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ErrorCode.USER_NOT_FOUND.getErrors());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(UserAlreadyExisted.class)
  public ErrorResponse handleUserAlreadyExistedException(UserAlreadyExisted ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(),
        ErrorCode.USER_ALREADY_EXISTS.getErrors());
  }

  // class handler exception
  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(ClassNotFound.class)
  public ErrorResponse handleClassNotFound(ClassNotFound ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(), ErrorCode.CLASS_NOT_FOUND.getErrors());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(ClassRoomExited.class)
  public ErrorResponse handleClassRoomExited(ClassRoomExited ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ErrorCode.CLASS_EXITED.getErrors());
  }

  @ResponseStatus(HttpStatus.CONFLICT)
  @ExceptionHandler(UserInClassRoom.class)
  public ErrorResponse handleUserInClass(UserInClassRoom ex) {
    return new ErrorResponse(HttpStatus.CONFLICT.value(), ErrorCode.USER_IN_CLASS.getErrors());
  }

  // document handler exception

  @ResponseStatus(HttpStatus.NOT_FOUND)
  @ExceptionHandler(DocumentNotFound.class)
  public ErrorResponse handleDocumentNotFound(DocumentNotFound ex) {
    return new ErrorResponse(HttpStatus.NOT_FOUND.value(),
        ErrorCode.DOCUMENT_NOT_FOUND.getErrors());
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(DocumentExited.class)
  public ErrorResponse handleDocumentExited(DocumentExited ex) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST.value(), ErrorCode.DOCUMENT_EXITED.getErrors());
  }

  @ResponseStatus(HttpStatus.NO_CONTENT)
  @ExceptionHandler(DocumentEmpty.class)
  public ErrorResponse handleDocumentEmpty(DocumentEmpty ex) {
    return new ErrorResponse(HttpStatus.NO_CONTENT.value(), ErrorCode.DOCUMENT_EMPTY.getErrors());
  }


}
