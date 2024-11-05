package org.example.projeto_trainee.util;


import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;
import org.example.projeto_trainee.dto.service_entity.CreateServiceRequestDTO;
import org.example.projeto_trainee.enums.PeriodEnum;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ServiceFactory {

    public static CreateServiceRequestDTO createNewValidService() {
        CreateServiceRequestDTO createServiceRequestDTO = new CreateServiceRequestDTO();
        createServiceRequestDTO.setCategoryName("MANUTENCAO");
        createServiceRequestDTO.setSubCategoryName("ELÉTRICA");
        createServiceRequestDTO.setFixedPrice(180D);
        List<ServiceDayDTO> serviceDays = new ArrayList<>();
        ServiceDayDTO day1 = new ServiceDayDTO(
                LocalDate.now(),
                PeriodEnum.Tarde
        );
        ServiceDayDTO day2 = new ServiceDayDTO(
                LocalDate.now().plusDays(1),
                PeriodEnum.Manha
        );
        serviceDays.add(day1);
        serviceDays.add(day2);
        createServiceRequestDTO.setServiceDays(serviceDays);
        return createServiceRequestDTO;
    }

    public static CreateServiceRequestDTO invalidUserService() {
        CreateServiceRequestDTO createServiceRequestDTO = new CreateServiceRequestDTO();
        createServiceRequestDTO.setCategoryName("MANUTENCAO");
        createServiceRequestDTO.setSubCategoryName("ELÉTRICA");
        createServiceRequestDTO.setFixedPrice(180D);
        List<ServiceDayDTO> serviceDays = new ArrayList<>();
        ServiceDayDTO day1 = new ServiceDayDTO(
                LocalDate.now(),
                PeriodEnum.Tarde
        );
        ServiceDayDTO day2 = new ServiceDayDTO(
                LocalDate.now().plusDays(1),
                PeriodEnum.Manha
        );
        serviceDays.add(day1);
        serviceDays.add(day2);
        createServiceRequestDTO.setServiceDays(serviceDays);
        return createServiceRequestDTO;
    }

    public static CreateServiceRequestDTO invalidSubCategoryService() {
        CreateServiceRequestDTO createServiceRequestDTO = new CreateServiceRequestDTO();
        createServiceRequestDTO.setCategoryName("MANUTENCAO");
        createServiceRequestDTO.setSubCategoryName("CONCERTO");
        createServiceRequestDTO.setFixedPrice(180D);
        List<ServiceDayDTO> serviceDays = new ArrayList<>();
        ServiceDayDTO day1 = new ServiceDayDTO(
                LocalDate.now(),
                PeriodEnum.Tarde
        );
        ServiceDayDTO day2 = new ServiceDayDTO(
                LocalDate.now().plusDays(1),
                PeriodEnum.Manha
        );
        serviceDays.add(day1);
        serviceDays.add(day2);
        createServiceRequestDTO.setServiceDays(serviceDays);
        return createServiceRequestDTO;
    }

    public static CreateServiceRequestDTO invalidPriceService() {
        CreateServiceRequestDTO createServiceRequestDTO = new CreateServiceRequestDTO();
        createServiceRequestDTO.setCategoryName("MANUTENCAO");
        createServiceRequestDTO.setSubCategoryName("ELÉTRICA");
        createServiceRequestDTO.setMinPrice(400D);
        createServiceRequestDTO.setMaxPrice(200D);
        List<ServiceDayDTO> serviceDays = new ArrayList<>();
        ServiceDayDTO day1 = new ServiceDayDTO(
                LocalDate.now(),
                PeriodEnum.Tarde
        );
        ServiceDayDTO day2 = new ServiceDayDTO(
                LocalDate.now().plusDays(1),
                PeriodEnum.Manha
        );
        serviceDays.add(day1);
        serviceDays.add(day2);
        createServiceRequestDTO.setServiceDays(serviceDays);
        return createServiceRequestDTO;
    }

    public static CreateServiceRequestDTO invalidDateService() {
        CreateServiceRequestDTO createServiceRequestDTO = new CreateServiceRequestDTO();
        createServiceRequestDTO.setSubCategoryName("");
        createServiceRequestDTO.setFixedPrice(200D);
        List<ServiceDayDTO> serviceDays = new ArrayList<>();
        ServiceDayDTO day1 = new ServiceDayDTO(
                LocalDate.now(),
                PeriodEnum.Tarde
        );
        ServiceDayDTO day2 = new ServiceDayDTO(
                LocalDate.now().plusDays(10),
                PeriodEnum.Manha
        );
        serviceDays.add(day1);
        serviceDays.add(day2);
        createServiceRequestDTO.setServiceDays(serviceDays);
        return createServiceRequestDTO;
    }
}
