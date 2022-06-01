package cz.cvut.fel.ear.lingo.user.use_case;

import cz.cvut.fel.ear.lingo.user.adapter.UserRepositoryAdapter;
import cz.cvut.fel.ear.lingo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateUserUseCase {

    private final UserRepositoryAdapter adapter;

    public void execute(Long id, User user) {
        adapter.updateUser(id, user);
    }
}
