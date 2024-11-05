package org.example.projeto_trainee.repositories;

import java.util.Optional;
import java.util.UUID;

import org.example.projeto_trainee.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,UUID> {
    Optional<User> findByEmail(String email);
    Optional<User> findByTokenVerification(String tokenVerification);
    Optional<User> findByChangePasswordToken(String changePasswordToken);
}
