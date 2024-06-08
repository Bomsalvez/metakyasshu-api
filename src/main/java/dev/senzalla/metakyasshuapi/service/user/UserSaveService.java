package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserSaveService {
    private final ToolsUserService toolService;
    private final UserRepository repository;

    public void validateUser(String token) {
        Optional<User> user = repository.findByKeyUser(token);
        toolService.checkUserExists(user);
        toolService.checkUserConfirmed(user);
        User userValidate = user.get();
        userValidate.setConfirmedUser(true);
        repository.save(userValidate);
    }
}
