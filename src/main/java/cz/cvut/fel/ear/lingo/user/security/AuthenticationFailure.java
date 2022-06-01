package cz.cvut.fel.ear.lingo.user.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import cz.cvut.fel.ear.lingo.user.security.model.LoginStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Returns info about authentication failure.
 */
@Slf4j
@Service
public class AuthenticationFailure implements AuthenticationFailureHandler {

    private final ObjectMapper mapper;

    @Autowired
    public AuthenticationFailure(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
                                        AuthenticationException e) throws IOException {
        log.debug("Login failed for user {}.", httpServletRequest.getParameter(SecurityConstants.USERNAME_PARAM));
        final LoginStatus status = new LoginStatus(false, null, e.getMessage(), false);
        mapper.writeValue(httpServletResponse.getOutputStream(), status);
    }
}
