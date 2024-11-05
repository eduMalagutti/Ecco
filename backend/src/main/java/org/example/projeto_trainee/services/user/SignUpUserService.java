package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.SignUpRequestDTO;
import org.example.projeto_trainee.dto.user.SignUpResponseDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.BadRequestException;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class SignUpUserService {

    @Value ("${spring.api.url}")
    public String apiUrl;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private EmailService emailService;

    @Transactional
    public SignUpResponseDTO execute(SignUpRequestDTO signUpRequest) {


        this.userRepository.findByEmail(signUpRequest.getEmail())
                .ifPresent((User) -> {
                    throw new BadRequestException("User with this email already exists");
                });

        User user = new User();

        user.setName(signUpRequest.getName());
        user.setEmail(signUpRequest.getEmail());

        String password = passwordEncoder.encode(signUpRequest.getPassword());
        user.setPassword(password);

        user.setPhone(signUpRequest.getPhone());
        String verificationToken = UUID.randomUUID().toString().replaceAll("-", "");
        user.setTokenVerification(verificationToken);
        user.setProfilePhoto(null);
        user.setPhotoFrontDocument(null);
        user.setPhotoBackDocument(null);

        String verificationLink = apiUrl + "/v1/users/verify-email/" + user.getTokenVerification();

        emailService.sendEmail(
                user.getEmail(),
                "Verify account",
                "To verify your account, click on the link: " + verificationLink
        );

        User savedUser = userRepository.save(user);
        return new SignUpResponseDTO(savedUser.getId());
    }
}
