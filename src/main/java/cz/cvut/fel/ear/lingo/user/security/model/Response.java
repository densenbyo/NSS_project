package cz.cvut.fel.ear.lingo.user.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Response {

    private Boolean success;

    private String message;

}
