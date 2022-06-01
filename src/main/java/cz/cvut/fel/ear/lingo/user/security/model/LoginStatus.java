package cz.cvut.fel.ear.lingo.user.security.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LoginStatus {

    private boolean loggedIn;

    private String username;

    private String errorMessage;

    private boolean success;

}