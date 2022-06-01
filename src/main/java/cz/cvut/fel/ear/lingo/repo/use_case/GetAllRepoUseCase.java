package cz.cvut.fel.ear.lingo.repo.use_case;

import cz.cvut.fel.ear.lingo.repo.adapter.RepoRepositoryAdapter;
import cz.cvut.fel.ear.lingo.repo.domain.Repo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllRepoUseCase {

    private final RepoRepositoryAdapter adapter;

    public List<Repo> execute() {
        return adapter.findAll();
    }
}