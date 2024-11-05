package org.example.projeto_trainee.repositories;

import org.example.projeto_trainee.entities.ServiceEntity;
import org.example.projeto_trainee.entities.SubCategory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface ServiceEntityRepository extends JpaRepository<ServiceEntity, UUID> {

    List<ServiceEntity> findAllBySubCategory(SubCategory subCategory);

    Page<ServiceEntity> findAllBySubCategory(SubCategory subCategory, Pageable pageable);
}
