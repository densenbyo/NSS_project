package cz.cvut.fel.ear.lingo.user.use_case;


import cz.cvut.fel.ear.lingo.user.adapter.UserRepositoryAdapter;
import cz.cvut.fel.ear.lingo.user.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetUserByIdUseCase {

    private final UserRepositoryAdapter adapter;

    public User execute(Long id) {
        return adapter.findById(id);
    }
}