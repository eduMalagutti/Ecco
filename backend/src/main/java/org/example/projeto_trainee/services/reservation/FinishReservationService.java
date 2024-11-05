package org.example.projeto_trainee.services.reservation;

import jakarta.annotation.PostConstruct;
import org.example.projeto_trainee.entities.Reservation;
import org.example.projeto_trainee.enums.StatusEnum;
import org.example.projeto_trainee.repositories.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;
import java.util.logging.Logger;

@Service
@Transactional
public class FinishReservationService {

    private final Logger logger = Logger.getLogger(FinishReservationService.class.toString());

    @Autowired
    private TaskScheduler taskScheduler;

    @Autowired
    ReservationRepository reservationRepository;

    @PostConstruct
    public void scheduleAllFinishReservationTask() {
        List<Reservation> reservations = reservationRepository.findAllByStatus(StatusEnum.ACEITA);
        for (Reservation reservation : reservations) {
            scheduleFinishReservationTask(reservation);
        }
    }

    public void scheduleFinishReservationTask(Reservation reservation) {
        LocalDateTime endTime = reservation.getEndTime();
        Instant endReservationinstant = endTime.atZone(ZoneId.systemDefault()).toInstant();

        taskScheduler.schedule(() -> finishReservation(reservation), endReservationinstant);
        logger.info("Reservation: " + reservation.getId() + " set to end at: " + reservation.getEndTime());
    }

    public void finishReservation(Reservation reservation) {
        if (reservation.getStatus() == StatusEnum.CANCELADA) {
            return;
        }

        reservation.setStatus(StatusEnum.CONCLUIDA);
        reservationRepository.save(reservation);

        logger.info("Reservation " + reservation.getId() + " set as ended");
    }
}
