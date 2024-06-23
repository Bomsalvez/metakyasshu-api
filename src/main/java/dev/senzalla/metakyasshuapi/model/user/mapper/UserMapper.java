package dev.senzalla.metakyasshuapi.model.user.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
import dev.senzalla.metakyasshuapi.model.user.module.UserSummarized;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
public class UserMapper implements InterfaceMapper<UserDto, User, UserForm, Void> {
    @Override
    public UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setPkUser(user.getPkUser());
        userDto.setNameUser(user.getNameUser());
        userDto.setEmailUser(user.getEmailUser());
        userDto.setDateCreateUser(user.getDateCreateUser());
        userDto.setImageUser(user.getImageUser());
        return userDto;
    }

    @Override
    public User toEntity(UserForm userForm) {
        User user = new User();
        user.setNameUser(userForm.getNameUser().replaceAll("\\s+", " "));
        user.setEmailUser(userForm.getEmailUser());
        user.setPasswordUser(userForm.getPasswordUser());
        user.setCpfUser(userForm.getCpfUser());
        return user;
    }

    @Deprecated(since = "1.0.0", forRemoval = true)
    @Override
    public Page<Void> toSummarized(Page<User> user) {
        throw new UnsupportedOperationException();
    }

    public UserSummarized toSummarized(User user) {
        UserSummarized userSummarized = new UserSummarized();
        userSummarized.setPkUser(user.getPkUser());
        userSummarized.setNameUser(user.getNameUser());
        userSummarized.setEmailUser(user.getEmailUser());
        return userSummarized;
    }
}
