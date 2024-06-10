package dev.senzalla.metakyasshuapi.repository;

import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.types.TypeCategory;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findByNameCategory(String nameCategory);

    @Query("SELECT c FROM Category c " +
            "WHERE :user MEMBER OF c.users " +
            "AND :type IS NULL OR c.typeCategory = :type " +
            "OR c.users IS EMPTY ")
    List<Category> findAllAndUser(User user, TypeCategory type);
}