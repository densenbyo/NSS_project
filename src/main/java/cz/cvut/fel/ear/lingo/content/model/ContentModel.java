package cz.cvut.fel.ear.lingo.content.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContentModel {

    private Long id;
    private Long creatorId;
    private String content;
    private String type;
}
