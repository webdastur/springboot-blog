package uz.webdastur.springbootblog.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserRequest {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
}
