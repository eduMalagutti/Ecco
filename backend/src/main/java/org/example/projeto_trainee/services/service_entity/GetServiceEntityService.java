package org.example.projeto_trainee.services.service_entity;

import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;
import org.example.projeto_trainee.dto.service_entity.GetServiceResponseDTO;
import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.repositories.ServiceEntityRepository;
import org.example.projeto_trainee.services.user.UserRatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class GetServiceEntityService {

    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @Autowired
    private UserRatingService userRatingService;

    public GetServiceResponseDTO execute(UUID id) {
        ServiceEntity service = serviceEntityRepository.findById(id).orElseThrow(
                () -> new NotFoundException("Service not found")
        );

        List<ServiceDay> serviceDayList = service.getServiceDays();
        List<ServiceDayDTO> serviceDayDTOS = new ArrayList<>();

        for (ServiceDay serviceDay : serviceDayList) {
            ServiceDayDTO serviceDayDTO = new ServiceDayDTO();
            serviceDayDTO.setDate(serviceDay.getDate());
            serviceDayDTO.setPeriod(serviceDay.getPeriod());
            serviceDayDTOS.add(serviceDayDTO);
        }

        return new GetServiceResponseDTO(
                service.getId(),
                service.getUser().getId(),
                service.getUser().getName(),
                userRatingService.getUserRating(service.getUser()),
                service.getSubCategory().getCategory().getName(),
                service.getSubCategory().getName(),
                service.getFixedPrice(),
                service.getMinPrice(),
                service.getMaxPrice(),
                serviceDayDTOS
        );
    }
}
