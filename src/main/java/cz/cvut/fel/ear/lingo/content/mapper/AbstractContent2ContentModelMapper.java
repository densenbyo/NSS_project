package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.*;
import cz.cvut.fel.ear.lingo.content.model.ContentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AbstractContent2ContentModelMapper {

    public ContentModel toContentModel(AbstractContent abstractContent) {
        if (abstractContent == null)
            return null;
        ContentModel contentModel = ContentModel.builder()
                                                .id(abstractContent.getId())
                                                .creatorId(abstractContent.getCreatorId())
                                                .build();
        if (abstractContent.getClass().isAssignableFrom(MnemonicContent.class)) {
            var mnemonicContent = (MnemonicContent) abstractContent;
            contentModel.setContent(mnemonicContent.getMnemonic());
            contentModel.setType("MNEMONIC");
        } else if (abstractContent.getClass().isAssignableFrom(ImageContent.class)) {
            var imageContent = (ImageContent) abstractContent;
            contentModel.setContent(imageContent.getURI());
            contentModel.setType("IMAGE");
        } else if (abstractContent.getClass().isAssignableFrom(AudioContent.class)) {
            var audioContent = (AudioContent) abstractContent;
            contentModel.setContent(audioContent.getURI());
            contentModel.setType("AUDIO");
        } else if (abstractContent.getClass().isAssignableFrom(ContextSentenceContent.class)) {
            var contextSentenceContent = (ContextSentenceContent) abstractContent;
            contentModel.setContent(contextSentenceContent.getSentence());
            contentModel.setType("SENTENCE");
        }
        return contentModel;
    }

    public List<ContentModel> toContentModelList(List<AbstractContent> abstractContents) {
        List<ContentModel> contentModels = new ArrayList<>(abstractContents.size());
        for (AbstractContent entity : abstractContents) {
            contentModels.add(toContentModel(entity));
        }
        return contentModels;
    }
}
