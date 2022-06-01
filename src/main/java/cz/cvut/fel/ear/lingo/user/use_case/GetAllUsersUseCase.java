package cz.cvut.fel.ear.lingo.user.use_case;

import cz.cvut.fel.ear.lingo.user.adapter.UserRepositoryAdapter;
import cz.cvut.fel.ear.lingo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllUsersUseCase {

    private final UserRepositoryAdapter adapter;

    public List<User> execute() {
        return adapter.getListOfUser();
    }
}
