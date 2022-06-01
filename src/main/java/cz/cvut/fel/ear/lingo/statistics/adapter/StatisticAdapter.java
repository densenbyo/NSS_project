package cz.cvut.fel.ear.lingo.statistics.adapter;

import cz.cvut.fel.ear.lingo.statistics.facade.StatisticFacade;
import cz.cvut.fel.ear.lingo.statistics.mapper.Statistic2StatisticModelMapper;
import cz.cvut.fel.ear.lingo.statistics.model.StatisticModel;
import cz.cvut.fel.ear.lingo.user.entity.enumeration.UserRole;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class StatisticAdapter {

    private final StatisticFacade statisticFacade;
    private final Statistic2StatisticModelMapper statistic2StatisticModelMapper;

    public List<StatisticModel> findAllStatistics() {
        var statistics = statisticFacade.findAllStatistics();
        return statistic2StatisticModelMapper.toStatisticList(statistics);
    }

    public void clearStatistic(Long statisticId, Long id, UserRole role) {
        if (role.equals(UserRole.USER) && !statisticId.equals(id)) {
            log.info("User with id: {} cannot changed statistic with id{}.", id, statisticId);
        } else {
            statisticFacade.clearStatistic(statisticId);
        }
    }

}
