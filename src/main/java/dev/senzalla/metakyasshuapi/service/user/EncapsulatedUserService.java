package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import org.springframework.data.domain.Page;

import java.util.List;

abstract class EncapsulatedUserService implements InterfaceService<UserDto, UserFilter, UserForm, Void> {
    abstract public User findUser(UserFilter userFilter);

    abstract public void validateUser(String token);

    @Override
    public Page<Void> findAll(UserFilter userFilter) {
        return null;
    }

    @Override
    public List<Void> findAll() {
        return List.of();
    }

}
