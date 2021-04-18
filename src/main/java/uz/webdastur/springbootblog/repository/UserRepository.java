package uz.webdastur.springbootblog.repository;

import org.springframework.data.repository.CrudRepository;
import uz.webdastur.springbootblog.model.User;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
}
