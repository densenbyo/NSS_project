package cz.cvut.fel.ear.lingo.statistics.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.FlashcardProgress;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardProgressModel2FlashcardProgressMapper;
import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import cz.cvut.fel.ear.lingo.statistics.model.StatisticModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class StatisticModel2StatisticMapper {

    private final FlashcardProgressModel2FlashcardProgressMapper flashcardProgressModel2FlashcardProgressMapper;

    public Statistic toStatistic(StatisticModel statisticModel) {
        if (statisticModel == null) return null;
        List<FlashcardProgress> progresses = flashcardProgressModel2FlashcardProgressMapper
                .toFlashcardProgressList(statisticModel.getFlashcardProgressModels());
        return Statistic.builder()
                .id(statisticModel.getId())
                .achievements(statisticModel.getAchievements())
                .flashcardProgresses(progresses)
                .build();
    }
}
