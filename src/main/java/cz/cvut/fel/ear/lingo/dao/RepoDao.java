package cz.cvut.fel.ear.lingo.dao;

import org.springframework.stereotype.Repository;

@Repository
public class RepoDao extends BaseDao<Repo> {

    protected RepoDao() {
        super(Repo.class);
    }
}