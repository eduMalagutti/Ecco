package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.HelloResponseDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class HelloService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public HelloResponseDTO execute(String token) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(id)
                .orElseGet(User::new);

        if (user.getId() == null) {
            return new HelloResponseDTO("Hello! user was not found");
        }
        if (user.getVerified()) {
            return new HelloResponseDTO("Hello authenticated user!");
        } else {
            return new HelloResponseDTO("Hello! You are not authenticated");
        }
    }

}
