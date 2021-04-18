package uz.webdastur.springbootblog.controller.v1.api;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.webdastur.springbootblog.dto.model.UserDTO;
import uz.webdastur.springbootblog.dto.request.UserRequest;
import uz.webdastur.springbootblog.dto.response.Response;
import uz.webdastur.springbootblog.dto.response.UserResponse;
import uz.webdastur.springbootblog.service.UserService;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1")
public class UserController {
    private final UserService userService;
    private final ModelMapper modelMapper;

    @PostMapping("/user")
    public Response<UserResponse> signUp(@RequestBody UserRequest userRequest) {
        UserDTO userDTO = modelMapper.map(userRequest, UserDTO.class);
        UserResponse returnValue;
        returnValue = modelMapper.map(userService.signup(userDTO), UserResponse.class);
        Response<UserResponse> response = Response.ok();
        response.setPayload(returnValue);
        return response;
    }
}
