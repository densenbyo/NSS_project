package cz.cvut.fel.ear.lingo.content.adapter;

import cz.cvut.fel.ear.lingo.content.domain.*;
import cz.cvut.fel.ear.lingo.content.entity.*;
import cz.cvut.fel.ear.lingo.content.entity.repository.ContentEntityRepository;
import cz.cvut.fel.ear.lingo.content.mapper.AbstractContentEntityMapper;
import cz.cvut.fel.ear.lingo.user.entity.repository.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Component
@RequiredArgsConstructor
public class ContentJpaRepositoryAdapter implements ContentRepositoryAdapter{

    private final ContentEntityRepository contentEntityRepository;
    private final UserEntityRepository userEntityRepository;
    private final AbstractContentEntityMapper abstractContentEntityMapper;

    @Override
    public void saveAudioContent(AudioContent audioContent) {
        var userEntityOptional = userEntityRepository.findByIdAndIsRemovedIsFalse(audioContent.getCreatorId());
        if (userEntityOptional.isPresent()) {
            AudioContentEntity audioContentEntity = new AudioContentEntity();
            audioContentEntity.setId(audioContent.getId());
            audioContentEntity.setSourceURI(audioContent.getURI());
            audioContentEntity.setCreator(userEntityOptional.get());
            contentEntityRepository.save(audioContentEntity);
        }
    }

    @Override
    public void saveImageContent(ImageContent imageContent) {
        var userEntityOptional = userEntityRepository.findByIdAndIsRemovedIsFalse(imageContent.getCreatorId());
        if (userEntityOptional.isPresent()) {
            ImageContentEntity imageContentEntity = new ImageContentEntity();
            imageContentEntity.setId(imageContent.getId());
            imageContentEntity.setSourceURI(imageContent.getURI());
            imageContentEntity.setCreator(userEntityOptional.get());
            contentEntityRepository.save(imageContentEntity);
        }
    }

    @Override
    public void saveMnemonicContent(MnemonicContent mnemonicContent) {
        var userEntityOptional = userEntityRepository.findByIdAndIsRemovedIsFalse(mnemonicContent.getCreatorId());
        if (userEntityOptional.isPresent()) {
            MnemonicContentEntity mnemonicContentEntity = new MnemonicContentEntity();
            mnemonicContentEntity.setId(mnemonicContent.getId());
            mnemonicContentEntity.setMnemonic(mnemonicContent.getMnemonic());
            mnemonicContentEntity.setCreator(userEntityOptional.get());
            contentEntityRepository.save(mnemonicContentEntity);
        }
    }

    @Override
    public void saveContextSentenceContent(ContextSentenceContent contextContent) {
        var userEntityOptional = userEntityRepository.findByIdAndIsRemovedIsFalse(contextContent.getCreatorId());
        if (userEntityOptional.isPresent()) {
            ContextSentenceContentEntity contextSentenceContentEntity= new ContextSentenceContentEntity();
            contextSentenceContentEntity.setId(contextContent.getId());
            contextSentenceContentEntity.setSentence(contextContent.getSentence());
            contextSentenceContentEntity.setCreator(userEntityOptional.get());
            contentEntityRepository.save(contextSentenceContentEntity);
        }
    }

    @Override
    public List<AbstractContent> getListOfContent() {
        var contentEntities = contentEntityRepository.findAll();
        List<AbstractContentEntity> result =
                StreamSupport.stream(contentEntities.spliterator(), false).collect(Collectors.toList());
        return abstractContentEntityMapper.toAbstractContentList(result);
    }

    @Override
    public AbstractContent findById(Long id) {
        var abstractContentEntity = contentEntityRepository.findById(id);
        return abstractContentEntity.map(abstractContentEntityMapper::toAbstractContent).orElse(null);
    }

    @Override
    public void deleteContent(Long id) {
        var abstractContentEntityOptional = contentEntityRepository.findById(id);
        if (abstractContentEntityOptional.isPresent()) {
            var abstractContentEntity = abstractContentEntityOptional.get();
            contentEntityRepository.delete(abstractContentEntity);
        }
    }
}