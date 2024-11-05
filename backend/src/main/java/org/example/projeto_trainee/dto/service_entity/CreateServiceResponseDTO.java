package org.example.projeto_trainee.dto.service_entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;

import java.util.List;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateServiceResponseDTO {

    private UUID id;

    private Double fixedPrice;

    private Double minPrice;

    private Double maxPrice;

    private String categoryName;

    private String subCategoryName;

    private List<ServiceDayDTO> serviceDays;
}
