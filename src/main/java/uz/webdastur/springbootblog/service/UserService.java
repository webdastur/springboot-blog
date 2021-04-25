package uz.webdastur.springbootblog.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import uz.webdastur.springbootblog.dto.model.UserDTO;

public interface UserService extends UserDetailsService {
    UserDTO signup(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

    UserDTO getUser(String email);
}
