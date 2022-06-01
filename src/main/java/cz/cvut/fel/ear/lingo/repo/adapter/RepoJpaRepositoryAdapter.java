package cz.cvut.fel.ear.lingo.repo.adapter;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeck2FlashcardDeckEntityMapper;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.entity.repository.RepoEntityRepository;
import cz.cvut.fel.ear.lingo.repo.mapper.RepoEntityMapper;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Component
public class RepoJpaRepositoryAdapter implements RepoRepositoryAdapter {

    private final RepoEntityRepository repoEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final RepoEntityMapper repoEntityMapper;
    private final Flashcard2FlashcardEntityMapper flashcard2FlashcardEntityMapper;
    private final FlashcardDeck2FlashcardDeckEntityMapper flashcardDeck2FlashcardDeckEntityMapper;

    @Override
    public Repo getFlashcardById(Long id) {
        var repoEntity = repoEntityRepository.findById(id);
        return repoEntity.map(repoEntityMapper::toRepo).orElse(null);
    }

    @Override
    public List<Repo> findAll() {
        var repoEntities = repoEntityRepository.findAll();
        return repoEntityMapper.toRepoList(repoEntities);
    }

    @Override
    public void addFlashcardToRepo(Long id, Flashcard flashcard) {
        var repoEntityOptional = repoEntityRepository.findById(id);
        var userEntityOptional = userEntityRepository.findUserEntityByRepoId(id);
        if (repoEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            FlashcardEntity entity = flashcard2FlashcardEntityMapper.toFlashcardEntity(flashcard, userEntityOptional.get());
            var repoEntity = repoEntityOptional.get();
            repoEntity.addFlashcard(entity);
            repoEntityRepository.save(repoEntity);
        }
    }

    @Override
    public void removeFlashcardToRepo(Long id, Flashcard flashcard) {
        var repoEntityOptional = repoEntityRepository.findById(id);
        var userEntityOptional = userEntityRepository.findUserEntityByRepoId(id);
        if (repoEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            FlashcardEntity entity = flashcard2FlashcardEntityMapper.toFlashcardEntity(flashcard, userEntityOptional.get());
            var repoEntity = repoEntityOptional.get();
            repoEntity.removeFlashcard(entity);
            repoEntityRepository.save(repoEntity);
        }
    }

    @Override
    public void addFlashcardDeckToRepo(Long id, FlashcardDeck flashcardDeck) {
        var repoEntityOptional = repoEntityRepository.findById(id);
        var userEntityOptional = userEntityRepository.findUserEntityByRepoId(id);
        if (repoEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            FlashcardDeckEntity entity = flashcardDeck2FlashcardDeckEntityMapper
                    .toFlashcardDeckEntity(flashcardDeck, userEntityOptional.get());
            var repoEntity = repoEntityOptional.get();
            repoEntity.addFlashcardDeck(entity);
            repoEntityRepository.save(repoEntity);
        }
    }

    @Override
    public void removeFlashcardDeckToRepo(Long id, FlashcardDeck flashcardDeck) {
        var repoEntityOptional = repoEntityRepository.findById(id);
        var userEntityOptional = userEntityRepository.findById(id);
        if (repoEntityOptional.isPresent() && userEntityOptional.isPresent()) {
            FlashcardDeckEntity entity = flashcardDeck2FlashcardDeckEntityMapper
                    .toFlashcardDeckEntity(flashcardDeck, userEntityOptional.get());
            var repoEntity = repoEntityOptional.get();
            repoEntity.removeFlashcardDeck(entity);
            repoEntityRepository.save(repoEntity);
        }
    }
}
