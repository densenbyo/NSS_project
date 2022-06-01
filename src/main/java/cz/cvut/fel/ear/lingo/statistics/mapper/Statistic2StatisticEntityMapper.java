package cz.cvut.fel.ear.lingo.statistics.mapper;

import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardProgressEntity;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardProgress2FlashcardProgressEntityMapper;
import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import cz.cvut.fel.ear.lingo.statistics.entity.StatisticEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Statistic2StatisticEntityMapper {

    private final FlashcardProgress2FlashcardProgressEntityMapper flashcardProgress2FlashcardProgressEntityMapper;

    public StatisticEntity toStatisticEntity(Statistic statistic) {
        if (statistic == null) return null;
        List<FlashcardProgressEntity> progressEntities = flashcardProgress2FlashcardProgressEntityMapper
                .toFlashcardProgressEntityList(statistic.getFlashcardProgresses());
        StatisticEntity statisticEntity = new StatisticEntity();
        statisticEntity.setId(statistic.getId());
        statisticEntity.setAchievements(statistic.getAchievements());
        statisticEntity.setFlashcardProgresses(progressEntities);
        return statisticEntity;
    }
}
