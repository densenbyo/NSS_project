package cz.cvut.fel.ear.lingo.flashcard.domain;

import cz.cvut.fel.ear.lingo.content.domain.AbstractContent;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Flashcard {

    private Long id;
    private boolean isRemoved;
    private String word;
    private String translation;
    private List<AbstractContent> abstractContents;
    private List<FlashcardProgress> flashcardProgresses;
    private Long creatorId;

}
