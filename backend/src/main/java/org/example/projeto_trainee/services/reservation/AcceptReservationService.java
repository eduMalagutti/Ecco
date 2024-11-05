package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.dto.reservation.AcceptReservationDTO;
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

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class AcceptReservationService {

    @Autowired
    ReservationRepository reservationRepository;

    @Autowired
    FinishReservationService finishReservationService;

    @Autowired
    private JWTProvider jwtProvider;

    @Autowired
    private UserRepository userRepository;

    public AcceptReservationDTO execute(UUID id, String token) {
        Reservation reservation = reservationRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Reservation entity not found")
        );

        UUID userid = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userid).orElseThrow(()-> new UnauthorizedException("User not found"));

        boolean isNotProvider = !user.equals(reservation.getService().getUser());

        if (isNotProvider){
            throw new UnauthorizedException("You are not authorized to perform this action");
        }

        if (reservation.getStatus().equals(StatusEnum.ACEITA)) {
            return new AcceptReservationDTO("Reservation Accepted");
        }

        if (reservation.getStatus().equals(StatusEnum.CANCELADA)) {
            throw new BadRequestException("Reservation has been canceled");
        }

        if (LocalDateTime.now().isAfter(reservation.getEndTime())) {
            throw new BadRequestException("Reservation has already ended");
        }

        reservation.setStatus(StatusEnum.ACEITA);
        reservationRepository.save(reservation);

        finishReservationService.scheduleFinishReservationTask(reservation);

        return new AcceptReservationDTO("Reservation Accepted");
    }
}
