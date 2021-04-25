package uz.webdastur.springbootblog.service;

import org.springframework.data.domain.Page;
import uz.webdastur.springbootblog.dto.model.PostDTO;
import uz.webdastur.springbootblog.model.Post;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);

    Page<Post> getAllPosts(int page, int size);

    PostDTO getPost(String postId);

    PostDTO updatePost(String postId, PostDTO postDTO);

    void deletePost(String postId);
}
