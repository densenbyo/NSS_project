package cz.cvut.fel.ear.lingo.flashcard.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.facade.FlashCardFacade;
import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardModelMapper;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardModel2FlashcardMapper;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@RequiredArgsConstructor
@Component
public class FlashcardAdapter {

    private final FlashCardFacade flashCardFacade;
    private final FlashcardModel2FlashcardMapper flashcardModel2FlashcardMapper;
    private final Flashcard2FlashcardModelMapper flashcard2FlashcardModelMapper;

    public void createFlashcard(FlashcardModel flashcardModel, UserEntity userEntity) {
        Flashcard flashcard = flashcardModel2FlashcardMapper.toFlashcard(flashcardModel);
        flashcard.setCreatorId(userEntity.getId());
        flashCardFacade.createFlashcard(flashcard);
    }

    public FlashcardModel getFlashcardById(Long id) {
        var flashcard = flashCardFacade.findFlashcardById(id);
        return flashcard2FlashcardModelMapper.toFlashcardModel(flashcard);
    }

    public List<FlashcardModel> getListOfFlashcard() {
        var flashcards = flashCardFacade.findAllFlashcards();
        return flashcard2FlashcardModelMapper.toFlashcardModelList(flashcards);
    }
    public void updateFlashcard(FlashcardModel flashcardModel) {
        Flashcard flashcard = flashcardModel2FlashcardMapper.toFlashcard(flashcardModel);
        flashCardFacade.updateFlashcard(flashcard);
    }

    public void deleteFlashcard(Long id) {
        flashCardFacade.deleteFlashcard(id);
    }

    public void restoreFlashcard(Long id) {
        flashCardFacade.restoreFlashcard(id);
    }

    public List<FlashcardModel> getFlashcardsByWord(String word) {
        var flashcards = flashCardFacade.findFlashcardByWord(word);
        return flashcard2FlashcardModelMapper.toFlashcardModelList(flashcards);
    }
}
