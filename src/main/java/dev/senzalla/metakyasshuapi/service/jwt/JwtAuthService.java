package dev.senzalla.metakyasshuapi.service.jwt;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class JwtAuthService {
    private final JwtAuthExtractService extractService;
    private final JwtAuthCreateService createService;

    public String extractToken(HttpServletRequest request) {
        return extractService.extractToken(request);
    }

    public boolean validateToken(String token) {
        return extractService.validateToken(token);
    }

    public String getEmailUSer(String token) {
        return extractService.getEmailUSer(token);
    }

    public String createToken(User user) {
        return createService.createToken(user);
    }
}