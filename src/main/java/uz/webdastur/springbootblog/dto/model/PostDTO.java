package uz.webdastur.springbootblog.dto.model;

import lombok.*;
import uz.webdastur.springbootblog.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostDTO {
    private String postId;
    private String title;
    private String content;
    private User author;
}
