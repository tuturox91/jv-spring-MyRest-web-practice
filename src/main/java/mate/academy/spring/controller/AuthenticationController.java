package mate.academy.spring.controller;

import jakarta.validation.Valid;
import mate.academy.spring.model.dto.request.UserRequestDto;
import mate.academy.spring.security.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {
    @Autowired
    private AuthenticationService authService;

    @PostMapping("/register")
    @ResponseStatus (HttpStatus.CREATED)
    private void register(@RequestBody @Valid UserRequestDto userRequestDto) {
        authService.register(
                userRequestDto.getEmail(),
                userRequestDto.getPassword());
    }
}
