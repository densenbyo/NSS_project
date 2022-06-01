package cz.cvut.fel.ear.lingo.content.use_case;

import cz.cvut.fel.ear.lingo.content.adapter.ContentRepositoryAdapter;
import cz.cvut.fel.ear.lingo.content.domain.AudioContent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateAudioContentUseCase {

    private final ContentRepositoryAdapter adapter;

    public void execute(AudioContent audioContent) {
        adapter.saveAudioContent(audioContent);
    }
}
