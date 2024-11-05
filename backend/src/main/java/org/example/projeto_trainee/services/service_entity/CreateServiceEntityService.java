package org.example.projeto_trainee.services.service_entity;

import jakarta.transaction.Transactional;
import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;
import org.example.projeto_trainee.dto.service_entity.CreateServiceRequestDTO;
import org.example.projeto_trainee.dto.service_entity.CreateServiceResponseDTO;
import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.entities.SubCategory;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.BadRequestException;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.providers.JWTProvider;
import org.example.projeto_trainee.repositories.ServiceDayRepository;
import org.example.projeto_trainee.repositories.ServiceEntityRepository;
import org.example.projeto_trainee.repositories.SubCategoryRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class CreateServiceEntityService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceEntityRepository serviceEntityRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ServiceDayRepository serviceDayRepository;

    @Autowired
    JWTProvider jwtProvider;

    public CreateServiceResponseDTO execute(CreateServiceRequestDTO requestDTO, String token) {
        UUID userId = jwtProvider.getUserIdFromToken(token);

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        SubCategory subCategory = subCategoryRepository.findByNameAndCategory_Name(
                requestDTO.getSubCategoryName(),
                requestDTO.getCategoryName()).orElseThrow(
                () -> new NotFoundException("SubCategory not found")
        );

        ServiceEntity serviceEntity = new ServiceEntity();
        serviceEntity.setUser(user);
        serviceEntity.setSubCategory(subCategory);

        if (requestDTO.getFixedPrice() != null) {
            serviceEntity.setFixedPrice(requestDTO.getFixedPrice());
        } else if (requestDTO.getMaxPrice() > requestDTO.getMinPrice()) {
            serviceEntity.setMaxPrice(requestDTO.getMaxPrice());
            serviceEntity.setMinPrice(requestDTO.getMinPrice());
        } else {
            throw new BadRequestException("Maximum price should be bigger than minimum price");
        }
        serviceEntityRepository.save(serviceEntity);

        List<ServiceDay> serviceDayList = requestDTO.getServiceDays()
                .stream().map(ServiceDayDTO::convertToServiceDay).toList();

        if (serviceDayList.size() > 4) {
            throw new BadRequestException("There should be lees than 4 days");
        }

        for (ServiceDay serviceDay : serviceDayList) {
            if (serviceDay.getDate().isAfter(LocalDate.now().plusDays(3))) { //TODO: maybe needs refactoring
                throw new BadRequestException("Service Day Date is after 4 days");
            }
            serviceDay.setService(serviceEntity);
        }

        serviceDayRepository.saveAll(serviceDayList);

        List<ServiceDayDTO> serviceDayDTOS = serviceDayList
                .stream()
                .map(ServiceDay::convertToServiceDayDTO).toList();
        System.out.println("oi-");

        return new CreateServiceResponseDTO(
                serviceEntity.getId(),
                serviceEntity.getFixedPrice(),
                serviceEntity.getMinPrice(),
                serviceEntity.getMaxPrice(),
                serviceEntity.getSubCategory().getCategory().getName(),
                serviceEntity.getSubCategory().getName(),
                serviceDayDTOS
        );
    }

}
