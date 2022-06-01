package cz.cvut.fel.ear.lingo.flashcarddeck.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.repository.FlashcardDeckEntityRepository;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeckEntityMapper;
import cz.cvut.fel.ear.lingo.repo.entity.repository.RepoEntityRepository;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class FlashcardDeckJpaRepositoryAdapter implements FlashcardDeckRepositoryAdapter{

    private final FlashcardDeckEntityRepository flashcardDeckEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final RepoEntityRepository repoEntityRepository;
    private final Flashcard2FlashcardEntityMapper flashcard2FlashcardEntityMapper;
    private final FlashcardDeckEntityMapper flashcardDeckEntityMapper;

    @Override
    public void saveFlashcardDeck(FlashcardDeck flashcardDeck) {
        var userEntityOptional = userEntityRepository
                .findByIdAndIsRemovedIsFalse(flashcardDeck.getCreatorId());
        if (userEntityOptional.isPresent()) {
            List<FlashcardEntity> flashcardEntityList = flashcard2FlashcardEntityMapper
                    .toFlashcardEntityList(flashcardDeck.getFlashcards(),  userEntityOptional.get());
            FlashcardDeckEntity flashcardDeckEntity = new FlashcardDeckEntity();
            flashcardDeckEntity.setName(flashcardDeck.getName());
            flashcardDeckEntity.setDescription(flashcardDeck.getDescription());
            flashcardDeckEntity.setIsPublic(flashcardDeck.getIsPublic());
            flashcardDeckEntity.setIsRemoved(flashcardDeck.getIsRemoved());
            flashcardDeckEntity.setCreator(userEntityOptional.get());
            flashcardDeckEntity.setFlashcards(flashcardEntityList);
            flashcardDeckEntityRepository.save(flashcardDeckEntity);
            var repo = userEntityOptional.get().getRepo();
            repo.addFlashcardDeck(flashcardDeckEntity);
            repoEntityRepository.save(repo);
        }
    }

    @Override
    public List<FlashcardDeck> findAllFlashcards() {
        var flashcardDeckEntities = flashcardDeckEntityRepository
                .findFlashcardDeckEntitiesByIsRemovedIsFalse();
        return flashcardDeckEntityMapper.toFlashcardDeckList(flashcardDeckEntities);
    }

    @Override
    public FlashcardDeck findFlashcardDeckById(Long id) {
        var flashcardDeckEntityOptional = flashcardDeckEntityRepository.
                findFlashcardDeckEntityByIdAndIsRemovedIsFalse(id);
        return flashcardDeckEntityOptional.map(flashcardDeckEntityMapper::toFlashcardDeck).orElse(null);
    }

    @Override
    public void updateFlashcardDeck(Long id, FlashcardDeck flashcardDeck) {
        var flashcardDeckEntityOptional = flashcardDeckEntityRepository
                .findFlashcardDeckEntityByIdAndIsRemovedIsFalse(id);
        var userEntityOptional = userEntityRepository
                .findUserEntityById(flashcardDeck.getCreatorId());
        if (flashcardDeckEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            var userEntity = userEntityOptional.get();
            var flashcardDeckEntity = flashcardDeckEntityOptional.get();
            List<FlashcardEntity> flashcardEntities = flashcard2FlashcardEntityMapper
                    .toFlashcardEntityList(flashcardDeck.getFlashcards(), userEntityOptional.get());
            flashcardDeckEntity.setIsRemoved(flashcardDeck.getIsRemoved());
            flashcardDeckEntity.setIsPublic(flashcardDeck.getIsPublic());
            flashcardDeckEntity.setName(flashcardDeck.getName());
            flashcardDeckEntity.setDescription(flashcardDeck.getDescription());
            flashcardDeckEntity.setCreator(userEntity);
            flashcardDeckEntity.setFlashcards(flashcardEntities);
            flashcardDeckEntityRepository.save(flashcardDeckEntity);
        }
    }

    @Override
    public void deleteFlashcardDeck(Long id) {
        var flashcardDeckEntityOptional = flashcardDeckEntityRepository
                .findFlashcardDeckEntityByIdAndIsRemovedIsFalse(id);
        if (flashcardDeckEntityOptional.isPresent()) {
            var flashcardDeckEntity = flashcardDeckEntityOptional.get();
            flashcardDeckEntity.setIsRemoved(true);
            flashcardDeckEntityRepository.save(flashcardDeckEntity);
        }
    }

    @Override
    public void restoreFlashcardDeck(Long id) {
        var flashcardDeckEntityOptional = flashcardDeckEntityRepository
                .findFlashcardDeckEntityByIdAndIsRemovedIsFalse(id);
        if (flashcardDeckEntityOptional.isPresent()) {
            var flashcardDeckEntity = flashcardDeckEntityOptional.get();
            flashcardDeckEntity.setIsRemoved(false);
            flashcardDeckEntityRepository.save(flashcardDeckEntity);
        }
    }

    @Override
    public void addFlashcardToFlashcardDeck(Long id, Flashcard flashcard) {
        var flashcardDeckEntityOptional = flashcardDeckEntityRepository
                .findFlashcardDeckEntityByIdAndIsRemovedIsFalse(id);
        var userEntityOptional = userEntityRepository
                .findUserEntityById(flashcard.getCreatorId());
        if (flashcardDeckEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            var flashcardEntity = flashcard2FlashcardEntityMapper.toFlashcardEntity(flashcard, userEntityOptional.get());
            var flashcardDeckEntity = flashcardDeckEntityOptional.get();
            flashcardDeckEntity.addFlashcard(flashcardEntity);
            flashcardDeckEntityRepository.save(flashcardDeckEntity);
        }
    }

    @Override
    public void removeFlashcardToFlashcardDeck(Long id, Flashcard flashcard) {
        var flashcardDeckEntityOptional = flashcardDeckEntityRepository
                .findFlashcardDeckEntityByIdAndIsRemovedIsFalse(id);
        var userEntityOptional = userEntityRepository
                .findUserEntityById(flashcard.getCreatorId());
        if (flashcardDeckEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            var flashcardEntity = flashcard2FlashcardEntityMapper.toFlashcardEntity(flashcard, userEntityOptional.get());
            var flashcardDeckEntity = flashcardDeckEntityOptional.get();
            flashcardDeckEntity.removeFlashcard(flashcardEntity);
            flashcardDeckEntityRepository.save(flashcardDeckEntity);
        }
    }
}