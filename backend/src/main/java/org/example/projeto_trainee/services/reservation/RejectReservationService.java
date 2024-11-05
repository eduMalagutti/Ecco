package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.dto.reservation.RejectReservationDTO;
import org.example.projeto_trainee.dto.reservation.RejectReservationRequestDTO;
import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.enums.StatusEnum;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.exceptions.UnauthorizedException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RejectReservationService {
    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;


    public RejectReservationDTO execute (RejectReservationRequestDTO request, String token){
        Reservation reservation = reservationRepository.findById(request.getId()).orElseThrow(
                () -> new NotFoundException("Reservation entity not found")
        );

        UUID userid = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userid).orElseThrow(()-> new UnauthorizedException("User not found"));

        boolean isNotProvider = !user.equals(reservation.getService().getUser());
        
        if (isNotProvider){
            throw new UnauthorizedException("You are not authorized to perform this action");
        }

        if (reservation.getStatus().equals(StatusEnum.REJEITADA)){
            throw new RuntimeException("Reservation has been rejected");
        }

        reservation.setStatus(StatusEnum.REJEITADA);
        reservation.setRejectionReason(request.getRejectionReason());
        reservationRepository.save(reservation);
        return new RejectReservationDTO("Reservation Reject");
    }
}
