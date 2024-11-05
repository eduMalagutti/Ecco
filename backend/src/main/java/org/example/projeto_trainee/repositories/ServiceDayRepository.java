package org.example.projeto_trainee.repositories;

import org.example.projeto_trainee.entities.ServiceDay;
import org.example.projeto_trainee.entities.ServiceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ServiceDayRepository extends JpaRepository<ServiceDay, UUID> {

    Optional<ServiceDay> findByServiceAndDate(ServiceEntity service, LocalDate date);

    Boolean existsByDate(LocalDate date);

    void deleteAllByService(ServiceEntity service);
}
