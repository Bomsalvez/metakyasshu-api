package dev.senzalla.metakyasshuapi.model.category.entity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryUserRepository extends JpaRepository<CategoryUser, Long> {
}