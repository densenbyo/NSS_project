package cz.cvut.fel.ear.lingo.user.mapper;

import cz.cvut.fel.ear.lingo.repo.mapper.RepoEntityMapper;
import cz.cvut.fel.ear.lingo.statistics.mapper.StatisticEntityMapper;
import cz.cvut.fel.ear.lingo.user.domain.User;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
@RequiredArgsConstructor
public class UserEntityMapper {

    private final RepoEntityMapper repoEntityMapper;
    private final StatisticEntityMapper statisticEntityMapper;

    public User toUser(UserEntity userEntity){
        Objects.requireNonNull(userEntity);
        User user = new User();
        user.setId(userEntity.getId());
        user.setRole(userEntity.getRole().toString());
        user.setIsActive(userEntity.getIsActive());
        user.setIsRemoved(userEntity.getIsRemoved());
        user.setMail(userEntity.getMail());
        user.setUsername(userEntity.getUsername());
        user.setPassword(userEntity.getPassword());
        user.setRepo(userEntity.getRepo() == null ? null : repoEntityMapper.toRepo(userEntity.getRepo()));
        user.setStatistic(userEntity.getStatistic() == null ? null :statisticEntityMapper.toStatistic(userEntity.getStatistic()));
        return user;
    }

    public List<User> toUserList(List<UserEntity> userEntities) {
        List<User> users = new ArrayList<>(userEntities.size());
        for (UserEntity entity: userEntities) {
            users.add(toUser(entity));
        }
        return users;
    }
}
