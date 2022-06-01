package cz.cvut.fel.ear.lingo.tag.domain;

import cz.cvut.fel.ear.lingo.flashcard.domain.Flashcard;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Tag {

    private Long id;
    private String name;
    private List<Long> relatedTagsId;
    private List<Flashcard> flashcards;
    private Boolean isRemoved;

}
