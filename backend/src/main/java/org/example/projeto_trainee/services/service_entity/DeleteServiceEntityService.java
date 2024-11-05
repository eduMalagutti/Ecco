package org.example.projeto_trainee.services.service_entity;

import org.example.projeto_trainee.repositories.ServiceEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class DeleteServiceEntityService {

    @Autowired
    ServiceEntityRepository serviceEntityRepository;

    public void delete(UUID id) {
        serviceEntityRepository.deleteById(id);
    }
}
