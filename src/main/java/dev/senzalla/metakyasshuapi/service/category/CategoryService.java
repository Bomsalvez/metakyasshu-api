package dev.senzalla.metakyasshuapi.service.category;

import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.TypeCategory;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CategoryService {
    private final CategorySaveService saveService;
    private final CategoryFindService findService;

    public void save(CategoryFormDto categoryForm, String token) {
        saveService.save(categoryForm, token);
    }

    public List<CategoryFormDto> findAll(String token, TypeCategory type) {
        return findService.findAll(token, type);
    }
}
