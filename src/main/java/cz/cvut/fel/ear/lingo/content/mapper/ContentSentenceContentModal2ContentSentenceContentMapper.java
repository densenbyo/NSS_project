package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.ContextSentenceContent;
import cz.cvut.fel.ear.lingo.content.model.ContextSentenceContentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ContentSentenceContentModal2ContentSentenceContentMapper {

    public ContextSentenceContent toContextSentenceContent(ContextSentenceContentModel contextSentenceContentModel) {
        if (contextSentenceContentModel == null)
            return null;
        ContextSentenceContent contextSentenceContent = new ContextSentenceContent(contextSentenceContentModel.getId(),
                contextSentenceContentModel.getCreatorId());
        contextSentenceContent.setSentence(contextSentenceContentModel.getSentence());
        return contextSentenceContent;
    }
}
