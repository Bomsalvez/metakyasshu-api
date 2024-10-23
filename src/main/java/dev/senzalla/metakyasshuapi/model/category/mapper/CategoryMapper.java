package dev.senzalla.metakyasshuapi.model.category.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryMapper implements InterfaceMapper<CategoryFormDto, Category, CategoryFormDto, Void> {
    @Override
    public CategoryFormDto toDto(Category category) {
        CategoryFormDto categoryFormDto = new CategoryFormDto();
        categoryFormDto.setPkCategory(category.getPkCategory());
        categoryFormDto.setNameCategory(category.getNameCategory());
        categoryFormDto.setTypeCategory(category.getTypeCategory());
        return categoryFormDto;
    }

    @Override
    public Category toEntity(CategoryFormDto categoryFormDto) {
        Category category = new Category();
        category.setNameCategory(categoryFormDto.getNameCategory().replaceAll("\\s+", " "));
        category.setTypeCategory(categoryFormDto.getTypeCategory());
        return category;
    }

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public Page<Void> toSummarized(Page<Category> category) {
        return null;
    }

    public List<CategoryFormDto> toCategoryFormDto(List<Category> categories) {
        return categories.stream().map(this::toDto).toList();
    }

    public Category toEntityWithPk(CategoryFormDto categoryFormDto) {
        Category category = toEntity(categoryFormDto);
        category.setPkCategory(categoryFormDto.getPkCategory());
        return category;
    }
}