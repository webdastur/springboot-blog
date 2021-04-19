package uz.webdastur.springbootblog.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
@Setter
public class CustomAppException extends RuntimeException {
    private String message;
    private HttpStatus status;
}
