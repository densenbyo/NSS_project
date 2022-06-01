package cz.cvut.fel.ear.lingo.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user.entity.enumeration.UserRole;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import cz.cvut.fel.ear.lingo.user.security.jwt.JwtAuthenticationResponse;
import cz.cvut.fel.ear.lingo.user.security.model.LoginStatus;
import cz.cvut.fel.ear.lingo.user.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Service;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Service("oauth2authSuccessHandler")
@RequiredArgsConstructor
public class Oauth2AuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    private final LoginService loginService;
    private final ObjectMapper mapper;
    private final UserEntityRepository repository;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException {
        DefaultOidcUser oauthUser = (DefaultOidcUser) authentication.getPrincipal();
        String email = oauthUser.getAttribute("email");
        String username = oauthUser.getAttribute("name");
        UserEntity user = UserEntity.builder()
                .username(username)
                .mail(email)
                .password("")
                .isActive(true)
                .role(UserRole.USER)
                .build();
        repository.save(user);
        final LoginStatus loginStatus = new LoginStatus(true, username, null, authentication.isAuthenticated());
        response.setStatus(HttpStatus.OK.value());
        ResponseEntity<?> entity = ResponseEntity.ok(new JwtAuthenticationResponse(loginService.login(username, "")));
        String json = new ObjectMapper().writeValueAsString(entity.getBody());
        ServletOutputStream out = response.getOutputStream();
        out.print(json);
        mapper.writeValue(out, loginStatus);
    }
}
