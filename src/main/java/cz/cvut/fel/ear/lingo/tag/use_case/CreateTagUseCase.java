package cz.cvut.fel.ear.lingo.tag.use_case;

import cz.cvut.fel.ear.lingo.tag.adapter.TagRepositoryAdapter;
import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateTagUseCase {

    private final TagRepositoryAdapter adapter;

    public void execute(Tag tag) {
        adapter.createTag(tag);
    }
}
