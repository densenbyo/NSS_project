package cz.cvut.fel.ear.lingo.tag.model;

import cz.cvut.fel.ear.lingo.flashcard.model.FlashcardModel;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TagModel {

    private Long id;
    private String name;
    private List<Long> relatedTagIds;
    private List<FlashcardModel> flashcardModels;
    private Boolean isRemoved;

}
