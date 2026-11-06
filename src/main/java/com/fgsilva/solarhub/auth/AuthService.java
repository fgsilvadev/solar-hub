package com.fgsilva.solarhub.auth;

import com.fgsilva.solarhub.user.User;
import com.fgsilva.solarhub.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final JwtService jwtService;

    public LoginResponse login(LoginRequest request) {
        String email = request.email().trim().toLowerCase();;

        // Throws AuthenticationException (BadCredentialsException) if email or password are wrong.
        authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken.unauthenticated(email, request.password()));

        User user = userRepository.findByEmail(email)
            .orElseThrow(() -> new IllegalStateException("Authenticated user not found: " + email));

        return LoginResponse.bearer(jwtService.generateToken(user.getEmail(), user.getRole()));
    }
}
