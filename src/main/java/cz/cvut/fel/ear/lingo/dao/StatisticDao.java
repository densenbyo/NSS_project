package cz.cvut.fel.ear.lingo.dao;

import cz.cvut.fel.ear.lingo.statistic_service.entity.StatisticEntity;
import org.springframework.stereotype.Repository;

@Repository
public class StatisticDao extends BaseDao<StatisticEntity> {

    public StatisticDao() {
        super(StatisticEntity.class);
    }
}