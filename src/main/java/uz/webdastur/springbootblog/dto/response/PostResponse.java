package uz.webdastur.springbootblog.dto.response;

import lombok.*;
import uz.webdastur.springbootblog.model.User;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostResponse {
    private String postId;
    private String title;
    private String content;
    private User author;
}
