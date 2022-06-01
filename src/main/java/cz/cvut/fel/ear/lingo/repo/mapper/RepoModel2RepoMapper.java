package cz.cvut.fel.ear.lingo.repo.mapper;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import cz.cvut.fel.ear.lingo.flashcard.mapper.FlashcardModel2FlashcardMapper;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import cz.cvut.fel.ear.lingo.flashcarddeck.mapper.FlashcardDeckModel2FlashcardDeckMapper;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import cz.cvut.fel.ear.lingo.repo.model.RepoModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RepoModel2RepoMapper {

    private final FlashcardModel2FlashcardMapper flashcardModel2FlashcardMapper;
    private final FlashcardDeckModel2FlashcardDeckMapper flashcardDeckModel2FlashcardDeckMapper;

    public Repo toRepo(RepoModel repoModel) {
        if (repoModel == null) return null;
        List<Flashcard> flashcards = flashcardModel2FlashcardMapper
                .toFlashcardList(repoModel.getFlashcardsModel());
        List<FlashcardDeck> flashcardDecks = flashcardDeckModel2FlashcardDeckMapper
                .toFlashcardDeckList(repoModel.getFlashcardDecksModel());
        return Repo.builder()
                .id(repoModel.getId())
                .flashcards(flashcards)
                .flashcardDecks(flashcardDecks)
                .build();
    }
}
