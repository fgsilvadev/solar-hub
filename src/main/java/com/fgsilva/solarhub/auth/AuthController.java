package com.fgsilva.solarhub.auth;

import com.fgsilva.solarhub.user.RegisterUserRequest;
import com.fgsilva.solarhub.user.UserResponse;
import com.fgsilva.solarhub.user.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse register(@Valid @RequestBody RegisterUserRequest request) {

        return userService.register(request);
    }
}
