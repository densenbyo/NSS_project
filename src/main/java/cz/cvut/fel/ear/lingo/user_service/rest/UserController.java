package cz.cvut.fel.ear.lingo.user_service.rest;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.model.util.Views;
import cz.cvut.fel.ear.lingo.services.interfaces.KafkaService;
import cz.cvut.fel.ear.lingo.user_service.adapter.UserAdapter;
import cz.cvut.fel.ear.lingo.user_service.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user_service.model.UserModel;
import cz.cvut.fel.ear.lingo.user_service.security.CurrentUser;
import cz.cvut.fel.ear.lingo.user_service.security.model.RegistrationRequest;
import cz.cvut.fel.ear.lingo.user_service.security.model.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/users")
@CrossOrigin(origins = "http://localhost:3000/")
@RequiredArgsConstructor
public class UserController {

    private final KafkaService kafkaService;
    private final UserAdapter userAdapter;

    // todo check it!!
    @GetMapping(value = "/current", produces = MediaType.APPLICATION_JSON_VALUE)
    @JsonView(Views.Public.class)
    public UserEntity getCurrent(@CurrentUser UserDetailsImpl userDetails) {
        return userDetails.getUser();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @JsonView(Views.Public.class)
    @GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<UserModel> getUsers() {
        return userAdapter.findAllUser();
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "find/findById/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel getUser(@Valid @PathVariable Long id) {
        return userAdapter.getUserById(id);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "find/findByUsername/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel findByUsername(@Valid @PathVariable String username) {
        return userAdapter.getUserByUsername(username);
    }

    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping(value = "find/findByMail/{mail}", produces = MediaType.APPLICATION_JSON_VALUE)
    public UserModel findByMail(@Valid @PathVariable String mail) {
        return userAdapter.getUserByMail(mail);
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/block")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> blockUser(@Valid @PathVariable Long id) {
        userAdapter.block(id);
        //        userService.sendMessageToKafka("Admin {\nBlocked user with id: " + id + "\n}");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/unblock")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> unblockUser(@Valid @PathVariable Long id) {
        userAdapter.unblock(id);
//        userService.sendMessageToKafka("Admin {\nUnblocked user: " + user.getId() + "\n}");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/role/{role}")
    public ResponseEntity<Void> updateRole(@Valid @PathVariable Long id, @Valid @PathVariable String role) {
        userAdapter.updateRole(id, role);
        log.info("The role of the user with id: {} has changed.", id);
//        userService.sendMessageToKafka("Admin {\nUser: " + user.getId() + "\n" + "Role: " + role + "\n}");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') || #id == authentication.principal.id")
    @PutMapping(value = "/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> updateUser(@Valid @PathVariable Long id, @RequestBody(required = false) UserModel user) {
        userAdapter.updateUser(id, user);
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN') || #id == authentication.principal.id")
    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Void> deleteUser(@Valid @PathVariable Long id) {
        userAdapter.deleteUser(id);
//        userService.sendMessageToKafka("Admin {\nDeleted user: " + user.getId() + "\n}");
        return ResponseEntity.noContent().build();
    }

    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    @PatchMapping(value = "/{id}/restore")
    public ResponseEntity<Void> restoreUser(@Valid @PathVariable Long id) {
        userAdapter.restoreUser(id);
        return ResponseEntity.noContent().build();
    }
}