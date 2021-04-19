package uz.webdastur.springbootblog.service;

import uz.webdastur.springbootblog.dto.model.UserDTO;

public interface UserService {
    UserDTO signup(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);
}
