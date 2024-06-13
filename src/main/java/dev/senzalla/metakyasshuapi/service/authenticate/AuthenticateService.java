package dev.senzalla.metakyasshuapi.service.authenticate;

import dev.senzalla.metakyasshuapi.model.authenticate.Login;
import dev.senzalla.metakyasshuapi.model.authenticate.Token;
import dev.senzalla.metakyasshuapi.model.user.entity.Permission;
import dev.senzalla.metakyasshuapi.model.user.entity.User;
import dev.senzalla.metakyasshuapi.model.user.module.UserFilter;
import dev.senzalla.metakyasshuapi.service.jwt.JwtAuthService;
import dev.senzalla.metakyasshuapi.service.user.UserService;
import dev.senzalla.metakyasshuapi.settings.exception.UserDisabledException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final JwtAuthService jwtService;
    private final UserService userService;

    public Token startSection(Login login) {
        try {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(login.getCpf(), login.getPassword(), List.of(new Permission()));
            authenticationManager.authenticate(authenticationToken);
            User user = userService.findUser(createFilter(authenticationToken.getPrincipal().toString()));
            String token = jwtService.createToken(user);
            return new Token(token);
        } catch (DisabledException e) {
            throw new UserDisabledException("error.user-not-validate");
        } catch (BadCredentialsException | InternalAuthenticationServiceException e) {
            throw new UserDisabledException("error.user-login");
        }
    }

    private UserFilter createFilter(String string) {
        return UserFilter.builder().cpfUser(string).build();
    }
}
