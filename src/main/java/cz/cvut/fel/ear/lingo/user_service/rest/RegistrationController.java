package cz.cvut.fel.ear.lingo.user_service.rest;

import cz.cvut.fel.ear.lingo.statistic_service.entity.StatisticEntity;
import cz.cvut.fel.ear.lingo.repo_service.entity.RepoEntity;
import cz.cvut.fel.ear.lingo.rest.RestUtils;
import cz.cvut.fel.ear.lingo.user_service.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user_service.entity.enumeration.UserRole;
import cz.cvut.fel.ear.lingo.user_service.entity.repository.UserEntityRepository;
import cz.cvut.fel.ear.lingo.user_service.security.model.RegistrationRequest;
import cz.cvut.fel.ear.lingo.user_service.security.model.Response;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class RegistrationController {

    private final UserEntityRepository repository;

    @PostMapping(value = "/registration", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> register(@RequestBody RegistrationRequest request) {
        if ((repository.findUserEntityByUsernameAndIsRemovedIsFalse(request.getUsername()).isPresent()) ||
                (repository.findUserEntityByMailAndIsRemovedIsFalse(request.getMail()).isPresent()))
            return new ResponseEntity<>(new Response(false, "Bad Credentials!"), HttpStatus.BAD_REQUEST);
        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .mail(request.getMail())
                .password(request.getPassword())
                .repo(new RepoEntity())
                .statisticEntity(new StatisticEntity())
                .isActive(true)
                .isRemoved(false)
                .role(UserRole.USER)
                .build();
        repository.save(user);
        log.debug("User {} successfully registered.", user);
        final HttpHeaders headers = RestUtils.createLocationHeaderFromCurrentUri("/current");
        return new ResponseEntity<>(headers, HttpStatus.CREATED);
    }
}