package org.example.projeto_trainee.services.reservation;

import org.example.projeto_trainee.dto.reservation.CreateReservationRequestDTO;
import org.example.projeto_trainee.dto.reservation.CreateReservationResponseDTO;
import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.enums.PeriodEnum;
import org.example.projeto_trainee.enums.StatusEnum;
import org.example.projeto_trainee.exceptions.BadRequestException;
import org.example.projeto_trainee.exceptions.InvalidPeriodException;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.example.projeto_trainee.repositories.ServiceDayRepository;
import org.example.projeto_trainee.repositories.ServiceEntityRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

@Service
public class CreateReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @Autowired
    private ServiceDayRepository serviceDayRepository;

    @Autowired
    private JWTProvider jwtProvider;

    public CreateReservationResponseDTO execute(CreateReservationRequestDTO request, String token) {
        UUID userid = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userid).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        ServiceEntity serviceEntity = serviceEntityRepository.findById(request.getServiceId()).orElseThrow(
                () -> new NotFoundException("Service not found")
        );

        if (user.equals(serviceEntity.getUser())) { // User is customer and provider
            throw new BadRequestException("Customer and provider can not be the same user");
        }

        ServiceDay serviceDay = serviceDayRepository.findByServiceAndDate(
                        serviceEntity,
                        request.getStartTime().toLocalDate())
                .orElseThrow(() -> new NotFoundException("The service is not available at this time"));

        validatePeriod(serviceDay.getPeriod(), request.getStartTime(), request.getEndTime());

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setService(serviceEntity);

        reservation.setStartTime(request.getStartTime());
        reservation.setEndTime(request.getEndTime());

        reservation.setNumber(request.getNumber());
        reservation.setStreet(request.getStreet());
        reservation.setNeighborhood(request.getNeighborhood());
        reservation.setCity(request.getCity());
        reservation.setState(request.getState());
        reservation.setCep(request.getCep());

        reservation.setComment(request.getComment());
        reservation.setRequestDate(LocalDateTime.now().toLocalDate());
        reservation.setStatus(StatusEnum.SOLICITADA);

        reservation.setRejectionReason(null);

        reservationRepository.save(reservation);

        return new CreateReservationResponseDTO(
                reservation.getId(),
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
                reservation.getStatus(),
                reservation.getCommentReviewToProvider(),
                reservation.getNoteReviewToProvider(),
                reservation.getCommentReviewToCustomer(),
                reservation.getNoteReviewToCustomer(),
                reservation.getService().getId()
        );
    }

    private void validatePeriod(PeriodEnum period, LocalDateTime startTime, LocalDateTime endTime) {
        if (!startTime.toLocalDate().isEqual(endTime.toLocalDate())) {
            throw new InvalidPeriodException("start time and end time should be in the same day");
        }

        boolean timeIsNotInTheMorning =
                startTime.isBefore(LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(6, 0))) ||
                        endTime.isAfter(LocalDateTime.of(endTime.toLocalDate(), LocalTime.of(12, 0)));
        boolean timeIsNotInTheAfternoon =
                startTime.isBefore(LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(12, 0))) ||
                        endTime.isAfter(LocalDateTime.of(endTime.toLocalDate(), LocalTime.of(18, 0)));
        boolean timeIsInvalid =
                startTime.isBefore(LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(6, 0))) ||
                        endTime.isAfter(LocalDateTime.of(startTime.toLocalDate(), LocalTime.of(18, 0)));

        switch (period) {
            case Manha:
                if (timeIsNotInTheMorning) {
                    throw new InvalidPeriodException(
                            "Invalid time for morning period. Period informed must be between 06:00 - 12:00");
                }
                break;
            case Tarde:
                if (timeIsNotInTheAfternoon) {
                    throw new InvalidPeriodException(
                            "Invalid time for the afternoon period. Period informed must be between 12:00 - 18:00");
                }
                break;
            case DiaInteiro:
                if (timeIsInvalid) {
                    throw new InvalidPeriodException(
                            "Invalid time. Period entered must be between 06:00 - 18:00");
                }
                break;
            default:
                throw new InvalidPeriodException("Invalid Period");
        }
    }


}
