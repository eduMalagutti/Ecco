package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.dto.reservation.GetReservationResponseDTO;
import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class GetReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    public GetReservationResponseDTO execute(UUID id) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation entity not found")
        );

        return new GetReservationResponseDTO(
                reservation.getId(),
                reservation.getUser().getId(),
                reservation.getService().getId(),
                reservation.getRejectionReason(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getNumber(),
                reservation.getStreet(),
                reservation.getNeighborhood(),
                reservation.getCity(),
                reservation.getState(),
                reservation.getCep(),
                reservation.getComment(),
                reservation.getRequestDate(),
                reservation.getCommentReviewToProvider(),
                reservation.getNoteReviewToProvider(),
                reservation.getCommentReviewToCustomer(),
                reservation.getNoteReviewToCustomer(),
                reservation.getStatus()
        );
    }
}
