package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.dto.reservation.ReviewToCustomerRequestDTO;
import org.example.projeto_trainee.dto.reservation.ReviewToProviderRequestDTO;
import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.exceptions.UnauthorizedException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional
public class ReviewReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public void reviewToCustomer(ReviewToCustomerRequestDTO requestDTO, String token) {
        UUID userId = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        Reservation reservation = reservationRepository.findById(requestDTO.getReservationId()).orElseThrow(
                () -> new NotFoundException("Reservation not found")
        );

        if (user.equals(reservation.getService().getUser())) { // Request user should be the service provider
            throw new UnauthorizedException("User can't access this route");
        }

        reservation.setCommentReviewToCustomer(requestDTO.getCommentReviewForCustomer());
        reservation.setNoteReviewToCustomer(requestDTO.getNoteReviewForCustomer());
        reservationRepository.save(reservation);
    }

    public void reviewToProvider(ReviewToProviderRequestDTO requestDTO, String token) {
        UUID userId = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );


        Reservation reservation = reservationRepository.findById(requestDTO.getReservationId()).orElseThrow(
                () -> new NotFoundException("Reservation not found")
        );

        if (!user.equals(reservation.getUser())) { // Request user should be the reservation user (Customer)
            throw new UnauthorizedException("User can't access this route");
        }

        reservation.setCommentReviewToProvider(requestDTO.getCommentReviewToProvider());
        reservation.setNoteReviewToProvider(requestDTO.getNoteReviewToProvider());
        reservationRepository.save(reservation);
    }
}
