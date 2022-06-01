package cz.cvut.fel.ear.lingo.user.use_case;

import cz.cvut.fel.ear.lingo.user.adapter.UserRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class BlockUserUseCase {

    private final UserRepositoryAdapter adapter;

    public void execute(Long id) {
        adapter.blockUserById(id);
    }
}