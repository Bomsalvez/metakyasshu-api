package dev.senzalla.metakyasshuapi.service.user;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.repository.UserRepository;
import dev.senzalla.metakyasshuapi.service.tools.ImageService;
import dev.senzalla.metakyasshuapi.settings.exception.ImageException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
class UserPhotoService {
    private final UserFindService findService;
    private final UserRepository repository;
    private final ImageService imageService;

    public void updatePhoto(String token, MultipartFile photo) {
        try {
            imageService.checkImage(photo);
            User user = findService.findByToken(token);
            user.setImageUser(photo.getBytes());
            repository.save(user);
        } catch (IOException e) {
            throw new ImageException("error.avatar", e.getMessage());
        }
    }
}
