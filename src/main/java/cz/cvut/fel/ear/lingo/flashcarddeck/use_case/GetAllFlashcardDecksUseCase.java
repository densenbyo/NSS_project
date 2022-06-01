package cz.cvut.fel.ear.lingo.flashcarddeck.use_case;

import cz.cvut.fel.ear.lingo.flashcarddeck.adapter.FlashcardDeckRepositoryAdapter;
import cz.cvut.fel.ear.lingo.flashcarddeck.domain.FlashcardDeck;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllFlashcardDecksUseCase {

    private final FlashcardDeckRepositoryAdapter adapter;

    public List<FlashcardDeck> execute() {
        return adapter.findAllFlashcards();
    }
}
