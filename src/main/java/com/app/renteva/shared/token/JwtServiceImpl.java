package com.app.renteva.shared.token;

import com.app.renteva.user.User;
import com.app.renteva.user.UserRepository;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
class JwtServiceImpl implements JwtService {

    String secret;

    int sessionTime;

    UserRepository userRepository;

    @Autowired
    public JwtServiceImpl(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.sessionTime}") int sessionTime,
            UserRepository userRepository) {
        this.secret = secret;
        this.sessionTime = sessionTime;
        this.userRepository = userRepository;
    }

    @Override
    public String generateToken(User user) {
        return Jwts.builder()
                .setSubject(Long.toString(user.getId()))
                .setExpiration(new Date(System.currentTimeMillis() + sessionTime * 1000L))
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    @Override
    public Optional<User> getUser(String token) {
        try {
            final var subject = Jwts.parser()
                    .setSigningKey(secret)
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();

            final var userId = Long.parseLong(subject);
            return userRepository.findById(userId);
        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
