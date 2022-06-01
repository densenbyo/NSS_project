package cz.cvut.fel.ear.lingo.statistics.adapter;

import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import cz.cvut.fel.ear.lingo.statistics.entity.repository.StatisticEntityRepository;
import cz.cvut.fel.ear.lingo.statistics.mapper.StatisticEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class StatisticJpaRepositoryAdapter implements StatisticRepositoryAdapter {

    private final StatisticEntityRepository statisticEntityRepository;
    private final StatisticEntityMapper statisticEntityMapper;

    @Override
    public List<Statistic> findAllStatistic() {
        var statisticEntities = statisticEntityRepository.findAll();
        return statisticEntityMapper.toStatisticList(statisticEntities);
    }

    @Override
    public void clearStatistic(Long id) {
        var statisticEntityOptional = statisticEntityRepository.findById(id);
        if (statisticEntityOptional.isPresent()) {
            var statisticEntity = statisticEntityOptional.get();
            statisticEntity.setFlashcardProgresses(new ArrayList<>());
            statisticEntityRepository.save(statisticEntity);
        }
    }
}
