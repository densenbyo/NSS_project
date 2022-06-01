package cz.cvut.fel.ear.lingo.content.model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AudioContentModel {

    private Long id;
    private Long creatorId;
    private String URI;

}
