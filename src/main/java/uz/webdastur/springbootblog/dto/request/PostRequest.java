package uz.webdastur.springbootblog.dto.request;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PostRequest {
    @NotNull
    @NotBlank
    private String title;
    @NotNull
    @NotBlank
    private String content;
}
