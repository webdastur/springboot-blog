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

import java.util.Optional;

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

    @Override
    public UserDTO login(UserDTO userDTO) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()));
        if (user.isPresent()) {
            if (!passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword())) {
                throw AppExceptions.notFound("Password does not match " + userDTO.getEmail());
            } else {
                return modelMapper.map(user.get(), UserDTO.class);
            }
        } else {
            throw AppExceptions.notFound("User not found " + userDTO.getEmail());
        }
    }
}
