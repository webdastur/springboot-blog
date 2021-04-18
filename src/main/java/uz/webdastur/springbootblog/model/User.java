package uz.webdastur.springbootblog.model;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String userId; // for public use
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private String email;
    @OneToMany(mappedBy = "author")
    private Set<Post> posts;
}
