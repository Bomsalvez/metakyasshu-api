package dev.senzalla.metakyasshuapi.model.category.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
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
        category.setNameCategory(categoryFormDto.getNameCategory());
        category.setTypeCategory(categoryFormDto.getTypeCategory());
        return category;
    }

    @Override
    @Deprecated
    public Void toSummarized(Category category) {
        return null;
    }

    public List<CategoryFormDto> toCategoryFormDto(List<Category> categories) {
        return categories.stream().map(this::toDto).toList();
    }
}