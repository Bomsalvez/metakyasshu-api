package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.email.EmailService;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.annotation.Transient;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserCreateService {
    public final PasswordEncoder passwordEncoder;
    private final ToolsUserService toolService;
    private final MessageDecode messageDecode;
    private final UserRepository repository;
    private final EmailService emailService;
    private final UserMapper mapper;


    @Transient
    public void saveUser(UserForm userForm) {
        try {
            User user = mapper.toEntity(userForm);
            user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
            user.setKeyUser(toolService.createCode());
            repository.save(user);
            emailService.sendEmailCreateAccount(user);
        } catch (DataIntegrityViolationException e) {
            String error = messageDecode.extractMessage(e.getMessage());
            log.error("Error creating user: {}", error);
            String message = MessageDecode.decodeUnique(e.getMessage());
            throw new DuplicateException("error.duplicate", message);
        }

    }
}
