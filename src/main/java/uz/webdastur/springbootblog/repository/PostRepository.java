package uz.webdastur.springbootblog.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import uz.webdastur.springbootblog.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    Post findByPostId(String postId);
}
