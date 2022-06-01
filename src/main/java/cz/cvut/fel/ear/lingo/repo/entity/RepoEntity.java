package cz.cvut.fel.ear.lingo.repo.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "REPO")
public class RepoEntity extends AbstractClassEntity {
    @ManyToMany
    @OrderBy("name")
    @JsonView(Views.Public.class)
    private List<FlashcardDeckEntity> flashcardDecks;

    @ManyToMany
    @JsonView(Views.Public.class)
    private List<FlashcardEntity> flashcards;

    public RepoEntity() {
        this.flashcardDecks = new ArrayList<>();
        this.flashcards = new ArrayList<>();
    }

    public void addFlashcardDeck(FlashcardDeckEntity toAdd) {
        flashcardDecks.add(toAdd);
        if (toAdd.getFlashcards() != null) {
            for (FlashcardEntity flashcardEntity : toAdd.getFlashcards()) {
                addFlashcard(flashcardEntity);
            }
        }
    }

    public void removeFlashcardDeck(FlashcardDeckEntity flashcardDeckEntity) {
        if (flashcardDecks != null)
            flashcardDecks.removeIf(c -> Objects.equals(c.getId(), flashcardDeckEntity.getId()));
    }

    public void addFlashcard(FlashcardEntity flashcardEntity) {
        flashcards.add(flashcardEntity);
    }

    public void removeFlashcard(FlashcardEntity flashcardEntity) {
        if (flashcards != null)
            flashcards.removeIf(c -> Objects.equals(c.getId(), flashcardEntity.getId()));
    }

//    public boolean contains(FlashcardEntity flashcardEntity) {
//        final Iterator<FlashcardEntity> it = flashcards.iterator();
//        while (it.hasNext()) {
//            final FlashcardEntity curr = it.next();
//            if (Objects.equals(flashcardEntity.getId(), curr.getId()))
//                return true;
//        }
//        return false;
//    }
//
//    public boolean contains(FlashcardDeckEntity flashcardDeckEntity) {
//        final Iterator<FlashcardDeckEntity> it = flashcardDecks.iterator();
//        while (it.hasNext()) {
//            final FlashcardDeckEntity curr = it.next();
//            if (Objects.equals(flashcardDeckEntity.getId(), curr.getId()))
//                return true;
//        }
//        return false;
//    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RepoEntity repo = (RepoEntity) o;
        if (!Objects.equals(flashcardDecks, repo.getFlashcardDecks()))
            return false;
        return Objects.equals(flashcards, repo.getFlashcards());
    }

    @Override
    public int hashCode() {
        int result = flashcardDecks != null ? flashcardDecks.hashCode() : 0;
        result = 31 * result + (flashcards != null ? flashcards.hashCode() : 0);
        return result;
    }
}
