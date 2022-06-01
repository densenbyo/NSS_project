package cz.cvut.fel.ear.lingo.content.facade;

import cz.cvut.fel.ear.lingo.content.domain.*;
import cz.cvut.fel.ear.lingo.content.use_case.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ContentFacade {

    private final CreateAudioContentUseCase createAudioContentUseCase;
    private final CreateImageContentUseCase createImageContentUseCase;
    private final CreateMnemonicContentUseCase createMnemonicContentUseCase;
    private final CreateContextSentenceContentUseCase createContextSentenceContentUseCase;
    private final GetAllContentsUseCase getAllContentsUseCase;
    private final GetContentById getContentById;
    private final DeleteContentUseCase deleteContentUseCase;

    public void saveAudioContent(AudioContent audioContent) {
        createAudioContentUseCase.execute(audioContent);
    }

    public void saveImageContent(ImageContent imageContent) {
        createImageContentUseCase.execute(imageContent);
    }

    public void saveMnemonicContent(MnemonicContent mnemonicContent) {
        createMnemonicContentUseCase.execute(mnemonicContent);
    }

    public void saveContextSentenceContent(ContextSentenceContent contextSentenceContent) {
        createContextSentenceContentUseCase.execute(contextSentenceContent);
    }

    public List<AbstractContent> findAllContents() {
        return getAllContentsUseCase.execute();
    }

    public AbstractContent findAbstractContentById(Long id) {
        return getContentById.execute(id);
    }

    public void deleteContent(Long id) {
        deleteContentUseCase.execute(id);
    }
}