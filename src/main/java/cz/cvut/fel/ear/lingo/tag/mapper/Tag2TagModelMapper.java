package cz.cvut.fel.ear.lingo.tag.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardModelMapper;
import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import cz.cvut.fel.ear.lingo.tag.model.TagModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Tag2TagModelMapper {

    private final Flashcard2FlashcardModelMapper flashcard2FlashcardModelMapper;

    public TagModel toTagModel(Tag tag) {
        if (tag == null) return null;
        var flashcardModels = flashcard2FlashcardModelMapper.toFlashcardModelList(tag.getFlashcards());
        return TagModel.builder()
                .id(tag.getId())
                .name(tag.getName())
                .isRemoved(tag.getIsRemoved())
                .flashcardModels(flashcardModels)
                .relatedTagIds(tag.getRelatedTagsId())
                .build();
    }

    public List<TagModel> toTagModelList(List<Tag> tags) {
        List<TagModel> tagModels = new ArrayList<>(tags.size());
        for (Tag entity: tags) {
            tagModels.add(toTagModel(entity));
        }
        return tagModels;
    }
}
