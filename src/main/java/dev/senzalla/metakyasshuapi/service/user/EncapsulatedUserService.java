package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.*;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import org.springframework.data.domain.Page;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

abstract class EncapsulatedUserService implements InterfaceService<UserDto, UserFilter, UserForm, Void> {
    abstract public User findUser(UserFilter userFilter);

    abstract public UserDto findUserDto(UserFilter userFilter);

    abstract public void validateUser(String token);

    abstract public void update(UserForm userForm, String token);

    abstract public void delete(String token);

    abstract public void updatePassword(PasswordForm passwordForm, String token);

    abstract public void updatePhoto(String token, MultipartFile photo);

    abstract public  void recoverPassword(RecoverAccess recoverAccess);

    @Override
    public Page<Void> findAll(UserFilter userFilter) {
        return null;
    }

    @Override
    public List<Void> findAll() {
        return List.of();
    }

    @Override
    public void update(Long pk, UserForm userForm) {
    }

    @Override
    public UserDto find(Long pk) {
        return null;
    }

    @Override
    public void delete(Long pk) {
    }
}
