package uz.webdastur.springbootblog.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import uz.webdastur.springbootblog.dto.model.PostDTO;
import uz.webdastur.springbootblog.exception.CustomAppException;
import uz.webdastur.springbootblog.model.Post;
import uz.webdastur.springbootblog.model.User;
import uz.webdastur.springbootblog.repository.PostRepository;
import uz.webdastur.springbootblog.repository.UserRepository;
import uz.webdastur.springbootblog.utils.StringUtils;

import java.util.Optional;

@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final StringUtils stringUtils;

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Post post = modelMapper.map(postDTO, Post.class);
        post.setAuthor(getCurrentUser());
        post.setPostId(stringUtils.generateRandomString(30));
        Post savedPost = postRepository.save(post);
        return modelMapper.map(savedPost, PostDTO.class);
    }

    @Override
    public Page<Post> getAllPosts(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Post> postPage = postRepository.findAll(pageable);
        return postPage;
    }

    @Override
    public PostDTO getPost(String postId) {
        Optional<Post> post = Optional.ofNullable(postRepository.findByPostId(postId));
        PostDTO returnValue;
        if (post.isPresent()) {
            returnValue = modelMapper.map(post.get(), PostDTO.class);
            return returnValue;
        } else {
            throw new CustomAppException("Post not found.", HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public PostDTO updatePost(String postId, PostDTO postDTO) {
        Optional<Post> post = Optional.ofNullable(postRepository.findByPostId(postId));
        Post updatedPost;
        PostDTO returnValue;
        if (post.isPresent()) {
            post.get().setContent(postDTO.getContent());
            post.get().setTitle(postDTO.getTitle());
            updatedPost = postRepository.save(post.get());
            returnValue = modelMapper.map(updatedPost, PostDTO.class);
            return returnValue;
        } else {
            throw new CustomAppException("Post not found.", HttpStatus.NOT_FOUND);
        }
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String email;

        if (principal instanceof UserDetails) {
            email = ((UserDetails) principal).getUsername();
        } else {
            email = principal.toString();
        }
        return userRepository.findByEmail(email);
    }
}
