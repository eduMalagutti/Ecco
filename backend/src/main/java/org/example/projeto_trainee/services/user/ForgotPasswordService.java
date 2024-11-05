package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.ForgotPasswordCodeDTO;
import org.example.projeto_trainee.dto.user.ForgotPasswordRequestDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.exceptions.UnauthorizedException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.example.projeto_trainee.utils.RandomTokenGenerator.generateNumericToken;

@Service
public class ForgotPasswordService {

    @Autowired
    EmailService emailService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    JWTProvider jwtProvider;

    public void sendEmail(ForgotPasswordRequestDTO request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new NotFoundException("No user found with that email")
        );

        emailService.sendEmail(
                request.getEmail(),
                "Password recovery code",
                "Enter this code to create new password " +
                        createVerificationCode(user)
        );
    }

    public String createVerificationCode(User user) {

        String token = generateNumericToken(6);

        user.setChangePasswordToken(token);
        user.setChangePasswordTokenExpirationDate(Instant.now().plus(5, ChronoUnit.MINUTES));
        userRepository.save(user);

        return token;
    }

    public String verifyCode(ForgotPasswordCodeDTO request) {

        User user = this.userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        if (!user.getChangePasswordToken().equals(request.getCode()))
            throw new UnauthorizedException("Given code is invalid");

        Instant expirationDate = user.getChangePasswordTokenExpirationDate();
        if (Instant.now().isAfter(expirationDate))
            throw new UnauthorizedException("Code has expired");

        return this.jwtProvider.createToken(user);
    }
}
