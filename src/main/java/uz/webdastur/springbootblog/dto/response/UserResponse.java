package uz.webdastur.springbootblog.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String email;
}
