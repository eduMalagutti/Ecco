package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.LoginUserDTO;
import org.example.projeto_trainee.exceptions.BadCredentialsException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JWTProvider jwtProvider;

    public String authenticate(LoginUserDTO input) {
        var user = this.userRepository.findByEmail(input.getEmail()).orElseThrow(
                () -> new BadCredentialsException("Invalid email or password")
        );

        var passwordMatches = passwordEncoder.matches(input.getPassword(), user.getPassword());

        if (!passwordMatches) {
            throw new BadCredentialsException("Invalid email or password") {
                @Override
                public String getMessage() {
                    return super.getMessage();
                }
            };
        }

        return this.jwtProvider.createToken(user);
    }
}
