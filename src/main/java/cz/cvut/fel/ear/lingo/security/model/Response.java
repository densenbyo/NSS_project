package cz.cvut.fel.ear.lingo.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {

    private Boolean success;

    private String message;

}
