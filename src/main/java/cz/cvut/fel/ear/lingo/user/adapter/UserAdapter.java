package cz.cvut.fel.ear.lingo.user.adapter;

import cz.cvut.fel.ear.lingo.user.domain.User;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user.facade.UserFacade;
import cz.cvut.fel.ear.lingo.user.mapper.User2UserModelMapper;
import cz.cvut.fel.ear.lingo.user.mapper.UserEntityMapper;
import cz.cvut.fel.ear.lingo.user.mapper.UserModel2UserMapper;
import cz.cvut.fel.ear.lingo.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class UserAdapter {

    private final UserFacade userFacade;
    private final User2UserModelMapper user2UserModelMapper;
    private final UserModel2UserMapper userModel2UserMapper;
    private final UserEntityMapper userEntityMapper;

    public UserModel getCurrentUser(UserEntity userEntity) {
        User user = userEntityMapper.toUser(userEntity);
        return user2UserModelMapper.toUserResponse(user);
    }

    public UserModel getUserById(Long id) {
        var user = userFacade.findUserById(id);
        return user2UserModelMapper.toUserResponse(user);
    }

    public UserModel getUserByUsername(String username) {
        var user = userFacade.findUserByUsername(username);
        return user2UserModelMapper.toUserResponse(user);
    }

    public UserModel getUserByMail(String mail) {
        var user = userFacade.findUserByMail(mail);
        return user2UserModelMapper.toUserResponse(user);
    }

    public List<UserModel> findAllUser() {
        var users = userFacade.findAllUsers();
        return user2UserModelMapper.toUserList(users);
    }

    public void block(Long id) {
        userFacade.blockUser(id);
    }

    public void unblock(Long id) {
        userFacade.unblockUser(id);
    }

    public void updateRole(Long id, String role) {
        userFacade.updateRole(id, role);
    }

    public void updateUser(Long id, UserModel userModel) {
        User user = userModel2UserMapper.toUser(userModel);
        userFacade.updateUser(id, user);
    }

    public void deleteUser(Long id) {
        userFacade.deleteUser(id);
    }

    public void restoreUser(Long id) {
        userFacade.restoreUser(id);
    }
}
