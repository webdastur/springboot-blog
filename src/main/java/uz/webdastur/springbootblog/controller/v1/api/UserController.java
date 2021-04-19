package uz.webdastur.springbootblog.controller.v1.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.webdastur.springbootblog.dto.model.UserDTO;
import uz.webdastur.springbootblog.dto.request.UserLoginRequest;
import uz.webdastur.springbootblog.dto.request.UserSignUpRequest;
import uz.webdastur.springbootblog.dto.response.Response;
import uz.webdastur.springbootblog.dto.response.UserResponse;
import uz.webdastur.springbootblog.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/user/signup")
    public Response<UserResponse> signUp(@RequestBody UserSignUpRequest userSignUpRequest) {
        UserDTO userDTO = modelMapper.map(userSignUpRequest, UserDTO.class);
        UserResponse returnValue;
        returnValue = modelMapper.map(userService.signup(userDTO), UserResponse.class);
        Response<UserResponse> response = Response.ok();
        response.setPayload(returnValue);
        return response;
    }

    @PostMapping("/user/login")
    public Response<UserResponse> login(@RequestBody UserLoginRequest userLoginRequest) {
        UserDTO userDTO = modelMapper.map(userLoginRequest, UserDTO.class);
        UserResponse returnValue;
        returnValue = modelMapper.map(userService.login(userDTO), UserResponse.class);
        Response<UserResponse> response = Response.ok();
        response.setPayload(returnValue);
        return response;
    }
}
