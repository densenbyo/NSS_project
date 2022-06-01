package cz.cvut.fel.ear.lingo.user.service;

import cz.cvut.fel.ear.lingo.user.security.DefaultAuthenticationProvider;
import cz.cvut.fel.ear.lingo.user.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final DefaultAuthenticationProvider provider;
    private final JwtTokenProvider tokenProvider;

    @Transactional(readOnly = true)
    public String login(String username, String password) {
        Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
        Authentication authentication = provider.authenticate(auth);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return tokenProvider.generateToken(authentication);
    }
}