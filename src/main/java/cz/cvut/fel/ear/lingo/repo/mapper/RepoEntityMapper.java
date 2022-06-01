package cz.cvut.fel.ear.lingo.repo.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardEntityMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeckEntityMapper;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.entity.RepoEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class RepoEntityMapper {

    private final FlashcardEntityMapper flashcardEntityMapper;
    private final FlashcardDeckEntityMapper flashcardDeckEntityMapper;

    public Repo toRepo(RepoEntity repoEntity) {
        if (repoEntity == null) return null;
        List<Flashcard> flashcards = flashcardEntityMapper.toFlashcardList(repoEntity.getFlashcards());
        List<FlashcardDeck> flashcardDecks = flashcardDeckEntityMapper.toFlashcardDeckList(repoEntity.getFlashcardDecks());
        return Repo.builder()
                .id(repoEntity.getId())
                .flashcardDecks(flashcardDecks)
                .flashcards(flashcards)
                .build();
    }

    public List<Repo> toRepoList(List<RepoEntity> repoEntities) {
        List<Repo> repos = new ArrayList<>(repoEntities.size());
        for (RepoEntity entity : repoEntities) {
            repos.add(toRepo(entity));
        }
        return repos;
    }
}
