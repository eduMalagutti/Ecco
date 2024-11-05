package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.ChangePasswordRequestDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ChangePasswordService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JWTProvider jwtProvider;

    @Transactional
    public void changePassword(String token, ChangePasswordRequestDTO changePasswordRequestDTO) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User not found"));

        String encodedPassword = passwordEncoder.encode(changePasswordRequestDTO.getNewPassword());
        user.setPassword(encodedPassword);

        userRepository.save(user);
    }
}