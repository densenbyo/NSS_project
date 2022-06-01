package cz.cvut.fel.ear.lingo.flashcarddeck.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardModel2FlashcardMapper;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.facade.FlashcardDeckFacade;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeck2FlashcardDeckModelMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeckModel2FlashcardDeckMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
import cz.cvut.fel.ear.lingo.user.domain.User;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user.entity.enumeration.UserRole;
import cz.cvut.fel.ear.lingo.user.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FlashcardDeckAdapter {

    private final FlashcardDeckFacade flashcardDeckFacade;
    private final FlashcardDeckModel2FlashcardDeckMapper flashcardDeckModel2FlashcardDeckMapper;
    private final FlashcardDeck2FlashcardDeckModelMapper flashcardDeck2FlashcardDeckModelMapper;
    private final FlashcardModel2FlashcardMapper flashcardModel2FlashcardMapper;
    private final UserEntityMapper userEntityMapper;

    public void saveFlashcardDeck(FlashcardDeckModel flashcardDeckModel, UserEntity userEntity) {
        User user = userEntityMapper.toUser(userEntity);
        flashcardDeckModel.setCreatorId(user.getId());
        FlashcardDeck flashcardDeck = flashcardDeckModel2FlashcardDeckMapper.toFlashcardDeck(flashcardDeckModel);
        flashcardDeckFacade.createFlashcardDeck(flashcardDeck);
    }
    public List<FlashcardDeckModel> findAllFlashcardDecks(UserRole role) {
        var flashcardDeckList = flashcardDeckFacade.findAllFlashcardDeck();
        if (role.equals(UserRole.USER)) {
            flashcardDeckList = flashcardDeckList.stream().filter(s->s.getIsPublic().equals(true)).toList();
        }
        return flashcardDeck2FlashcardDeckModelMapper.toFlashcardDeckModelList(flashcardDeckList);
    }

    public FlashcardDeckModel findFlashcardDeckById(Long id, UserRole role) {
        var flashcardDeck = flashcardDeckFacade.findFlashcardDeckById(id);
        if (role.equals(UserRole.USER)) {
            flashcardDeck = flashcardDeck.getIsPublic().equals(true) ? flashcardDeck : null;
        }
        assert flashcardDeck != null;
        return flashcardDeck2FlashcardDeckModelMapper.toFlashcardDeckModel(flashcardDeck);
    }

    public void updateFlashcardDeck(Long id, UserEntity userEntity, FlashcardDeckModel flashcardDeckModel) {
        FlashcardDeck flashcardDeck = flashcardDeckModel2FlashcardDeckMapper.toFlashcardDeck(flashcardDeckModel);
        if (userEntity.getRole().equals(UserRole.USER) && !userEntity.getId().equals(flashcardDeck.getCreatorId())) {
            log.info("User with id{} cannot change flashcard with id {}.", userEntity.getId(), id);
        } else {
            flashcardDeckFacade.updateFlashcardDeck(id, flashcardDeck);
        }
    }

    public void deleteFlashcardDeck(Long id) {
        flashcardDeckFacade.deleteFlashcardDeck(id);
    }

    public void restoreFlashcardDeck(Long id) {
        flashcardDeckFacade.restoreFlashcardDeck(id);
    }

    public void addFlashcardToFlashcardDeck(Long flashcardDeckId, FlashcardModel flashcardModel) {
        Flashcard flashcard = flashcardModel2FlashcardMapper.toFlashcard(flashcardModel);
        flashcardDeckFacade.addFlashcardToFlashcardDeck(flashcardDeckId, flashcard);
    }

    public void removeFlashcardToFlashcardDeck(Long flashcardDeckId, FlashcardModel flashcardModel) {
        Flashcard flashcard = flashcardModel2FlashcardMapper.toFlashcard(flashcardModel);
        flashcardDeckFacade.removeFlashcardToFlashcardDeck(flashcardDeckId, flashcard);
    }
}
