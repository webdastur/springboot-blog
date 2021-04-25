package uz.webdastur.springbootblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import uz.webdastur.springbootblog.dto.response.Response;

import java.util.HashMap;
import java.util.Map;

@RestController
@ControllerAdvice
public class AppExceptionsHandler {

    @ExceptionHandler(value = {CustomAppException.class})
    public ResponseEntity<Response<Object>> handleCustomAppException(CustomAppException ex, WebRequest request) {
        Response<Object> response = new Response<>();
        response.setStatus(ex.getStatus().value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, ex.getStatus());
    }

    @ExceptionHandler(value = {BadCredentialsException.class})
    public ResponseEntity<Response<Object>> handleBadCredentialsException(BadCredentialsException ex, WebRequest request) {
        Response<Object> response = new Response<>();
        response.setStatus(HttpStatus.FORBIDDEN.value());
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = {MethodArgumentNotValidException.class})
    public ResponseEntity<Response<Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,
                                                                                  WebRequest request) {
        Response<Object> response = new Response<>();
        Map<String, String> errors = new HashMap<>();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            errors.put(error.getObjectName(), error.getDefaultMessage());
        }
        response.setStatus(HttpStatus.BAD_REQUEST.value());
        response.setMessage(errors);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

//    @ExceptionHandler(value = {AppExceptions.EntityAlreadyExists.class})
//    public ResponseEntity<Response<Object>> handleEntityAlreadyExists(Exception ex, WebRequest request) {
//        Response<Object> response = Response.alreadyExists();
//        response.setMessage(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
//    }
//
//    @ExceptionHandler(value = {AppExceptions.EntityNotFound.class})
//    public ResponseEntity<Response<Object>> handleEntityNotFound(Exception ex, WebRequest request) {
//        Response<Object> response = Response.notFound();
//        response.setMessage(ex.getMessage());
//        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
//    }
}
