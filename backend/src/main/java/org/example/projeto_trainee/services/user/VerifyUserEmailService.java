package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.dto.user.VerifyRequestDTO;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VerifyUserEmailService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public void verifyAccount(VerifyRequestDTO verifyRequestDTO) {
        User user = userRepository.findByTokenVerification(verifyRequestDTO.getVerificationToken())
                .orElseThrow(() -> new NotFoundException("User not found"));
        user.setVerified(true);
        userRepository.save(user);
    }
}
