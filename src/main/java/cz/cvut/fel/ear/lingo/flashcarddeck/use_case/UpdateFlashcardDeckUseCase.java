package cz.cvut.fel.ear.lingo.flashcarddeck.use_case;

import cz.cvut.fel.ear.lingo.flashcarddeck.adapter.FlashcardDeckRepositoryAdapter;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateFlashcardDeckUseCase {
    private final FlashcardDeckRepositoryAdapter adapter;

    public void execute(Long id, FlashcardDeck flashcardDeck) {
        adapter.updateFlashcardDeck(id, flashcardDeck);
    }
}