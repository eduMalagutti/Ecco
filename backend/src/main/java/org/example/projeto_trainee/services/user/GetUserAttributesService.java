package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.GetUserAtrributesDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserAttributesService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public GetUserAtrributesDTO execute(String token) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        User user = this.userRepository.findById(id).orElseThrow(
                () -> new UsernameNotFoundException("User not found")
        );

        return new GetUserAtrributesDTO(
                user.getName(),
                user.getPhone(),
                user.getEmail()
        );
    }
}
