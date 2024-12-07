package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.PasswordForm;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.email.EmailService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import dev.senzalla.metakyasshuapi.settings.exception.UserDisabledException;
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

    public void updateUser(UserForm userForm, String token) {
        try {
            User user = findService.findByToken(token);
            log.info("Updating user: {}", user.getEmailUser());
            user.setNameUser(userForm.getNameUser());
            user.setCpfUser(userForm.getCpfUser());
            checkMail(user, userForm);
            userRepository.save(user);
            log.info("User updated: {}", user.getEmailUser());
        } catch (DataIntegrityViolationException e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error update user: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        }
    }

    private void checkMail(User user, UserForm userForm) {
        if (!user.getEmailUser().equals(userForm.getEmailUser())) {
            user.setConfirmedUser(false);
            user.setEmailUser(userForm.getEmailUser());
            emailService.sendEmailCreateAccount(user);
        }
    }

    public void updatePassword(PasswordForm passwordForm, String token) {
        User user = findService.findByToken(token);
        if (passwordEncoder.matches(passwordForm.getOldPassword(), user.getPasswordUser())) {
            user.setPasswordUser(passwordEncoder.encode(passwordForm.getNewPassword()));
            userRepository.save(user);
            log.info("Password updated: {}", user.getEmailUser());
        } else {
            log.error("Error updating password: {}", user.getEmailUser());
            throw new UserDisabledException("error.user-password");
        }
    }
}
