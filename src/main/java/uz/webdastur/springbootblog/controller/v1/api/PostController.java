package uz.webdastur.springbootblog.controller.v1.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.webdastur.springbootblog.dto.model.PostDTO;
import uz.webdastur.springbootblog.dto.request.PostRequest;
import uz.webdastur.springbootblog.dto.response.PostResponse;
import uz.webdastur.springbootblog.dto.response.Response;
import uz.webdastur.springbootblog.service.PostService;

import javax.validation.Valid;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class PostController {
    private final ModelMapper modelMapper;
    private final PostService postService;

    @PostMapping("/posts")
    public Response<PostResponse> createPost(@Valid @RequestBody PostRequest postRequest) {
        PostDTO postDTO = modelMapper.map(postRequest, PostDTO.class);
        PostResponse returnValue = modelMapper.map(postService.createPost(postDTO), PostResponse.class);
        Response<PostResponse> response = Response.ok();
        response.setPayload(returnValue);
        return response;
    }
}
