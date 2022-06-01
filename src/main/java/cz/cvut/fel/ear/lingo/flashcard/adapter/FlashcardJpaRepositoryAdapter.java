package cz.cvut.fel.ear.lingo.flashcard.adapter;

import cz.cvut.fel.ear.lingo.content.entity.AbstractContentEntity;
import cz.cvut.fel.ear.lingo.content.mapper.AbstractContent2AbstractContentEntityMapper;
import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardProgressEntity;
import cz.cvut.fel.ear.lingo.flashcard.entity.repository.FlashcardEntityRepository;
import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardProgress2FlashcardProgressEntityMapper;
import cz.cvut.fel.ear.lingo.repo.entity.repository.RepoEntityRepository;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class FlashcardJpaRepositoryAdapter implements FlashcardRepositoryAdapter {

    private final UserEntityRepository userEntityRepository;
    private final FlashcardEntityMapper flashcardEntityMapper;
    private final FlashcardEntityRepository flashcardEntityRepository;
    private final RepoEntityRepository repoEntityRepository;
    private final Flashcard2FlashcardEntityMapper flashcard2FlashcardEntityMapper;
    private final FlashcardProgress2FlashcardProgressEntityMapper flashcardProgress2FlashcardProgressEntityMapper;
    private final AbstractContent2AbstractContentEntityMapper abstractContent2AbstractContentEntityMapper;

    @Override
    public void createFlashcard(Flashcard flashcard) {
        var creatorEntityOptional = userEntityRepository.findUserEntityById(flashcard.getCreatorId());
        if (creatorEntityOptional.isPresent()) {
            FlashcardEntity flashcardEntity = flashcard2FlashcardEntityMapper.toFlashcardEntity(flashcard, creatorEntityOptional.get());
            flashcardEntity.setCreator(creatorEntityOptional.get());
            flashcardEntityRepository.save(flashcardEntity);
            var repo = creatorEntityOptional.get().getRepo();
            repo.addFlashcard(flashcardEntity);
            repoEntityRepository.save(repo);
        }
    }

    @Override
    public Flashcard getFlashcardById(Long id) {
        var flashcardEntity = flashcardEntityRepository.findFlashcardEntityByIdAndRemovedIsFalse(id);
        return flashcardEntity.map(flashcardEntityMapper::toFlashcard).orElse(null);
    }

    @Override
    public List<Flashcard> findAllFlashcards() {
        var flashcardsOptional = flashcardEntityRepository.findFlashcardEntitiesByRemovedIsFalse();
        return flashcardEntityMapper.toFlashcardList(flashcardsOptional);
    }

    @Override
    public void updateFlashcard(Flashcard flashcard) {
        var flashcardEntityOptional = flashcardEntityRepository
                .findFlashcardEntityByIdAndRemovedIsFalse(flashcard.getId());
        var userEntityOptional = userEntityRepository
                .findUserEntityById(flashcard.getCreatorId());

        if (flashcardEntityOptional.isEmpty()) {
            log.info("Not found flashcard to update {}.", flashcard);
        }

        if (userEntityOptional.isEmpty()) {
            log.info("Not found creator user with id {}.", flashcard.getCreatorId());
        }

        if (flashcardEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            List<FlashcardProgressEntity> progressEntities = flashcardProgress2FlashcardProgressEntityMapper
                    .toFlashcardProgressEntityList(flashcard.getFlashcardProgresses());
            List<AbstractContentEntity> contentEntities = abstractContent2AbstractContentEntityMapper
                    .toAbstractContentEntityList(flashcard.getAbstractContents(), userEntityOptional.get());
            var flashcardEntity = flashcardEntityOptional.get();
            flashcardEntity.setRemoved(flashcard.isRemoved());
            flashcardEntity.setWord(flashcard.getWord());
            flashcardEntity.setTranslation(flashcard.getTranslation());
            flashcardEntity.setCreator(userEntityOptional.get());
            flashcardEntity.setFlashcardProgresses(progressEntities);
            flashcardEntity.setContents(contentEntities);
            flashcardEntityRepository.save(flashcardEntity);
        }
    }

    @Override
    public void deleteFlashcard(Long id) {
        var flashcardEntityOptional = flashcardEntityRepository
                .findFlashcardEntityByIdAndRemovedIsFalse(id);
        if (flashcardEntityOptional.isPresent()) {
            var flashcardEntity = flashcardEntityOptional.get();
            flashcardEntity.setRemoved(true);
            flashcardEntityRepository.save(flashcardEntity);
        }
    }

    @Override
    public void restoreFlashcard(Long id) {
        var flashcardEntityOptional = flashcardEntityRepository.findById(id);
        if (flashcardEntityOptional.isPresent()) {
            var flashcardEntity = flashcardEntityOptional.get();
            flashcardEntity.setRemoved(false);
            flashcardEntityRepository.save(flashcardEntity);
        }
    }

    @Override
    public List<Flashcard> getFlashcardByWord(String word) {
        var flashcardsOptional = flashcardEntityRepository
                .findFlashcardEntitiesByWordAndRemovedIsFalse(word);
        return flashcardEntityMapper.toFlashcardList(flashcardsOptional);
    }
}
