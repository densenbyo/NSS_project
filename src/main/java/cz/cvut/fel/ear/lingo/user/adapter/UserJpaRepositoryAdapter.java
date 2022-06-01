package cz.cvut.fel.ear.lingo.user.adapter;


import cz.cvut.fel.ear.lingo.repo.mapper.Repo2RepoEntityMapper;
import cz.cvut.fel.ear.lingo.statistics.mapper.Statistic2StatisticEntityMapper;
import cz.cvut.fel.ear.lingo.user.domain.User;
import cz.cvut.fel.ear.lingo.user.entity.enumeration.UserRole;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import cz.cvut.fel.ear.lingo.user.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserJpaRepositoryAdapter implements UserRepositoryAdapter{

    private final UserEntityRepository repository;
    private final UserEntityMapper mapper;
    private final Statistic2StatisticEntityMapper statistic2StatisticEntityMapper;
    private final Repo2RepoEntityMapper repo2RepoEntityMapper;

    @Override
    public User findById(Long id) {
        var userEntity = repository.findUserEntityById(id);
        return userEntity.map(mapper::toUser).orElse(null);
    }

    @Override
    public User findByUsername(String username) {
        var userEntity = repository.findUserEntityByUsernameAndIsRemovedIsFalse(username);
        return userEntity.map(mapper::toUser).orElse(null);
    }

    @Override
    public User findByMail(String mail) {
        var userEntity = repository.findUserEntityByMailAndIsRemovedIsFalse(mail);
        return userEntity.map(mapper::toUser).orElse(null);
    }

    @Override
    public List<User> getListOfUser() {
        var userEntities = repository.findUserEntitiesByIsRemovedIsFalse();
        return mapper.toUserList(userEntities);
    }

    @Override
    public void blockUserById(Long id) {
        var userEntityOptional = repository.findByIdAndIsRemovedIsFalse(id);
        if (userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            userEntity.setIsActive(false);
            repository.save(userEntity);
        }
    }

    @Override
    public void unblockUserById(Long id) {
        var userEntityOptional = repository.findByIdAndIsRemovedIsFalse(id);
        if (userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            userEntity.setIsActive(true);
            repository.save(userEntity);
        }
    }

    @Override
    public void updateRole(Long id, String role) {
        var userEntityOptional = repository.findByIdAndIsRemovedIsFalse(id);
        if (userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            userEntity.setRole(UserRole.valueOf(role));
            repository.save(userEntity);
        }
    }

    @Override
    public void updateUser(Long id, User user) {
        var userEntityOptional = repository.findByIdAndIsRemovedIsFalse(id);
        if (userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            var repoEntity = repo2RepoEntityMapper.toRepoEntity(user.getRepo(), userEntityOptional.get());
            var statisticEntity = statistic2StatisticEntityMapper.toStatisticEntity(user.getStatistic());
            userEntity.setRole(UserRole.valueOf(user.getRole()));
            userEntity.setIsActive(user.getIsActive());
            userEntity.setIsRemoved(user.getIsRemoved());
            userEntity.setMail(user.getMail());
            userEntity.setPassword(user.getPassword());
            userEntity.setUsername(user.getUsername());
            userEntity.setRepo(repoEntity);
            userEntity.setStatistic(statisticEntity);
            repository.save(userEntity);
        }
    }

    @Override
    public void deleteUser(Long id) {
        var userEntityOptional = repository.findByIdAndIsRemovedIsFalse(id);
        if (userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            userEntity.setIsRemoved(true);
            repository.save(userEntity);
        }
    }

    @Override
    public void restoreUser(Long id) {
        var userEntityOptional = repository.findById(id);
        if (userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            userEntity.setIsRemoved(false);
            repository.save(userEntity);
        }
    }
}
