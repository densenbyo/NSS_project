package cz.cvut.fel.ear.lingo.repo.use_case;

import cz.cvut.fel.ear.lingo.repo.adapter.RepoRepositoryAdapter;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetRepoByIdUseCase {

    private final RepoRepositoryAdapter adapter;

    public Repo execute(Long id) {
        return adapter.getFlashcardById(id);
    }
}