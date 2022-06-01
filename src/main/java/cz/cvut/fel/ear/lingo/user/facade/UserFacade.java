package cz.cvut.fel.ear.lingo.user.facade;


import cz.cvut.fel.ear.lingo.user.use_case.*;
import cz.cvut.fel.ear.lingo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final GetUserByIdUseCase getUserByIdUseCase;
    private final GetUserByUsername getUserByUsername;
    private final GetUserByMailUseCase getUserByMailUseCase;
    private final GetAllUsersUseCase getAllUsersUseCase;
    private final BlockUserUseCase blockUserUseCase;
    private final UnblockUserUseCase unblockUserUseCase;
    private final UpdateRoleUseCase updateRoleUseCase;
    private final UpdateUserUseCase updateUserUseCase;
    private final DeleteUserUseCase deleteUserUseCase;
    private final RestoreUserUseCase restoreUserUseCase;

    public User findUserById(Long id) {
        return getUserByIdUseCase.execute(id);
    }

    public User findUserByUsername(String username) {
        return getUserByUsername.execute(username);
    }

    public User findUserByMail(String mail) {
        return getUserByMailUseCase.execute(mail);
    }

    public List<User> findAllUsers() {
        return getAllUsersUseCase.execute();
    }

    public void blockUser(Long id) {
        blockUserUseCase.execute(id);
    }

    public void unblockUser(Long id) {
        unblockUserUseCase.execute(id);
    }

    public void updateRole(Long id, String role) {
        updateRoleUseCase.execute(id, role);
    }

    public void updateUser(Long id, User user) {
        updateUserUseCase.execute(id, user);
    }

    public void deleteUser(Long id) {
        deleteUserUseCase.execute(id);
    }

    public void restoreUser(Long id) {
        restoreUserUseCase.execute(id);
    }
}
