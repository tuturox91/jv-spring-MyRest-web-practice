package mate.academy.spring.controller;

import jakarta.validation.Valid;
import mate.academy.spring.mapper.DtoResponseMapper;
import mate.academy.spring.model.User;
import mate.academy.spring.model.dto.response.UserResponseDto;
import mate.academy.spring.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private UserService userService;
    private final DtoResponseMapper<UserResponseDto, User> userResponseMapper;

    public UserController(UserService userService,
                          DtoResponseMapper<UserResponseDto, User> userResponseMapper) {
        this.userService = userService;
        this.userResponseMapper = userResponseMapper;
    }

    @GetMapping("/by-email")
    public UserResponseDto getUserByEmail(@RequestParam @Valid String email) {
        return userResponseMapper.toDto(userService.findByEmail(email).get());
    }
}
