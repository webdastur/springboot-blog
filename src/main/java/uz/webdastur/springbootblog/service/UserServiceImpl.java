package uz.webdastur.springbootblog.service;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.webdastur.springbootblog.dto.model.UserDTO;
import uz.webdastur.springbootblog.exception.CustomAppException;
import uz.webdastur.springbootblog.model.User;
import uz.webdastur.springbootblog.repository.UserRepository;
import uz.webdastur.springbootblog.security.JwtTokenProvider;
import uz.webdastur.springbootblog.utils.StringUtils;

import java.util.ArrayList;
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
            throw new CustomAppException("User already exists " + userDTO.getEmail(), HttpStatus.CONFLICT);
        }
    }

    @Override
    public UserDTO login(UserDTO userDTO) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(userDTO.getEmail()));
        if (user.isPresent()) {
            if (!passwordEncoder.matches(userDTO.getPassword(), user.get().getPassword())) {
                throw new CustomAppException("Password does not match " + userDTO.getEmail(), HttpStatus.NOT_FOUND);
            } else {
                return modelMapper.map(user.get(), UserDTO.class);
            }
        } else {
            throw new CustomAppException("User not found " + userDTO.getEmail(), HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDTO getUser(String email) {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(email));
        if (user.isPresent()) {
            UserDTO returnValue = new UserDTO();
            BeanUtils.copyProperties(user.get(), returnValue);
            return returnValue;
//            return modelMapper.map(user.get(), UserDTO.class);
        } else {
            throw new CustomAppException("User not found " + email, HttpStatus.NOT_FOUND);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<User> user = Optional.ofNullable(userRepository.findByEmail(s));
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(
                    s,
                    user.get().getPassword(),
                    true,
                    true,
                    true,
                    true,
                    new ArrayList<>());
        } else {
            throw new CustomAppException("User not found " + s, HttpStatus.NOT_FOUND);
        }
    }
}
