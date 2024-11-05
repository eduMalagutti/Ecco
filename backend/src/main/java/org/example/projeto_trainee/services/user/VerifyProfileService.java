package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.enums.TypeDocumentEnum;
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
public class VerifyProfileService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public User execute(String token, MultipartFile photoFrontDocument, MultipartFile photoBackDocument, TypeDocumentEnum typeDocument) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new UserUpdateException("User not found");
        }

        User user = optionalUser.get();

        boolean verified = verifyDocument(user); // lógica de verificação

        if (verified) {
            byte[] fileContent1 = null;
            byte[] fileContent2 = null;
            try {
                fileContent1 = photoFrontDocument.getBytes();
                fileContent2 = photoBackDocument.getBytes();
            } catch (IOException e) {
                e.printStackTrace();
            }

            user.setPhotoFrontDocument(fileContent1);
            user.setPhotoBackDocument(fileContent2);


            user.setTypeDocument(typeDocument);
            user.setVerified(true);

            return userRepository.save(user);
        }
        return null;
    }

    private boolean verifyDocument(User user) {
        return true;
    }
}
