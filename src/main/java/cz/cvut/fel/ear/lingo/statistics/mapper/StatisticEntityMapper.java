package cz.cvut.fel.ear.lingo.statistics.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardProgressEntityMapper;
import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import cz.cvut.fel.ear.lingo.statistics.entity.StatisticEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticEntityMapper {

    private final FlashcardProgressEntityMapper flashcardProgressEntityMapper;

    public Statistic toStatistic(StatisticEntity statisticEntity) {
        if (statisticEntity == null) return null;
        List<FlashcardProgress> progresses = flashcardProgressEntityMapper
                .toFlashcardProgressList(statisticEntity.getFlashcardProgresses());
        return Statistic.builder()
                .id(statisticEntity.getId())
                .achievements(statisticEntity.getAchievements())
                .flashcardProgresses(progresses)
                .build();
    }

    public List<Statistic> toStatisticList(List<StatisticEntity> statisticEntities) {
        List<Statistic> statistics = new ArrayList<>(statisticEntities.size());
        for (StatisticEntity entity: statisticEntities) {
            statistics.add(toStatistic(entity));
        }
        return statistics;
    }
}
