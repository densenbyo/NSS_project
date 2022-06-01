package cz.cvut.fel.ear.lingo.statistics.use_case;

import cz.cvut.fel.ear.lingo.statistics.adapter.StatisticRepositoryAdapter;
import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllStatisticsUseCase {

    private final StatisticRepositoryAdapter adapter;

    public List<Statistic> execute() {
        return adapter.findAllStatistic();
    }
}
