package uz.webdastur.springbootblog.controller.v1.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import uz.webdastur.springbootblog.dto.model.PostDTO;
import uz.webdastur.springbootblog.dto.request.PostRequest;
import uz.webdastur.springbootblog.dto.response.PostResponse;
import uz.webdastur.springbootblog.dto.response.Response;
import uz.webdastur.springbootblog.model.Post;
import uz.webdastur.springbootblog.service.PostService;

import javax.validation.Valid;
import java.util.List;

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

    @GetMapping("/posts")
    public Response<List<PostResponse>> getAllPosts(
            @RequestParam(name = "page", defaultValue = "0") int page,
            @RequestParam(name = "size", defaultValue = "10") int size) {
        Page<Post> postPage = postService.getAllPosts(page, size);
        List<PostResponse> returnValue = modelMapper.map(postPage.getContent(), new TypeToken<List<PostResponse>>() {
        }.getType());
        Response<List<PostResponse>> response = Response.ok();
        response.setTotalPages(postPage.getTotalPages());
        response.setCurrentPage(postPage.getNumber());
        response.setPayload(returnValue);
        return response;
    }

    @GetMapping("/posts/{postId}")
    public Response<PostResponse> getPostByPostId(@PathVariable String postId) {
        PostDTO postDTO = postService.getPost(postId);
        Response<PostResponse> response = Response.ok();
        response.setPayload(modelMapper.map(postDTO, PostResponse.class));
        return response;
    }

    @PutMapping("/posts/{postId}")
    public Response<PostResponse> updatePost(@PathVariable String postId, @Valid @RequestBody PostRequest postRequest) {
        PostDTO post = modelMapper.map(postRequest, PostDTO.class);
        PostDTO postDTO = postService.updatePost(postId, post);
        Response<PostResponse> response = Response.ok();
        response.setPayload(modelMapper.map(postDTO, PostResponse.class));
        return response;
    }

    @DeleteMapping("/posts/{postId}")
    public Response<String> deletePostByPostId(@PathVariable String postId) {
        postService.deletePost(postId);
        Response<String> response = Response.ok();
        response.setMessage("Post successfully deleted.");
        return response;
    }
}
