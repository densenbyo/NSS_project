package cz.cvut.fel.ear.lingo.tag.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardModel2FlashcardMapper;
import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import cz.cvut.fel.ear.lingo.tag.model.TagModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TagModel2TagMapper {

    private final FlashcardModel2FlashcardMapper flashcardModel2FlashcardMapper;

    public Tag toTag(TagModel tagModel) {
        if (tagModel == null) return null;
        var flashcards = flashcardModel2FlashcardMapper.toFlashcardList(tagModel.getFlashcardModels());
        return Tag.builder()
                .id(tagModel.getId())
                .name(tagModel.getName())
                .isRemoved(tagModel.getIsRemoved())
                .flashcards(flashcards)
                .relatedTagsId(tagModel.getRelatedTagIds())
                .build();
    }
}
