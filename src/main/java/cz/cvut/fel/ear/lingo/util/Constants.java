package cz.cvut.fel.ear.lingo.util;

import cz.cvut.fel.ear.lingo.user_service.entity.enumeration.UserRole;

public class Constants {

    public static final UserRole DEFAULT_ROLE = UserRole.USER;

    private Constants() {
        throw new AssertionError();
    }
}
