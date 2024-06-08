package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.jwt.JwtAuthService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserFindService {
    private final ToolsUserService toolService;
    private final UserRepository repository;
    private final JwtAuthService jwtService;
    private final UserMapper mapper;

    public User findUser(UserFilter userFilter) {
        if (userFilter.getToken() != null) {
            String emailUser = jwtService.getEmailUSer(userFilter.getToken());
            userFilter.setEmailUser(emailUser);
        }
        Optional<User> user = repository.findUserByCpfUserOrEmailUserAndConfirmedUser(userFilter.getCpfUser(), userFilter.getEmailUser(), true);
        toolService.checkUserExists(user);
        return user.get();
    }

    public UserDto findUserDto(UserFilter userFilter) {
        String emailUser = jwtService.getEmailUSer(userFilter.getToken());
        userFilter.setEmailUser(emailUser);
        Optional<User> user = repository.findUserByCpfUserOrEmailUserAndConfirmedUser(userFilter.getCpfUser(), userFilter.getEmailUser(), true);
        toolService.checkUserExists(user);
        return mapper.toDto(user.get());
    }
}
