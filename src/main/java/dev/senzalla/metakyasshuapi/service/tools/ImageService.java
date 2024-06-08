package dev.senzalla.metakyasshuapi.service.tools;

import dev.senzalla.metakyasshuapi.settings.exception.ImageException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@AllArgsConstructor
public class ImageService {
    public void checkImage(MultipartFile photo) {
        String contentType = photo.getContentType();
        assert contentType != null;
        if (!contentType.startsWith("image/jpeg") && !contentType.startsWith("image/png")) {
            throw new ImageException("error.avatar-format");
        }
    }
}
