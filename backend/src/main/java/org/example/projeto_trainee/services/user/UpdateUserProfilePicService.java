package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.UserUpdateException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;
import java.util.UUID;

@Service
public class UpdateUserProfilePicService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTProvider jwtProvider;

    public User execute(String token, MultipartFile userReq) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserUpdateException("User not found");
        }

        User user = optionalUser.get();

        byte[] fileContent = null;
        try {
            fileContent = userReq.getBytes();
        } catch (IOException e) {
            e.printStackTrace();
        }

        user.setProfilePhoto(fileContent);

        return userRepository.save(user);
    }
}
