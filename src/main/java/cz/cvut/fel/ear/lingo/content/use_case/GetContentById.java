package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import cz.cvut.fel.ear.lingo.content.domain.AbstractContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class GetContentById {

    private final ContentRepositoryAdapter adapter;

    public AbstractContent execute(Long id) {
        return adapter.findById(id);
    }
}
