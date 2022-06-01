package cz.cvut.fel.ear.lingo.content.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public abstract class AbstractContent {

    private Long id;
    private Long creatorId;

}