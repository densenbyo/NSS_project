package cz.cvut.fel.ear.lingo.content.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContextSentenceContent extends AbstractContent{

    public ContextSentenceContent(Long id, Long creatorId) {
        super(id, creatorId);
    }

    private String sentence;
}
