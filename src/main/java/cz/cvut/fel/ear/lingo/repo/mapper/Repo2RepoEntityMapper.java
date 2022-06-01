package cz.cvut.fel.ear.lingo.repo.mapper;

import cz.cvut.fel.ear.lingo.flashcard.entity.FlashcardEntity;
import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.entity.FlashcardDeckEntity;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeck2FlashcardDeckEntityMapper;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.entity.RepoEntity;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Repo2RepoEntityMapper {

    private final Flashcard2FlashcardEntityMapper flashcard2FlashcardEntityMapper;
    private final FlashcardDeck2FlashcardDeckEntityMapper flashcardDeck2FlashcardDeckEntityMapper;

    public RepoEntity toRepoEntity(Repo repo, UserEntity userEntity) {
        if (repo == null) return null;
        List<FlashcardEntity> flashcardEntities = flashcard2FlashcardEntityMapper
                .toFlashcardEntityList(repo.getFlashcards(), userEntity);
        List<FlashcardDeckEntity> flashcardDeckEntities = flashcardDeck2FlashcardDeckEntityMapper
                .toFlashcardDeckEntitiesList(repo.getFlashcardDecks(), userEntity);
        RepoEntity repoEntity = new RepoEntity();
        repoEntity.setId(repo.getId());
        repoEntity.setFlashcards(flashcardEntities);
        repoEntity.setFlashcardDecks(flashcardDeckEntities);
        return repoEntity;
    }
}
