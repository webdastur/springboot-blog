package uz.webdastur.springbootblog.model;

import lombok.*;

import javax.persistence.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String postId; // for public use
    private String title;
    private String content;
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;
}
