package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import cz.cvut.fel.ear.lingo.content.domain.ContextSentenceContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateContextSentenceContentUseCase {

    private final ContentRepositoryAdapter adapter;

    public void execute(ContextSentenceContent contextSentenceContent) {
        adapter.saveContextSentenceContent(contextSentenceContent);
    }
}
