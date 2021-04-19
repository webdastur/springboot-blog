package uz.webdastur.springbootblog.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequest {
    private String email;
    private String password;
}
