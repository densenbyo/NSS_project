package cz.cvut.fel.ear.lingo.repo;

import cz.cvut.fel.ear.lingo.repo.dao.BaseDao;
import cz.cvut.fel.ear.lingo.model.Repo;
import org.springframework.stereotype.Repository;

@Repository
public class RepoDao extends BaseDao<Repo> {

    protected RepoDao() {
        super(Repo.class);
    }
}