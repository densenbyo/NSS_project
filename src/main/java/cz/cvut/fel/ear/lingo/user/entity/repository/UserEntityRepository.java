package cz.cvut.fel.ear.lingo.user.entity.repository;

import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    
    Optional<UserEntity> findUserEntityById(Long id);

    Optional<UserEntity> findUserEntityByMailAndIsRemovedIsFalse(String email);

    Optional<UserEntity> findUserEntityByUsernameAndIsRemovedIsFalse(String username);

    Optional<UserEntity> findByIdAndIsRemovedIsFalse(Long id);

    List<UserEntity> findUserEntitiesByIsRemovedIsFalse();

    Optional<UserEntity> findUserEntityByRepoId(Long id);

}
