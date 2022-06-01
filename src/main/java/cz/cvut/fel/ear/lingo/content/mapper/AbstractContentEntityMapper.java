package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.*;
import cz.cvut.fel.ear.lingo.content.entity.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AbstractContentEntityMapper {

    public AbstractContent toAbstractContent(AbstractContentEntity abstractContentEntity) {
        if (abstractContentEntity == null)
            return null;
        if (abstractContentEntity.getClass().isAssignableFrom(MnemonicContentEntity.class)) {
            var mnemonicContentEntity = (MnemonicContentEntity) abstractContentEntity;
            MnemonicContent mnemonicContent = new MnemonicContent(
                    abstractContentEntity.getId(), abstractContentEntity.getCreator().getId());
            mnemonicContent.setMnemonic(mnemonicContentEntity.getMnemonic());
            return mnemonicContent;
        } else if (abstractContentEntity.getClass().isAssignableFrom(ImageContentEntity.class)) {
            var imageContentEntity = (ImageContentEntity) abstractContentEntity;
            ImageContent imageContent = new ImageContent(
                    abstractContentEntity.getId(), abstractContentEntity.getCreator().getId());
            imageContent.setURI(imageContentEntity.getSourceURI());
            return imageContent;
        } else if (abstractContentEntity.getClass().isAssignableFrom(AudioContentEntity.class)) {
            var audioContentEntity = (AudioContentEntity) abstractContentEntity;
            AudioContent audioContent = new AudioContent(
                    abstractContentEntity.getId(), abstractContentEntity.getCreator().getId());
            audioContent.setURI(audioContentEntity.getSourceURI());
            return audioContent;
        } else if (abstractContentEntity.getClass().isAssignableFrom(ContextSentenceContentEntity.class)) {
            var contextSentenceContentEntity = (ContextSentenceContentEntity) abstractContentEntity;
            ContextSentenceContent contextSentenceContent = new ContextSentenceContent(
                    abstractContentEntity.getId(), abstractContentEntity.getCreator().getId());
            contextSentenceContent.setSentence(contextSentenceContentEntity.getSentence());
            return contextSentenceContent;
        }
        return null;
    }

    public List<AbstractContent> toAbstractContentList(List<AbstractContentEntity> abstractContentEntities) {
        List<AbstractContent> abstractContents = new ArrayList<>(abstractContentEntities.size());
        for (AbstractContentEntity entity: abstractContentEntities) {
            abstractContents.add(toAbstractContent(entity));
        }
        return abstractContents;
    }
}