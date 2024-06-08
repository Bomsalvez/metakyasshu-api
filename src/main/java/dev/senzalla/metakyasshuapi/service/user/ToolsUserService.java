package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.tools.MessageDecode;
import dev.senzalla.metakyasshuapi.settings.exception.DuplicateException;
import dev.senzalla.metakyasshuapi.settings.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Optional;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ToolsUserService {
    private final MessageDecode messageDecode;
    private final UserRepository repository;

    public String createCode() {
        String sb = createHashCode();
        return checkCode(sb);
    }

    private String checkCode(String code) {
        Optional<User> validateAccount = repository.findByKeyUser(code);
        if (validateAccount.isPresent()) {
            createCode();
        }
        return code;
    }

    public String createHashCode() {
        SecureRandom random = new SecureRandom();
        byte[] bytes = new byte[6];
        random.nextBytes(bytes);
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public void checkUserExists(Optional<User> user) {
        if (user.isEmpty()) {
            String message = messageDecode.info("entity.user");
            throw new NotFoundException("error.not-found", message);
        }
    }

    public void checkUserConfirmed(Optional<User> user) {
        if (user.get().isConfirmedUser()) {
            throw new DuplicateException("error.user-validate");
        }
    }
}
