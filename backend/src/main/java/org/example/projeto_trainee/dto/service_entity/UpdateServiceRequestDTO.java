package org.example.projeto_trainee.dto.service_entity;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projeto_trainee.entities.ServiceDay;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateServiceRequestDTO {

    @NotNull
    private String categoryName;

    @NotNull
    private String subCategoryName;

    private Double fixedPrice;

    private Double minPrice;

    private Double maxPrice;

    private List<ServiceDay> serviceDays;
}
