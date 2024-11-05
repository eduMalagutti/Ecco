package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NoContentException;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetUserProfilePicService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public byte[] getUserProfilePic(String token) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(id).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        byte[] image = user.getProfilePhoto();

        if (image == null) {
            throw new NoContentException("No image found");
        }

//       return outputStream -> {
//           try {
//               outputStream.write(image);
//               outputStream.flush();
//           } catch (IOException e) {
//               e.printStackTrace();
//           }
//       };

        return image;
    }
}
