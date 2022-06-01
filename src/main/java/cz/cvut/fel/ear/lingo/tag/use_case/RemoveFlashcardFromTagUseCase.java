package cz.cvut.fel.ear.lingo.tag.use_case;

import cz.cvut.fel.ear.lingo.tag.adapter.TagRepositoryAdapter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RemoveFlashcardFromTagUseCase {

    private final TagRepositoryAdapter adapter;

    public void execute(Long idTag, Long idFlashcard) {
        adapter.removeFlashcardFromTag(idTag, idFlashcard);
    }
}
