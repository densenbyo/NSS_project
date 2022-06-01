package cz.cvut.fel.ear.lingo.user.mapper;

import cz.cvut.fel.ear.lingo.repo.mapper.Repo2RepoModelMapper;
import cz.cvut.fel.ear.lingo.statistics.mapper.Statistic2StatisticModelMapper;
import cz.cvut.fel.ear.lingo.user.domain.User;
import cz.cvut.fel.ear.lingo.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class User2UserModelMapper {

    private final Repo2RepoModelMapper repo2RepoModelMapper;
    private final Statistic2StatisticModelMapper statistic2StatisticModelMapper;

    public UserModel toUserResponse(User user){
        if (user == null) return null;
        UserModel userModel = new UserModel();
        userModel.setId(user.getId());
        userModel.setIsActive(user.getIsActive());
        userModel.setIsRemoved(user.getIsRemoved());
        userModel.setRole(user.getRole());
        userModel.setMail(user.getMail());
        userModel.setPassword(user.getPassword());
        userModel.setUsername(user.getUsername());
        userModel.setStatisticModel(user.getStatistic() == null ? null : statistic2StatisticModelMapper.toStatisticModel(user.getStatistic()));
        userModel.setRepoModel(user.getRepo() == null ? null :repo2RepoModelMapper.toRepoModel(user.getRepo()));
        return userModel;
    }

    public List<UserModel> toUserList(List<User> users) {
        List<UserModel> usersResponse = new ArrayList<>(users.size());
        for (User user: users) {
            usersResponse.add(toUserResponse(user));
        }
        return usersResponse;
    }
}