package org.example.projeto_trainee.services.service_entity;

import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;
import org.example.projeto_trainee.dto.service_entity.UpdateServiceRequestDTO;
import org.example.projeto_trainee.dto.service_entity.UpdateServiceResponseDTO;
import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.entities.SubCategory;
import org.example.projeto_trainee.entities.User;
import org.example.projeto_trainee.exceptions.BadRequestException;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.repositories.ServiceDayRepository;
import org.example.projeto_trainee.repositories.ServiceEntityRepository;
import org.example.projeto_trainee.repositories.SubCategoryRepository;
import org.example.projeto_trainee.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Service
public class UpdateServiceEntityService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    ServiceEntityRepository serviceEntityRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    @Autowired
    ServiceDayRepository serviceDayRepository;

    @Transactional
    public UpdateServiceResponseDTO execute(UUID userId, UUID serviceId, UpdateServiceRequestDTO requestDTO) {
        ServiceEntity updatedService = serviceEntityRepository.findById(serviceId).orElseThrow(
                () -> new NotFoundException("Service not found")
        );

        User user = userRepository.findById(userId).orElseThrow(
                () -> new NotFoundException("User not found")
        );

        SubCategory newSubCategory = subCategoryRepository.findByNameAndCategory_Name(
                requestDTO.getSubCategoryName(),
                requestDTO.getCategoryName()).orElseThrow(
                () -> new NotFoundException("SubCategory not found")
        );

        // Updating the sub category
        updatedService.setSubCategory(newSubCategory);

        // Updating prices
        if (requestDTO.getFixedPrice() != null) {
            updatedService.setFixedPrice(requestDTO.getFixedPrice());
        } else if (requestDTO.getMaxPrice() > requestDTO.getMinPrice()) {
            updatedService.setMaxPrice(requestDTO.getMaxPrice());
            updatedService.setMinPrice(requestDTO.getMinPrice());
        } else {
            throw new BadRequestException("Maximum price should be bigger than minimum price");
        }

        // Creating a list with the new serviceDays
        List<ServiceDay> newServiceDayList = requestDTO.getServiceDays();

        if (newServiceDayList.size() > 4) {
            throw new BadRequestException("Too many serviceDays");
        }

        // Verifying and setting the updatedService as each serviceDay service field
        //TODO: talvez precise refatorar
        for (ServiceDay serviceDay : newServiceDayList) {
            if (serviceDay.getDate().isAfter(LocalDate.now().plusDays(3))) {
                throw new BadRequestException("Service Day Date is after 4 days");
            }
            serviceDay.setService(updatedService);
            System.out.println("Saving service day: " + serviceDay);
        }

        // Delete all existing service days
        serviceDayRepository.deleteAllByService(updatedService);

        // Save new service days
        serviceDayRepository.saveAll(newServiceDayList);

        // Clear the existing service days and add new ones
        updatedService.getServiceDays().clear();
        updatedService.getServiceDays().addAll(newServiceDayList);

        // Save the updated service entity
        serviceEntityRepository.save(updatedService);

        List<ServiceDayDTO> serviceDayDTOS = newServiceDayList.stream()
                .map(serviceDay ->
                        new ServiceDayDTO(
                                serviceDay.getDate(),
                                serviceDay.getPeriod()))
                .toList();

        return new UpdateServiceResponseDTO(
                updatedService.getId(),
                updatedService.getFixedPrice(),
                updatedService.getMinPrice(),
                updatedService.getMaxPrice(),
                updatedService.getSubCategory().getCategory().getName(),
                updatedService.getSubCategory().getName(),
                updatedService.getUser().getId(),
                serviceDayDTOS
        );
    }

}
