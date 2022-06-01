package cz.cvut.fel.ear.lingo.statistics.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardProgress2FlashcardProgressModelMapper;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardProgressModel;
import cz.cvut.fel.ear.lingo.statistics.domain.Statistic;
import cz.cvut.fel.ear.lingo.statistics.model.StatisticModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Statistic2StatisticModelMapper {

    private final FlashcardProgress2FlashcardProgressModelMapper flashcardProgress2FlashcardProgressModelMapper;

    public StatisticModel toStatisticModel(Statistic statistic) {
        if (statistic == null) return null;
        List<FlashcardProgressModel> progresses = flashcardProgress2FlashcardProgressModelMapper
                .toFlashcardProgressModelList(statistic.getFlashcardProgresses());
        return StatisticModel.builder()
                .id(statistic.getId())
                .achievements(statistic.getAchievements())
                .flashcardProgressModels(progresses)
                .build();
    }

    public List<StatisticModel> toStatisticList(List<Statistic> statistics) {
        List<StatisticModel> statisticModels = new ArrayList<>(statistics.size());
        for (Statistic entity: statistics) {
            statisticModels.add(toStatisticModel(entity));
        }
        return statisticModels;
    }
}
