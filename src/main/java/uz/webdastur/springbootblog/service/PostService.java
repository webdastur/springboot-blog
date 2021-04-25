package uz.webdastur.springbootblog.service;

import uz.webdastur.springbootblog.dto.model.PostDTO;

public interface PostService {
    PostDTO createPost(PostDTO postDTO);
}
