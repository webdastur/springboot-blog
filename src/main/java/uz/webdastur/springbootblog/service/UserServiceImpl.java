package uz.webdastur.springbootblog.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.webdastur.springbootblog.dto.model.UserDTO;
import uz.webdastur.springbootblog.exception.AppExceptions;
import uz.webdastur.springbootblog.model.User;
import uz.webdastur.springbootblog.repository.UserRepository;
import uz.webdastur.springbootblog.utils.StringUtils;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final StringUtils stringUtils;

    @Override
    public UserDTO signup(UserDTO userDTO) {
        User user = userRepository.findByEmail(userDTO.getEmail());
        if (user == null) {
            UserDTO returnValue;
            user = modelMapper.map(userDTO, User.class);
            user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
            user.setUserId(stringUtils.generateRandomString(30));
            user = userRepository.save(user);
            returnValue = modelMapper.map(user, UserDTO.class);
            return returnValue;
        } else {
            throw AppExceptions.alreadyExists("User already exists " + userDTO.getEmail());
        }
    }
}
