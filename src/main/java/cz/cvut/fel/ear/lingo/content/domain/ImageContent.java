package cz.cvut.fel.ear.lingo.content.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ImageContent extends AbstractContent {

    public ImageContent(Long id, Long creatorId) {
        super(id, creatorId);
    }

    private String URI;

}
