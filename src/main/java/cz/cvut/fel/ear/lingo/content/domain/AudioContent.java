package cz.cvut.fel.ear.lingo.content.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AudioContent extends AbstractContent{

    public AudioContent(Long id, Long creatorId) {
        super(id, creatorId);
    }

    private String URI;
}
