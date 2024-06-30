package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.*;
import dev.senzalla.metakyasshuapi.service.InterfaceService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

abstract class EncapsulatedUserService implements InterfaceService<UserDto, UserFilter, UserForm, Void> {
    public abstract User findUser(UserFilter userFilter);

    public abstract UserDto findUserDto(UserFilter userFilter);

    public abstract void validateUser(String token);

    public abstract void update(UserForm userForm, String token);

    public abstract void delete(String token);

    public abstract void updatePassword(PasswordForm passwordForm, String token);

    public abstract void updatePhoto(String token, MultipartFile photo);

    public abstract void recoverPassword(RecoverAccess recoverAccess);

    public abstract void resetPassword(String token, Login login);

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public UserDto save(UserForm userForm, String token) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public UserDto update(Long pk, UserForm userForm) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public UserDto find(Long pk) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public void delete(Long pk) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Deprecated(since = "1.0", forRemoval = true)
    public Page<Void> findAllPage(UserFilter userFilter, String token, Pageable pageable) {
        return null;
    }
}
