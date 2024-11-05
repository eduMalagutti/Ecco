package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.UpdateUserRequestDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.BadRequestException;
import org.example.projeto_trainee.exceptions.UserUpdateException;
import org.example.projeto_trainee.presenters.UserPresenter;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserPresenter userPresenter;

    @Autowired
    JWTProvider jwtProvider;

    public Map<String, Object> execute(String token, UpdateUserRequestDTO userDetails) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserUpdateException("User not found");
        }

        User user = optionalUser.get();

        Map<String, Object> response = this.userPresenter.presentModifiedFields(user, userDetails);

        if (userDetails.getName() != null) {
            user.setName(userDetails.getName());
        }
        if (userDetails.getEmail() != null && !userDetails.getEmail().equals(user.getEmail())) {
            userRepository.findByEmail(userDetails.getEmail())
                    .ifPresent(existingUser -> {
                        throw new BadRequestException("User with this email already exists");
                    });
            user.setEmail(userDetails.getEmail());
        }
        if (userDetails.getPhone() != null) {
            user.setPhone(userDetails.getPhone());
        }

        userRepository.save(user);

        return response;
    }
}
