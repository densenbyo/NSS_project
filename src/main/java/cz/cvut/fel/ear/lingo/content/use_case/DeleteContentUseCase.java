package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DeleteContentUseCase {
    private final ContentRepositoryAdapter adapter;

    public void execute(Long id) {
        adapter.deleteContent(id);
    }
}
