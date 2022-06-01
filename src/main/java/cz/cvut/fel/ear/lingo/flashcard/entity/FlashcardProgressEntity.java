package cz.cvut.fel.ear.lingo.flashcard.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Objects;

@Getter
@Setter
@ToString
@Entity
@Table(name = "FLASHCARD_PROGRESS")
public class FlashcardProgressEntity extends AbstractClassEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Double progressDegree;

    @ManyToOne
    @JsonView(Views.Public.class)
    private FlashcardEntity flashcardEntity;

    public FlashcardProgressEntity() {
        progressDegree = 0.;
    }

    public FlashcardProgressEntity(FlashcardEntity flashcardEntity) {
        this.flashcardEntity = flashcardEntity;
        this.progressDegree = 0.;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlashcardProgressEntity that = (FlashcardProgressEntity) o;
        if (!Objects.equals(progressDegree, that.progressDegree))
            return false;
        return Objects.equals(flashcardEntity, that.flashcardEntity);
    }

    @Override
    public int hashCode() {
        int result = progressDegree != null ? progressDegree.hashCode() : 0;
        result = 31 * result + (flashcardEntity != null ? flashcardEntity.hashCode() : 0);
        return result;
    }
}