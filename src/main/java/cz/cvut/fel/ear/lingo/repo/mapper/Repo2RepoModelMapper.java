package cz.cvut.fel.ear.lingo.repo.mapper;

import cz.cvut.fel.ear.lingo.flashcard.mapper.Flashcard2FlashcardModelMapper;
import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeck2FlashcardDeckModelMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.model.RepoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class Repo2RepoModelMapper {

    private final Flashcard2FlashcardModelMapper flashcard2FlashcardModelMapper;
    private final FlashcardDeck2FlashcardDeckModelMapper flashcardDeck2FlashcardDeckModelMapper;

    public RepoModel toRepoModel(Repo repo) {
        if (repo == null) return null;
        List<FlashcardModel> flashcards = flashcard2FlashcardModelMapper
                .toFlashcardModelList(repo.getFlashcards());
        List<FlashcardDeckModel> flashcardDecks = flashcardDeck2FlashcardDeckModelMapper
                .toFlashcardDeckModelList(repo.getFlashcardDecks());
        return RepoModel.builder()
                .id(repo.getId())
                .flashcardsModel(flashcards)
                .flashcardDecksModel(flashcardDecks)
                .build();
    }

    public List<RepoModel> toRepoModelList(List<Repo> repos) {
        List<RepoModel> repoEntities = new ArrayList<>(repos.size());
        for (Repo entity : repos) {
            repoEntities.add(toRepoModel(entity));
        }
        return repoEntities;
    }
}
