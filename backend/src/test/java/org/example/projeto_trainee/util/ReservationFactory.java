package org.example.projeto_trainee.util;


import org.example.projeto_trainee.dto.reservation.CreateReservationRequestDTO;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.enums.PeriodEnum;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.UUID;

public class ReservationFactory {

    public static CreateReservationRequestDTO createNewValidReservation(ServiceEntity service) {

        LocalDateTime startTime;
        LocalDateTime endTime;
        if (service.getServiceDays().get(0).getPeriod().equals(PeriodEnum.Manha)) {
            startTime = service.getServiceDays().get(0).getDate().atTime(6, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(8, 0);
        } else {
            startTime = service.getServiceDays().get(0).getDate().atTime(13, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(15, 0);
        }

        CreateReservationRequestDTO createReservationRequestDTO = new CreateReservationRequestDTO();
        createReservationRequestDTO.setServiceId(service.getId());
        createReservationRequestDTO.setStartTime(startTime);
        createReservationRequestDTO.setEndTime(endTime);
        createReservationRequestDTO.setComment("Comment");
        createReservationRequestDTO.setCep("09755408");
        createReservationRequestDTO.setCity("City");
        createReservationRequestDTO.setState("state");
        createReservationRequestDTO.setNeighborhood("Neighborhood");
        createReservationRequestDTO.setStreet("Street");
        createReservationRequestDTO.setNumber(101);

        return createReservationRequestDTO;
    }

    public static CreateReservationRequestDTO invalidUserReservation(ServiceEntity service) {

        LocalDateTime startTime;
        LocalDateTime endTime;
        if (service.getServiceDays().get(0).getPeriod().equals(PeriodEnum.Manha)) {
            startTime = service.getServiceDays().get(0).getDate().atTime(6, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(8, 0);
        } else {
            startTime = service.getServiceDays().get(0).getDate().atTime(13, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(15, 0);
        }

        CreateReservationRequestDTO createReservationRequestDTO = new CreateReservationRequestDTO();
        createReservationRequestDTO.setServiceId(service.getId());
        createReservationRequestDTO.setStartTime(startTime);
        createReservationRequestDTO.setEndTime(endTime);
        createReservationRequestDTO.setComment("Comment");
        createReservationRequestDTO.setCep("09755408");
        createReservationRequestDTO.setCity("City");
        createReservationRequestDTO.setState("state");
        createReservationRequestDTO.setNeighborhood("Neighborhood");
        createReservationRequestDTO.setStreet("Street");
        createReservationRequestDTO.setNumber(101);

        return createReservationRequestDTO;
    }

    public static CreateReservationRequestDTO invalidServiceReservation(ServiceEntity service) {

        LocalDateTime startTime;
        LocalDateTime endTime;
        if (service.getServiceDays().get(0).getPeriod().equals(PeriodEnum.Manha)) {
            startTime = service.getServiceDays().get(0).getDate().atTime(6, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(8, 0);
        } else {
            startTime = service.getServiceDays().get(0).getDate().atTime(13, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(15, 0);
        }

        CreateReservationRequestDTO createReservationRequestDTO = new CreateReservationRequestDTO();
        createReservationRequestDTO.setServiceId(UUID.randomUUID());
        createReservationRequestDTO.setStartTime(startTime);
        createReservationRequestDTO.setEndTime(endTime);
        createReservationRequestDTO.setComment("Comment");
        createReservationRequestDTO.setCep("09755408");
        createReservationRequestDTO.setCity("City");
        createReservationRequestDTO.setState("state");
        createReservationRequestDTO.setNeighborhood("Neighborhood");
        createReservationRequestDTO.setStreet("Street");
        createReservationRequestDTO.setNumber(101);

        return createReservationRequestDTO;
    }

    public static CreateReservationRequestDTO invalidTimeReservation(ServiceEntity service) {

        LocalDateTime startTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);
        LocalDateTime endTime = LocalDateTime.of(LocalDate.now(), LocalTime.MIDNIGHT);

        CreateReservationRequestDTO createReservationRequestDTO = new CreateReservationRequestDTO();
        createReservationRequestDTO.setServiceId(service.getId());
        createReservationRequestDTO.setStartTime(startTime);
        createReservationRequestDTO.setEndTime(endTime);
        createReservationRequestDTO.setComment("Comment");
        createReservationRequestDTO.setCep("09755408");
        createReservationRequestDTO.setCity("City");
        createReservationRequestDTO.setState("state");
        createReservationRequestDTO.setNeighborhood("Neighborhood");
        createReservationRequestDTO.setStreet("Street");
        createReservationRequestDTO.setNumber(101);

        return createReservationRequestDTO;
    }

    public static CreateReservationRequestDTO invalidAddressReservation(ServiceEntity service) {

        LocalDateTime startTime;
        LocalDateTime endTime;
        if (service.getServiceDays().get(0).getPeriod().equals(PeriodEnum.Manha)) {
            startTime = service.getServiceDays().get(0).getDate().atTime(6, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(8, 0);
        } else {
            startTime = service.getServiceDays().get(0).getDate().atTime(13, 0);
            endTime = service.getServiceDays().get(0).getDate().atTime(15, 0);
        }

        CreateReservationRequestDTO createReservationRequestDTO = new CreateReservationRequestDTO();
        createReservationRequestDTO.setServiceId(service.getId());
        createReservationRequestDTO.setStartTime(startTime);
        createReservationRequestDTO.setEndTime(endTime);
        createReservationRequestDTO.setComment("Comment");
        createReservationRequestDTO.setCep("09755");
        createReservationRequestDTO.setCity("");
        createReservationRequestDTO.setState("");
        createReservationRequestDTO.setNeighborhood("");
        createReservationRequestDTO.setStreet("");
        createReservationRequestDTO.setNumber(null);

        return createReservationRequestDTO;
    }
}
