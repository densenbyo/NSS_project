package cz.cvut.fel.ear.lingo.tag.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import cz.cvut.fel.ear.lingo.tag.entity.TagEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class TagEntityMapper {

    private final FlashcardEntityMapper flashcardEntityMapper;

    public Tag toTag(TagEntity tagEntity) {
        if (tagEntity == null) return null;
        var flashcards = flashcardEntityMapper.toFlashcardList(tagEntity.getFlashcardEntities());
        List<Long> relatedTagIds = new ArrayList<>();
        for (TagEntity entity : tagEntity.getRelatedTags()) {
            relatedTagIds.add(entity.getId());
        }
        return Tag.builder()
                .id(tagEntity.getId())
                .name(tagEntity.getName())
                .isRemoved(tagEntity.getIsRemoved())
                .flashcards(flashcards)
                .relatedTagsId(relatedTagIds)
                .build();
    }

    public List<Tag> toTagList(List<TagEntity> tagEntities) {
        List<Tag> tags = new ArrayList<>(tagEntities.size());
        for (TagEntity entity: tagEntities) {
            tags.add(toTag(entity));
        }
        return tags;
    }
}
