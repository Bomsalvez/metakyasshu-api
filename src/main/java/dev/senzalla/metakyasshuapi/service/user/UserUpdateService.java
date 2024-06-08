package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.email.EmailService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserUpdateService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final UserFindService findService;
    private final MessageDecode messageDecode;
    private final EmailService emailService;
    private final UserMapper mapper;

    public void updateUser(UserForm userForm, String token) {
        try {
            UserFilter userFilter = UserFilter.builder().token(token).build();
            User user = findService.findUser(userFilter);
            log.info("Updating user: {}", user.getEmailUser());
            User updatedUser = mapper.toEntity(userForm);
            updatedUser.setPkUser(user.getPkUser());
            updatedUser.setPasswordUser(passwordEncoder.encode(updatedUser.getPasswordUser()));
            updatedUser.setKeyUser(user.getKeyUser());
            updatedUser.setDateCreateUser(user.getDateCreateUser());
            checkMail(user, updatedUser);
            userRepository.save(updatedUser);
            log.info("User updated: {}", updatedUser.getEmailUser());
        } catch (DataIntegrityViolationException e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error update user: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        }
    }

    private void checkMail(User user, User updatedUser) {
        if (!user.getEmailUser().equals(updatedUser.getEmailUser())) {
            updatedUser.setConfirmedUser(false);
            emailService.sendEmailCreateAccount(updatedUser);
        }
    }
}
