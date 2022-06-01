package cz.cvut.fel.ear.lingo.flashcarddeck.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "FLASHCARDDECK")
public class FlashcardDeckEntity extends AbstractClassEntity {

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String name;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private String description;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Boolean isPublic;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Boolean isRemoved;

    @ManyToMany
    @OrderBy("word")
    @JsonView(Views.Public.class)
    private List<FlashcardEntity> flashcards;

    @OneToOne
    @JsonIgnore
    private UserEntity creator;

    public FlashcardDeckEntity() {
        this.isPublic = false;
        this.isRemoved = false;
    }

    public void addFlashcard(FlashcardEntity flashcardEntity){
        if (flashcards == null)
            flashcards = new ArrayList<>();
        flashcards.add(flashcardEntity);
    }

    public void removeFlashcard(FlashcardEntity flashcardEntity){
        if(flashcards != null)
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FlashcardDeckEntity that = (FlashcardDeckEntity) o;
        boolean result = Objects.equals(name, that.name);
        if (!Objects.equals(description, that.description)) result = false;
        if (!isPublic.equals(that.isPublic)) result = false;
        if (!isRemoved.equals(that.isRemoved)) result = false;
        if (!Objects.equals(flashcards, that.flashcards)) result = false;
        return result;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (description != null ? description.hashCode() : 0);
        result = 31 * result + isPublic.hashCode();
        result = 31 * result + isRemoved.hashCode();
        result = 31 * result + (flashcards != null ? flashcards.hashCode() : 0);
        return result;
    }
}