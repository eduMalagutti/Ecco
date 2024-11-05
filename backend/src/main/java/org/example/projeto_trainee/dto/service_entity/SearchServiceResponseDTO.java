package org.example.projeto_trainee.dto.service_entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.projeto_trainee.enums.PeriodEnum;

import java.time.LocalDate;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SearchServiceResponseDTO {

    private UUID id;

    private PeriodEnum period;

    private LocalDate date;

    private String providerName;

    private Double providerRating;

    private Double fixedPrice;

    private Double minPrice;

    private Double maxPrice;

    private String categoryName;

    private String subCategoryName;


}
