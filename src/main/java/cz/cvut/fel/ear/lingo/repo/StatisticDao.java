package cz.cvut.fel.ear.lingo.repo;

import cz.cvut.fel.ear.lingo.repo.dao.BaseDao;
import cz.cvut.fel.ear.lingo.model.Statistic;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticDao extends BaseDao<Statistic> {

    public StatisticDao() {
        super(Statistic.class);
    }
}