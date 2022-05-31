package cz.cvut.fel.ear.lingo.statistic_service.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.flashcard_service.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard_service.entity.FlashcardProgressEntity;
import cz.cvut.fel.ear.lingo.flashcarddeck_service.entity.FlashcardDeckEntity;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
public class StatisticEntity extends AbstractClassEntity {

    @ElementCollection
    @JsonView(Views.Public.class)
    private List<String> achievements;

    @OneToMany
    @JsonView(Views.Public.class)
    private List<FlashcardProgressEntity> flashcardProgresses;

    public StatisticEntity() {
        this.achievements = new ArrayList<>();
    }

    public List<FlashcardProgressEntity> getProgressesOfDeck(FlashcardDeckEntity deck) {
        List<FlashcardProgressEntity> progresses = new ArrayList<>();
        for (FlashcardEntity f : deck.getFlashcards()) {
            if (contains(f)) {
                progresses.add(flashcardProgresses.stream()
                        .filter(v -> v.getFlashcardEntity().equals(f))
                        .findFirst()
                        .get());
            } else {
                progresses.add(new FlashcardProgressEntity(f));
            }
        }

        return progresses;
    }

    public Boolean contains(FlashcardEntity flashcardEntity) {
        return flashcardProgresses.stream().map(FlashcardProgressEntity::getFlashcardEntity).anyMatch(v -> v.equals(flashcardEntity));
    }

    public void addFlashcard(FlashcardEntity flashcardEntity) {
        flashcardProgresses.add(
                new FlashcardProgressEntity(flashcardEntity)
        );
    }

    public void removeFlashcard(FlashcardEntity flashcardEntity) {
        int i = -1;
        for (FlashcardProgressEntity fp : flashcardProgresses) {
            if (fp.getFlashcardEntity().equals(flashcardEntity)) {
                i = flashcardProgresses.indexOf(fp);
            }
        }
        if (i != -1) {
            flashcardProgresses.remove(i);
        }
    }

    public void addAchievements(String toAdd) {
        Objects.requireNonNull(toAdd);
        if (achievements == null)
            achievements = new ArrayList<>();
        achievements.add(toAdd);
    }

    public void removeAchievements(String toDelete) {
        Objects.requireNonNull(toDelete);
        if (achievements == null)
            return;
        achievements.remove(toDelete);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticEntity statisticEntity = (StatisticEntity) o;
        if (!Objects.equals(achievements, statisticEntity.achievements))
            return false;
        return Objects.equals(flashcardProgresses, statisticEntity.flashcardProgresses);
    }

    @Override
    public int hashCode() {
        int result = achievements != null ? achievements.hashCode() : 0;
        result = 31 * result + (flashcardProgresses != null ? flashcardProgresses.hashCode() : 0);
        return result;
    }
}