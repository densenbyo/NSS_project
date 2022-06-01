package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import cz.cvut.fel.ear.lingo.content.domain.ImageContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateImageContentUseCase {

    private final ContentRepositoryAdapter adapter;

    public void execute(ImageContent imageContent) {
        adapter.saveImageContent(imageContent);
    }
}
