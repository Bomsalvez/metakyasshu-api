package dev.senzalla.metakyasshuapi.service.category;

import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.category.entity.CategoryUser;
import dev.senzalla.metakyasshuapi.model.category.mapper.CategoryMapper;
import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.CategoryRepository;
import dev.senzalla.metakyasshuapi.repository.CategoryUserRepository;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CategorySaveService {
    private final CategoryRepository repository;
    private final CategoryUserRepository categoryUserRepository;
    private final UserService userService;
    private final CategoryMapper mapper;

    public void save(CategoryFormDto categoryForm, String token) {
        User user = userService.findByToken(token);
        Optional<Category> categoryOptional = repository.findByNameCategory(categoryForm.getNameCategory());
        if (categoryOptional.isPresent()) {
            saveCategoryUser(categoryOptional.get(), user);
        } else {
            saveCategory(categoryForm, user);
        }
    }

    private void saveCategory(CategoryFormDto categoryForm, User user) {
        Category category = mapper.toEntity(categoryForm);
        category.setUsers(Collections.singleton(user));
        repository.save(category);
    }

    private void saveCategoryUser(Category category, User user) {
        if (!category.getUsers().contains(user)) {
            CategoryUser categoryUser = new CategoryUser();
            categoryUser.setFkCategory(category);
            categoryUser.setFkUser(user);
            categoryUserRepository.save(categoryUser);
        }
    }
}
