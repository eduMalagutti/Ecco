package org.example.projeto_trainee.repositories;

import org.example.projeto_trainee.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CategoryRepository extends JpaRepository<Category, UUID> {
}
