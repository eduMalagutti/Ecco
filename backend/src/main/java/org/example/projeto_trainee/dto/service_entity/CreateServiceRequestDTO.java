package org.example.projeto_trainee.dto.service_entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateServiceRequestDTO {

    @NotNull
    private String categoryName;

    @NotNull
    private String subCategoryName;

    private Double fixedPrice;

    private Double minPrice;

    private Double maxPrice;

    @NotNull
    private List<ServiceDayDTO> serviceDays;
}
