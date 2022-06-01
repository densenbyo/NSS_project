package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import cz.cvut.fel.ear.lingo.content.domain.AbstractContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetAllContentsUseCase {
    private final ContentRepositoryAdapter adapter;

    public List<AbstractContent> execute() {
        return adapter.getListOfContent();
    }
}
