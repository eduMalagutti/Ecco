package org.example.projeto_trainee.services.service_entity;

import org.example.projeto_trainee.dto.service_entity.SearchServiceResponseDTO;
import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.entities.SubCategory;
import org.example.projeto_trainee.exceptions.NotFoundException;
import org.example.projeto_trainee.repositories.ServiceDayRepository;
import org.example.projeto_trainee.repositories.ServiceEntityRepository;
import org.example.projeto_trainee.repositories.SubCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.PagedModel;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SearchServiceEntitiesUseCase {

    @Autowired
    private ServiceEntityRepository serviceEntityRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ServiceDayRepository serviceDayRepository;


    public PagedModel<SearchServiceResponseDTO> execute(String subCategoryName, LocalDate date, int page, int size) {

        SubCategory subCategory = subCategoryRepository.findByName(subCategoryName).orElseThrow(
                () -> new NotFoundException("Sub Category do not exist")
        );

        if (!serviceDayRepository.existsByDate(date)) {
            throw new NotFoundException("No service available in this date");
        }

        List<ServiceEntity> servicesInSubCategory = serviceEntityRepository.findAllBySubCategory(subCategory);
        if (servicesInSubCategory.isEmpty()) {
            throw new NotFoundException("No service available for this sub category");
        }

        Pageable pageable = PageRequest.of(page, size);
        Page<ServiceEntity> servicesInSubCategoryPage = serviceEntityRepository.findAllBySubCategory(subCategory, pageable);

        List<SearchServiceResponseDTO> responseList = servicesInSubCategoryPage.getContent().stream()
                .flatMap(serviceEntity -> serviceEntity.getServiceDays().stream()
                        .filter(serviceDay -> serviceDay.getDate().equals(date))
                        .map(serviceDay -> mapToResponseDTO(serviceEntity, serviceDay)))
                .collect(Collectors.toList());

        PagedModel.PageMetadata pageMetadata = new PagedModel.PageMetadata(
                pageable.getPageSize(),
                pageable.getPageNumber(),
                servicesInSubCategoryPage.getTotalElements(),
                servicesInSubCategoryPage.getTotalPages()
        );

        return PagedModel.of(responseList, pageMetadata);

    }

    private static SearchServiceResponseDTO mapToResponseDTO(ServiceEntity service, ServiceDay day) {
        SearchServiceResponseDTO responseService = new SearchServiceResponseDTO();
        responseService.setId(service.getId());
        responseService.setProviderName(service.getUser().getName());
        responseService.setProviderRating(service.getUser().getRating());
        responseService.setFixedPrice(service.getFixedPrice());
        responseService.setMaxPrice(service.getMaxPrice());
        responseService.setMinPrice(service.getMinPrice());
        responseService.setSubCategoryName(service.getSubCategory().getName());
        responseService.setCategoryName(service.getSubCategory().getCategory().getName());
        responseService.setPeriod(day.getPeriod());
        responseService.setDate(day.getDate());
        return responseService;
    }
}
