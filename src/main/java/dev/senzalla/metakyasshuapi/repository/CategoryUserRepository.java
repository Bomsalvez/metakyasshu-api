package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.category.entity.CategoryUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryUserRepository extends JpaRepository<CategoryUser, Long> {
}