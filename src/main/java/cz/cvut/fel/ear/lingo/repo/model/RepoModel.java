package cz.cvut.fel.ear.lingo.repo.model;

import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import cz.cvut.fel.ear.lingo.flashcarddeck.model.FlashcardDeckModel;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RepoModel {

    private Long id;
    private List<FlashcardDeckModel> flashcardDecksModel;
    private List<FlashcardModel> flashcardsModel;

}
