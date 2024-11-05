package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.dto.reservation.CancelReservationDTO;
import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.enums.StatusEnum;
import org.example.projeto_trainee.exceptions.BadRequestException;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.exceptions.UnauthorizedException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class CancelReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    public CancelReservationDTO execute (UUID id, String token){
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation entity not found")
        );

        UUID userid = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userid).orElseThrow(()-> new UnauthorizedException("User not found"));

        boolean IsNotProviderAndIsNotCustomer = !user.equals(reservation.getService().getUser()) && !user.equals(reservation.getUser());

        if (IsNotProviderAndIsNotCustomer){
            throw new UnauthorizedException("You are not authorized to perform this action");
        }

        if (reservation.getStatus().equals(StatusEnum.CANCELADA)){
            throw new RuntimeException("Reservation has been cancelled");
        }
        if (!reservation.getStatus().equals(StatusEnum.ACEITA)){
            throw new RuntimeException("This reservation was not accepted");
        }



        reservation.setStatus(StatusEnum.CANCELADA);
        reservationRepository.save(reservation);
        return new CancelReservationDTO("Reservation Canceled");
    }
}
