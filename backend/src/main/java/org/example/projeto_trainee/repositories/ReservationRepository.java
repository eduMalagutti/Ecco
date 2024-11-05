package org.example.projeto_trainee.repositories;

import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.enums.StatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, UUID> {

    List<Reservation> findAllByStatus(StatusEnum status);

    List<Reservation> findAllByUserId(UUID userId);
}
