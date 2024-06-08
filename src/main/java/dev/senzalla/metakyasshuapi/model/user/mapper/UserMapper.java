package dev.senzalla.metakyasshuapi.model.user.mapper;

import dev.senzalla.metakyasshuapi.model.InterfaceMapper;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserDto;
import dev.senzalla.metakyasshuapi.model.user.module.UserForm;
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

    @Override
    public Void toSummarized(User user) {
        return null;
    }
}
