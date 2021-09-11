package com.app.renteva.shared.token;

import com.app.renteva.user.User;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

@Component
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@RequiredArgsConstructor
public class TokenFilter extends OncePerRequestFilter {

    private static final String AUTH_HEADER = "Authorization";
    JwtService jwtService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        tokenString(request.getHeader(AUTH_HEADER))
                .ifPresent(
                        (String token) -> jwtService
                                .getUser(token)
                                .ifPresent(
                                        (User user) -> {
                                            UserDetails ud =
                                                    org.springframework.security.core.userdetails.User.withUsername(
                                                            user.getEmail())
                                                            .password(user.getPassword())
                                                            .authorities(Collections.emptyList())
                                                            .accountExpired(false)
                                                            .accountLocked(false)
                                                            .credentialsExpired(false)
                                                            .disabled(false)
                                                            .build();

                                            UsernamePasswordAuthenticationToken authenticationToken =
                                                    new UsernamePasswordAuthenticationToken(ud, token, ud.getAuthorities());

                                            authenticationToken.setDetails(
                                                    new WebAuthenticationDetailsSource().buildDetails(request));

                                            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                                        }));

        filterChain.doFilter(request, response);
    }

    private Optional<String> tokenString(String header) {
        if (header == null) {
            return Optional.empty();
        } else {
            final var split = header.split(" ");
            if (split.length < 2) {
                return Optional.empty();
            } else {
                return Optional.ofNullable(split[1]);
            }
        }
    }
}
