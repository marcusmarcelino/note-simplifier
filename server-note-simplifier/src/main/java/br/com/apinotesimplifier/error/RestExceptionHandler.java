package br.com.apinotesimplifier.error;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice(basePackages = "br.com.apinotesimplifier.controllers")
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<MessageExceptionHandler> handleNotFoudException(
      ResourceNotFoundException resourceNotFoundException, HttpServletRequest httpServletRequest) {
    MessageExceptionHandler exceptionHandler = new MessageExceptionHandler(
        new Date(),
        HttpStatus.NOT_FOUND.value(),
        HttpStatus.NOT_FOUND.getReasonPhrase(),
        resourceNotFoundException.getMessage(),
        httpServletRequest.getRequestURI());

    return new ResponseEntity<>(exceptionHandler, HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {

    List<ErrorObject> errors = getErrors(ex);
    ErrorResponse errorResponse = getErrorResponse(ex, status, errors);
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  private List<ErrorObject> getErrors(MethodArgumentNotValidException exception) {
    BindingResult bindingResult = exception.getBindingResult();
    List<FieldError> fieldErrors = bindingResult.getFieldErrors();

    return fieldErrors.stream()
        .map(error -> new ErrorObject(
            error.getDefaultMessage(),
            error.getField(),
            error.getCode(),
            error.getRejectedValue()))
        .collect(Collectors.toList());
  }

  private ErrorResponse getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status,
      List<ErrorObject> errors) {
    return new ErrorResponse("Missing or invalid fields!", status.value(),
        status.getReasonPhrase(), ex.getBindingResult().getObjectName(), errors);
  }
}
