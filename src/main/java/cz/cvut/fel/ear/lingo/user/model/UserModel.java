package cz.cvut.fel.ear.lingo.user.model;

import cz.cvut.fel.ear.lingo.repo.model.RepoModel;
import cz.cvut.fel.ear.lingo.statistics.model.StatisticModel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class UserModel {

    private Long id;
    private String username;
    private String password;
    private String mail;
    private Boolean isActive;
    private Boolean isRemoved;
    private String role;
    private RepoModel repoModel;
    private StatisticModel statisticModel;

}
