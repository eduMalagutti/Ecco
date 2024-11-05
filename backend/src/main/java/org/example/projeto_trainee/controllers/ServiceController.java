package org.example.projeto_trainee.controllers;

import jakarta.validation.Valid;
import org.example.projeto_trainee.dto.service_entity.CreateServiceRequestDTO;
import org.example.projeto_trainee.dto.service_entity.CreateServiceResponseDTO;
import org.example.projeto_trainee.dto.service_entity.GetServiceResponseDTO;
import org.example.projeto_trainee.dto.service_entity.SearchServiceResponseDTO;
import org.example.projeto_trainee.services.service_entity.CreateServiceEntityService;
import org.example.projeto_trainee.services.service_entity.DeleteServiceEntityService;
import org.example.projeto_trainee.services.service_entity.GetServiceEntityService;
import org.example.projeto_trainee.services.service_entity.SearchServiceEntitiesUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.UUID;

@RestController
@RequestMapping ("/v1/services")
public class ServiceController {

    @Autowired
    CreateServiceEntityService createServiceEntityService;

    @Autowired
    DeleteServiceEntityService deleteServiceEntityService;

    @Autowired
    SearchServiceEntitiesUseCase searchServiceEntitiesUseCase;

    @Autowired
    GetServiceEntityService getServiceEntityService;

    @GetMapping ("/paged-search")
    public ResponseEntity<PagedModel<SearchServiceResponseDTO>> searchServices(@RequestParam String subCategory,
                                                                               @RequestParam @DateTimeFormat (iso = DateTimeFormat.ISO.DATE) LocalDate date,
                                                                               @RequestParam int page,
                                                                               @RequestParam int size) {

        PagedModel<SearchServiceResponseDTO> response = searchServiceEntitiesUseCase.execute(subCategory, date, page, size);

        return ResponseEntity.status(HttpStatus.OK).body(response);

    }

    @PostMapping ("/create")
    public ResponseEntity<CreateServiceResponseDTO> createService(@Valid @RequestBody CreateServiceRequestDTO request,
                                                                  @RequestHeader("Authorization") String token) {
        CreateServiceResponseDTO response = createServiceEntityService.execute(request, token);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping ("delete/{id}")
    public ResponseEntity<?> deleteService(@PathVariable UUID id) {
        deleteServiceEntityService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping ("/{id}")
    public ResponseEntity<GetServiceResponseDTO> getService(@PathVariable UUID id) {
        GetServiceResponseDTO response = getServiceEntityService.execute(id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}


