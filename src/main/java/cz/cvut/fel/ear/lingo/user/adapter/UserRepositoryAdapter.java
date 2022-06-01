package cz.cvut.fel.ear.lingo.user.adapter;

import cz.cvut.fel.ear.lingo.user.domain.User;

import java.util.List;

public interface UserRepositoryAdapter {

    User findById(Long id);

    User findByUsername(String username);

    User findByMail(String mail);

    List<User> getListOfUser();

    void blockUserById(Long id);

    void unblockUserById(Long id);

    void updateRole(Long id, String role);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void restoreUser(Long id);
}
