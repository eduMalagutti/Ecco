package org.example.projeto_trainee.services.user;

import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserRatingService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public Double getUserRating(User user) {
        List<Reservation> userReservations = reservationRepository.findAllByUserId(user.getId());

        double userRating = 0D;

        for (Reservation reservation : userReservations) {
            User Customer = reservation.getUser();

            if (user.equals(Customer)) {
                if (reservation.getNoteReviewToCustomer() == null) {
                    continue;
                }
                userRating += reservation.getNoteReviewToCustomer().doubleValue();
            } else { // User equals provider
                if (reservation.getNoteReviewToProvider() == null) {
                    continue;
                }
                userRating += reservation.getNoteReviewToProvider().doubleValue();
            }
        }

        userRating = userRating / userReservations.size();

        return userRating;
    }

    public Double getUserRating(String token) {
        UUID id = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("User not found"));

        return getUserRating(user);
    }
}