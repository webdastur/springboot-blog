package uz.webdastur.springbootblog.dto.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class Response<T> {
    private int status;
    private T payload;
    private Object message;
    private Integer currentPage;
    private Integer totalPages;

    public static <T> Response<T> ok() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.OK.value());
        return response;
    }


    public static <T> Response<T> alreadyExists() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.CONFLICT.value());
        return response;
    }

    public static <T> Response<T> notFound() {
        Response<T> response = new Response<>();
        response.setStatus(HttpStatus.NOT_FOUND.value());
        return response;
    }
}
