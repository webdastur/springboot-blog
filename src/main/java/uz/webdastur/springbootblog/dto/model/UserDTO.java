package uz.webdastur.springbootblog.dto.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import uz.webdastur.springbootblog.model.Post;

import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserDTO {
    private String userId;
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    @JsonBackReference
    private Set<Post> posts;
}
