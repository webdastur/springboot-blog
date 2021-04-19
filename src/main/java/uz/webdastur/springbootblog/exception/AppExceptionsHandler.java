package uz.webdastur.springbootblog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import uz.webdastur.springbootblog.dto.response.Response;

@RestController
@ControllerAdvice
public class AppExceptionsHandler {
    @ExceptionHandler(value = {AppExceptions.EntityAlreadyExists.class})
    public ResponseEntity<Response<Object>> handleEntityAlreadyExists(Exception ex, WebRequest request) {
        Response<Object> response = Response.alreadyExists();
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = {AppExceptions.EntityNotFound.class})
    public ResponseEntity<Response<Object>> handleEntityNotFound(Exception ex, WebRequest request) {
        Response<Object> response = Response.notFound();
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
