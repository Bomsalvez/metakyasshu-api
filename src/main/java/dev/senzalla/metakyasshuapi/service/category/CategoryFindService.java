package dev.senzalla.metakyasshuapi.service.category;

import dev.senzalla.metakyasshuapi.model.category.entity.Category;
import dev.senzalla.metakyasshuapi.model.category.entity.CategoryRepository;
import dev.senzalla.metakyasshuapi.model.category.mapper.CategoryMapper;
import dev.senzalla.metakyasshuapi.model.category.module.CategoryFormDto;
import dev.senzalla.metakyasshuapi.model.types.TypeCategory;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class CategoryFindService {
    private final CategoryRepository repository;
    private final UserService userService;
    private final CategoryMapper mapper;


    public List<CategoryFormDto> findAll(String token, TypeCategory type) {
        UserFilter userFilter = UserFilter.builder().token(token).build();
        User user = userService.findUser(userFilter);
        List<Category> categories = repository.findAllAndUser(user, type);
        return mapper.toCategoryFormDto(categories);
    }
}
