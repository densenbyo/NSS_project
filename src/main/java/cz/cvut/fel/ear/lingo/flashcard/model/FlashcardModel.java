package cz.cvut.fel.ear.lingo.flashcard.model;

import cz.cvut.fel.ear.lingo.content.model.ContentModel;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FlashcardModel {

    private Long id;
    private boolean isRemoved;
    private String word;
    private String translation;
    private List<ContentModel> contentModels;
    private List<FlashcardProgressModel> flashcardProgressModels;
    private Long creatorId;

}
