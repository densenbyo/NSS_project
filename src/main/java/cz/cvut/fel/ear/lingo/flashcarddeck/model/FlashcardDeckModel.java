package cz.cvut.fel.ear.lingo.flashcarddeck.model;

import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import lombok.*;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class FlashcardDeckModel {

    private Long id;
    private String name;
    private String description;
    private Boolean isPublic;
    private Boolean isRemoved;
    private List<FlashcardModel> flashcardModels;
    private Long creatorId;

}
