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
    private List<TagEntity> relatedTags;

    @ManyToMany
    @JsonView(Views.Public.class)
    private List<FlashcardEntity> flashcardEntities;

    @Basic(optional = false)
    @Column(nullable = false)
    @JsonView(Views.Public.class)
    private Boolean isRemoved;

    public void addRelatedTag(TagEntity tagEntity) {
        Objects.requireNonNull(tagEntity);
        if (relatedTags == null)
            relatedTags = new ArrayList<>();
        relatedTags.add(tagEntity);
    }

    public void removeRelatedTag(TagEntity tagEntity) {
        Objects.requireNonNull(tagEntity);
        if(relatedTags == null || tagEntity.relatedTags == null)
            return;
        relatedTags.remove(tagEntity);
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
        if (!Objects.equals(relatedTags, tagEntity.relatedTags)) return false;
        return Objects.equals(flashcardEntities, tagEntity.flashcardEntities);
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (relatedTags != null ? relatedTags.hashCode() : 0);
        result = 31 * result + (flashcardEntities != null ? flashcardEntities.hashCode() : 0);
        return result;
    }
}