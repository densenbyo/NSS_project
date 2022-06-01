package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.*;
import cz.cvut.fel.ear.lingo.content.entity.*;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class AbstractContent2AbstractContentEntityMapper {

    public AbstractContentEntity toAbstractContentEntity(AbstractContent abstractContent, UserEntity userEntity) {
        if (abstractContent == null) {
            return null;
        } else if (abstractContent instanceof MnemonicContent) {
            MnemonicContentEntity entity = new MnemonicContentEntity();
            entity.setId(abstractContent.getId());
            entity.setCreator(userEntity);
            entity.setMnemonic(((MnemonicContent) abstractContent).getMnemonic());
            return entity;
        } else if (abstractContent instanceof ContextSentenceContent) {
            ContextSentenceContentEntity entity = new ContextSentenceContentEntity();
            entity.setId(abstractContent.getId());
            entity.setCreator(userEntity);
            entity.setSentence(((ContextSentenceContent) abstractContent).getSentence());
            return entity;
        } else if (abstractContent instanceof AudioContent) {
            AudioContentEntity entity = new AudioContentEntity();
            entity.setId(abstractContent.getId());
            entity.setCreator(userEntity);
            entity.setSourceURI(((AudioContent) abstractContent).getURI());
            return entity;
        } else if (abstractContent instanceof ImageContent) {
            ImageContentEntity entity = new ImageContentEntity();
            entity.setId(abstractContent.getId());
            entity.setCreator(userEntity);
            entity.setSourceURI(((ImageContent) abstractContent).getURI());
            return entity;
        }
        return null;
    }

    public List<AbstractContentEntity> toAbstractContentEntityList(List<AbstractContent> abstractContents, UserEntity userEntity) {
        List<AbstractContentEntity> entities = new ArrayList<>(abstractContents.size());
        for (AbstractContent entity : abstractContents) {
            entities.add(toAbstractContentEntity(entity, userEntity));
        }
        return entities;
    }
}