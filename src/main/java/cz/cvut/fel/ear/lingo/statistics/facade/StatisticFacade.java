package cz.cvut.fel.ear.lingo.statistics.facade;

import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import cz.cvut.fel.ear.lingo.statistics.use_case.ClearStatisticUseCase;
import cz.cvut.fel.ear.lingo.statistics.use_case.GetAllStatisticsUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StatisticFacade {

    private final GetAllStatisticsUseCase getAllStatisticsUseCase;
    private final ClearStatisticUseCase clearStatisticUseCase;

    public List<Statistic> findAllStatistics() {
        return getAllStatisticsUseCase.execute();
    }

    public void clearStatistic(Long id) {
        clearStatisticUseCase.execute(id);
    }
}
