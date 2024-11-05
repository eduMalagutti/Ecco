package org.example.projeto_trainee.dto.service_entity;

import lombok.Getter;
import lombok.Setter;
import org.example.projeto_trainee.dto.service_day.ServiceDayDTO;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class GetServiceResponseDTO {

    private UUID id;

    private UUID userId;

    private String userName;

    private Double userRating;

    private String categoryName;

    private String subCategoryName;

    private Double fixedPrice;

    private Double minPrice;

    private Double maxPrice;

    private List<ServiceDayDTO> serviceDays;

    public GetServiceResponseDTO(UUID id, UUID userId, String userName, Double userRating, String categoryName,
                                 String subCategoryName, Double fixedPrice, Double minPrice, Double maxPrice, List<ServiceDayDTO> serviceDays) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.userRating = userRating;
        this.categoryName = categoryName;
        this.subCategoryName = subCategoryName;
        this.fixedPrice = fixedPrice;
        this.minPrice = minPrice;
        this.maxPrice = maxPrice;
        this.serviceDays = serviceDays;
    }
}
