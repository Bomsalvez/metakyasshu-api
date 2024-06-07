package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserFindService {
    private final UserRepository repository;
    private final ToolsUserService toolService;

    public User findUser(UserFilter userFilter) {
        Optional<User> user = repository.findUserByCpfUserOrEmailUserAndConfirmedUser(userFilter.getCpfUser(), userFilter.getEmailUser(), true);
        toolService.checkUserExists(user);
        return user.get();
    }
}
