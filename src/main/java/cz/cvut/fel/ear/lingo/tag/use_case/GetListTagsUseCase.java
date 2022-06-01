package cz.cvut.fel.ear.lingo.tag.use_case;


import cz.cvut.fel.ear.lingo.tag.adapter.TagRepositoryAdapter;
import cz.cvut.fel.ear.lingo.tag.domain.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetListTagsUseCase {

    private final TagRepositoryAdapter adapter;

    public List<Tag> execute() {
        return adapter.getAllTags();
    }
}