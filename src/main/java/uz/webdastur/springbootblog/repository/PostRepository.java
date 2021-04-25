package uz.webdastur.springbootblog.repository;

import org.springframework.data.repository.CrudRepository;
import uz.webdastur.springbootblog.model.Post;

public interface PostRepository extends CrudRepository<Post, Long> {
}
