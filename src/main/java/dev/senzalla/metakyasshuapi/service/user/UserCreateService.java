package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.mapper.UserMapper;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserCreateService {
    public final PasswordEncoder passwordEncoder;
    private final ToolsUserService toolService;
    private final UserRepository repository;
    private final UserMapper mapper;


    public void saveUser(UserForm userForm) {
        try {
            User user = mapper.toEntity(userForm);
            user.setPasswordUser(passwordEncoder.encode(user.getPasswordUser()));
            user.setKeyUser(toolService.createCode());
            repository.save(user);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao salvar usuário");
        }

    }
}
