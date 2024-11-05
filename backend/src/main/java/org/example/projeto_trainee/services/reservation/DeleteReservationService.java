package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    public void deleteById(UUID id) {
        if (!reservationRepository.existsById(id)) {
            throw new NotFoundException("Reserva não encontrada.");
        }
        reservationRepository.deleteById(id);
    }
}

