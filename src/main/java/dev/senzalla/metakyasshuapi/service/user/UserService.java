package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.*;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends EncapsulatedUserService implements UserDetailsService {
    private final UserUpdateService updateService;
    private final UserCreateService createService;
    private final UserDeleteService deleteService;
    private final UserPhotoService photoService;
    private final UserSaveService saveService;
    private final UserFindService findService;

    public void save(UserForm userForm) {
        createService.saveUser(userForm);
    }

    @Override
    public UserDto findUserDto(UserFilter userFilter) {
        return findService.findUserDto(userFilter);
    }

    @Override
    public User findUser(UserFilter userFilter) {
        return findService.findUser(userFilter);
    }

    @Override
    public void validateUser(String token) {
        saveService.validateUser(token);
    }

    @Override
    public void update(UserForm userForm, String token) {
        updateService.updateUser(userForm, token);
    }

    @Override
    public void delete(String token) {
        deleteService.deleteUser(token);
    }

    @Override
    public void updatePassword(PasswordForm passwordForm, String token) {
        updateService.updatePassword(passwordForm, token);
    }

    @Override
    public void updatePhoto(String token, MultipartFile photo) {
        photoService.updatePhoto(token, photo);
    }

    @Override
    public void recoverPassword(RecoverAccess recoverAccess) {
        saveService.recoverPassword(recoverAccess);
    }

    @Override
    public void resetPassword(String token, Login login) {
        saveService.resetPassword(token, login);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(createUserFilter(username));
    }

    private UserFilter createUserFilter(String username) {
        return UserFilter.builder().cpfUser(username).build();
    }

    public User findByToken(String token) {
        return findService.findByToken(token);
    }
}
