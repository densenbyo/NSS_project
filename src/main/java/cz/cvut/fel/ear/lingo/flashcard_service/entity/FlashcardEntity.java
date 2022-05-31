package cz.cvut.fel.ear.lingo.flashcard_service.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.content_service.entity.AbstractContentEntity;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import cz.cvut.fel.ear.lingo.user_service.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Setter
@Getter
@Entity
@NamedNativeQuery(
        name = "findByWord",
        query = "SELECT * " +
                "FROM Flashcard f " +
                "WHERE f.word LIKE ? " +
                "AND f.isRemoved = false",
        resultSetMapping = "findByWordMapping"
)
@SqlResultSetMapping(
        name = "findByWordMapping",
        entities = {
                @EntityResult(
                        entityClass = FlashcardEntity.class,
                        fields = {
                                @FieldResult(name = "word", column = "word"),
                                @FieldResult(name = "isRemoved", column = "isRemoved"),
                                @FieldResult(name = "translation", column = "translation"),
                                @FieldResult(name = "id", column = "id")
                        }
                )
        }
)
@Table(name = "FLASHCARD")
public class FlashcardEntity extends AbstractClassEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String word;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private boolean removed;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String translation;

    @OneToMany
    @JsonIgnore
    private List<AbstractContentEntity> contents;

    @OneToMany
    @JsonView(Views.Public.class)
    private List<FlashcardProgressEntity> flashcardProgresses;

    @OneToOne
    @JsonIgnore
    private UserEntity creator;

    public FlashcardEntity() {
        this.removed = false;
    }

    public FlashcardEntity(String word, String translation, UserEntity creator) {
        this.word = word;
        this.translation = translation;
        this.creator = creator;
        this.removed = false;
    }

    public void addContent(AbstractContentEntity abstractContentEntity){
        Objects.requireNonNull(abstractContentEntity);
        if (contents == null)
            contents = new ArrayList<>();
        contents.add(abstractContentEntity);
    }

    public void removeContent(AbstractContentEntity abstractContentEntity){
        if(contents != null)
            contents.removeIf(c-> Objects.equals(c.getId(), abstractContentEntity.getId()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlashcardEntity flashcardEntity = (FlashcardEntity) o;
        if (removed != flashcardEntity.removed) return false;
        if (!Objects.equals(word, flashcardEntity.word)) return false;
        if (!Objects.equals(translation, flashcardEntity.translation))
            return false;
        if (!Objects.equals(contents, flashcardEntity.contents)) return false;
        if (!Objects.equals(flashcardProgresses, flashcardEntity.flashcardProgresses))
            return false;
        return Objects.equals(creator, flashcardEntity.creator);
    }

    @Override
    public int hashCode() {
        int result = word != null ? word.hashCode() : 0;
        result = 31 * result + (removed ? 1 : 0);
        result = 31 * result + (translation != null ? translation.hashCode() : 0);
        result = 31 * result + (contents != null ? contents.hashCode() : 0);
        result = 31 * result + (flashcardProgresses != null ? flashcardProgresses.hashCode() : 0);
        result = 31 * result + (creator != null ? creator.hashCode() : 0);
        return result;
    }
}