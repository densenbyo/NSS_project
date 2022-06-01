package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.*;
import cz.cvut.fel.ear.lingo.content.model.ContentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class ContentModel2AbstractContentMapper {

    public AbstractContent toAbstractContent(ContentModel contentModel) {
        if (contentModel == null)
            return null;
        switch (contentModel.getType()) {
            case "MNEMONIC":
                MnemonicContent mnemonicContent = new MnemonicContent(contentModel.getId(), contentModel.getCreatorId());
                mnemonicContent.setMnemonic(contentModel.getContent());
                return mnemonicContent;
            case "IMAGE":
                ImageContent imageContent = new ImageContent(contentModel.getId(), contentModel.getCreatorId());
                imageContent.setURI(contentModel.getContent());
                return imageContent;
            case "AUDIO":
                AudioContent audioContent = new AudioContent(contentModel.getId(), contentModel.getCreatorId());
                audioContent.setURI(contentModel.getContent());
                return audioContent;
            case "SENTENCE":
                ContextSentenceContent contextSentenceContent = new ContextSentenceContent(contentModel.getId(), contentModel.getCreatorId());
                contextSentenceContent.setSentence(contentModel.getContent());
                return contextSentenceContent;
            default:
                return null;
        }
    }

    public List<AbstractContent> toAbstractContentList(List<ContentModel> contentModels) {
        List<AbstractContent> abstractContents1 = new ArrayList<>(contentModels.size());
        for (ContentModel entity : contentModels) {
            abstractContents1.add(toAbstractContent(entity));
        }
        return abstractContents1;
    }
}
