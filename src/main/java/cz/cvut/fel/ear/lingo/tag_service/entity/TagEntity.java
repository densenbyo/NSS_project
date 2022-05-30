package cz.cvut.fel.ear.lingo.tag_service.entity;

import com.fasterxml.jackson.annotation.JsonView;
import cz.cvut.fel.ear.lingo.flashcard_service.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.model.abstracts.AbstractClassEntity;
import cz.cvut.fel.ear.lingo.model.util.Views;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
public class TagEntity extends AbstractClassEntity {

    @Basic(optional = false)
    @Column(nullable = false, unique = true)
    @JsonView(Views.Public.class)
    private String name;

    @ManyToMany
    private List<TagEntity> relatedTagEntities;

    @ManyToMany
    @JsonView(Views.Public.class)
    private List<FlashcardEntity> flashcardEntities;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Boolean isRemoved;

    public TagEntity(String name) {
        this.name = name;
        this.isRemoved = false;
    }

    public void addRelatedTag(TagEntity tagEntity) {
        Objects.requireNonNull(tagEntity);
        if (relatedTagEntities == null)
            relatedTagEntities = new ArrayList<>();
        relatedTagEntities.add(tagEntity);
    }

    public void removeRelatedTag(TagEntity tagEntity) {
        Objects.requireNonNull(tagEntity);
        if(relatedTagEntities == null || tagEntity.relatedTagEntities == null)
            return;
        relatedTagEntities.remove(tagEntity);
    }

    public void addFlashcard(FlashcardEntity flashcardEntity) {
        Objects.requireNonNull(flashcardEntity);
        if (flashcardEntities == null)
            flashcardEntities =  new ArrayList<>();
        flashcardEntities.add(flashcardEntity);
    }

    public void removeFlashcard(FlashcardEntity flashcardEntity) {
        if(flashcardEntities != null)
            flashcardEntities.removeIf(c-> Objects.equals(c.getId(), flashcardEntity.getId()));
    }

    public boolean contains(FlashcardEntity flashcardEntity) {
        final Iterator<FlashcardEntity> it = flashcardEntities.iterator();
        while (it.hasNext()) {
            final FlashcardEntity curr = it.next();
            if (Objects.equals(flashcardEntity.getId(), curr.getId()))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TagEntity tagEntity = (TagEntity) o;
        if (!Objects.equals(name, tagEntity.name)) return false;
        if (!Objects.equals(relatedTagEntities, tagEntity.relatedTagEntities)) return false;
        return Objects.equals(flashcardEntities, tagEntity.flashcardEntities);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (relatedTagEntities != null ? relatedTagEntities.hashCode() : 0);
        result = 31 * result + (flashcardEntities != null ? flashcardEntities.hashCode() : 0);
        return result;
    }
}