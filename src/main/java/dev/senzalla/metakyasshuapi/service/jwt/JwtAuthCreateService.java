package dev.senzalla.metakyasshuapi.service.jwt;

import dev.senzalla.metakyasshuapi.model.user.entity.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
class JwtAuthCreateService {

    @Value("${server.servlet.application-display-name}")
    private String applicationName;

    @Value("${jwt.api.secret}")
    private String jwtKey;

    @Value("${jwt.api.expiration}")
    private Long timeToExpiry;

    public String createToken(User user) {
        final Date today = new Date();
        final Date timeExpiry = new Date(today.getTime() + timeToExpiry);

        return Jwts.builder().setIssuer(applicationName)
                .setSubject(user.getEmailUser())
                .claim("pkUser", user.getPkUser().toString())
                .setIssuedAt(today)
                .setExpiration(timeExpiry)
                .signWith(createKey())
                .compact();
    }

    private Key createKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(jwtKey));
    }
}
