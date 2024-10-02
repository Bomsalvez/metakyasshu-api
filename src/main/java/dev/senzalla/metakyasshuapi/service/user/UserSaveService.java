package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.RecoverAccess;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.email.EmailService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserSaveService {
    private final ToolsUserService toolService;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    private final EmailService emailService;

    @Transient
    public void validateUser(String token) {
        Optional<User> user = repository.findByKeyUser(token);
        toolService.checkUserExists(user);
        toolService.checkUserConfirmed(user);
        User userValidate = user.get();
        userValidate.setConfirmedUser(true);
        repository.save(userValidate);
    }

    @Transient
    public void recoverPassword(RecoverAccess recoverAccess) {
        Optional<User> user = repository.findUserByCpfUserOrEmailUserAndConfirmedUser(recoverAccess.getCpf(), recoverAccess.getEmail(), true);
        toolService.checkUserExists(user);
        User userRecover = user.get();
        userRecover.setKeyUser(toolService.createCode());
        userRecover.setConfirmedUser(false);
        repository.save(userRecover);
        emailService.sendEmailRecoverPassword(userRecover);
    }

    @Transient
    public void resetPassword(String token, Login login) {
        Optional<User> user = repository.findUserByCpfUserAndKeyUser(login.getCpf(), token);
        toolService.checkUserExists(user);
        toolService.checkUserConfirmed(user);
        User userValidate = user.get();
        userValidate.setPasswordUser(passwordEncoder.encode(login.getPassword()));
        userValidate.setConfirmedUser(true);
        repository.save(userValidate);
    }
}
