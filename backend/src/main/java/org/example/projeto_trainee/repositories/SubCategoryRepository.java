package org.example.projeto_trainee.repositories;

import org.example.projeto_trainee.entities.SubCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface SubCategoryRepository extends JpaRepository<SubCategory, UUID> {

    Optional<SubCategory> findByName(String name);

    Optional<SubCategory> findByNameAndCategory_Name(String name, String categoryName);
}
