package cz.cvut.fel.ear.lingo.user.domain;

import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Setter
@Getter
public class User {

    private Long id;
    private String username;
    private String password;
    private String mail;
    private Boolean isActive;
    private Boolean isRemoved;
    private String role;
    private Repo repo;
    private Statistic statistic;

}