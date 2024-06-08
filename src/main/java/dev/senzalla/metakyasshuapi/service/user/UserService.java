package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserService extends EncapsulatedUserService implements UserDetailsService {
    private final UserCreateService createService;
    private final UserSaveService saveService;
    private final UserFindService findService;

    @Override
    public void save(UserForm userForm) {
        createService.saveUser(userForm);

    }

    @Override
    public void update(Long pk, UserForm userForm) {

    }

    @Override
    public void delete(Long pk) {

    }

    @Override
    public UserDto find(UserFilter userFilter) {
        return null;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return findUser(createUserFilter(username));
    }

    private UserFilter createUserFilter(String username) {
        return UserFilter.builder().cpfUser(username).build();
    }
}
