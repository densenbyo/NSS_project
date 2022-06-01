package cz.cvut.fel.ear.lingo.flashcard.mapper;

import cz.cvut.fel.ear.lingo.content.entity.AbstractContentEntity;
import cz.cvut.fel.ear.lingo.content.mapper.AbstractContent2AbstractContentEntityMapper;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardProgressEntity;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Flashcard2FlashcardEntityMapper {

    private final AbstractContent2AbstractContentEntityMapper abstractContentEntityMapper;
    private final FlashcardProgress2FlashcardProgressEntityMapper flashcardProgress2FlashcardProgressEntityMapper;

    public FlashcardEntity toFlashcardEntity(Flashcard flashcard, UserEntity userEntity) {
        if (flashcard == null) return null;
        List<AbstractContentEntity> contentEntityList = abstractContentEntityMapper
                .toAbstractContentEntityList(flashcard.getAbstractContents(), userEntity);
        List<FlashcardProgressEntity> progressEntities = flashcardProgress2FlashcardProgressEntityMapper.
                toFlashcardProgressEntityList(flashcard.getFlashcardProgresses());
        FlashcardEntity flashcardEntity = new FlashcardEntity();
        flashcardEntity.setId(flashcard.getId());
        flashcardEntity.setWord(flashcard.getWord());
        flashcardEntity.setTranslation(flashcard.getTranslation());
        flashcardEntity.setTranslation(flashcard.getTranslation());
        flashcardEntity.setRemoved(flashcard.isRemoved());
        flashcardEntity.setCreator(userEntity);
        flashcardEntity.setContents(contentEntityList);
        flashcardEntity.setFlashcardProgresses(progressEntities);
        return flashcardEntity;
    }

    public List<FlashcardEntity> toFlashcardEntityList(List<Flashcard> flashcards, UserEntity userEntity) {
        List<FlashcardEntity> flashcardEntities = new ArrayList<>(flashcards.size());
        for (Flashcard flashcard : flashcards) {
            flashcardEntities.add(toFlashcardEntity(flashcard, userEntity));
        }
        return flashcardEntities;
    }
}
