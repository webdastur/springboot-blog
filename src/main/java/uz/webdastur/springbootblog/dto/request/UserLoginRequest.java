package uz.webdastur.springbootblog.dto.request;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserLoginRequest {
    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
