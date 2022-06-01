package cz.cvut.fel.ear.lingo.user.mapper;

import cz.cvut.fel.ear.lingo.repo.mapper.RepoModel2RepoMapper;
import cz.cvut.fel.ear.lingo.statistics.mapper.StatisticModel2StatisticMapper;
import cz.cvut.fel.ear.lingo.user.domain.User;
import cz.cvut.fel.ear.lingo.user.model.UserModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserModel2UserMapper {

    private final RepoModel2RepoMapper repoModel2RepoMapper;
    private final StatisticModel2StatisticMapper statisticModel2StatisticMapper;

    public User toUser(UserModel userModel) {
        if (userModel == null) return null;
        User user = new User();
        user.setId(userModel.getId());
        user.setUsername(userModel.getUsername());
        user.setIsActive(userModel.getIsActive());
        user.setIsRemoved(userModel.getIsRemoved());
        user.setRole(userModel.getRole());
        user.setMail(userModel.getMail());
        user.setPassword(userModel.getPassword());
        user.setRepo(repoModel2RepoMapper.toRepo(userModel.getRepoModel()));
        user.setStatistic(statisticModel2StatisticMapper.toStatistic(userModel.getStatisticModel()));
        return user;
    }
}
