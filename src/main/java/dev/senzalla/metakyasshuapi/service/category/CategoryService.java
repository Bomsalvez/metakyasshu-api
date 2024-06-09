package dev.senzalla.metakyasshuapi.service.category;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
    private final CategorySaveService saveService;

    public void save(CategoryFormDto categoryForm, String token) {
        saveService.save(categoryForm, token);
    }
}
