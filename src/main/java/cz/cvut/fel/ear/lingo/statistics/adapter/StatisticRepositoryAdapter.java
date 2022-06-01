package cz.cvut.fel.ear.lingo.statistics.adapter;

import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;

import java.util.List;

public interface StatisticRepositoryAdapter {

    List<Statistic> findAllStatistic();

    void clearStatistic(Long id);
}
