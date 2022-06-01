package cz.cvut.fel.ear.lingo.content.adapter;

import cz.cvut.fel.ear.lingo.content.domain.AudioContent;
import cz.cvut.fel.ear.lingo.content.domain.ContextSentenceContent;
import cz.cvut.fel.ear.lingo.content.domain.ImageContent;
import cz.cvut.fel.ear.lingo.content.domain.MnemonicContent;
import cz.cvut.fel.ear.lingo.content.facade.ContentFacade;
import cz.cvut.fel.ear.lingo.content.mapper.*;
import cz.cvut.fel.ear.lingo.content.model.*;
import cz.cvut.fel.ear.lingo.user.entity.UserEntity;
import cz.cvut.fel.ear.lingo.user.mapper.UserEntityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@RequiredArgsConstructor
@Component
public class ContentAdapter {

    private final ContentFacade contentFacade;
    private final ImageContentModal2ImageContentMapper imageContentModal2ImageContentMapper;
    private final AudioContentModal2AudioContentMapper audioContentModal2AudioContentMapper;
    private final ContentSentenceContentModal2ContentSentenceContentMapper contentSentenceContentModal2ContentSentenceContentMapper;
    private final MnemonicContentModal2MnemonicContentMapper mnemonicContentModal2MnemonicContentMapper;
    private final AbstractContent2ContentModelMapper abstractContent2ContentModelMapper;
    private final UserEntityMapper userEntityMapper;

    public void saveAudioContent(AudioContentModel audioAudioContentModel, UserEntity userEntity) {
        AudioContent audioContent = audioContentModal2AudioContentMapper.toAudioContent(audioAudioContentModel);
        audioContent.setCreatorId(userEntityMapper.toUser(userEntity).getId());
        contentFacade.saveAudioContent(audioContent);
    }

    public void saveImageContent(ImageContentModel imageContentModel, UserEntity userEntity) {
        ImageContent imageContent = imageContentModal2ImageContentMapper.toImageContent(imageContentModel);
        imageContent.setCreatorId(userEntityMapper.toUser(userEntity).getId());
        contentFacade.saveImageContent(imageContent);
    }

    public void saveMnemonicContent(MnemonicContentModel mnemonicContentModel, UserEntity userEntity) {
        MnemonicContent mnemonicContent = mnemonicContentModal2MnemonicContentMapper.toMnemonicContent(mnemonicContentModel);
        mnemonicContent.setCreatorId(userEntityMapper.toUser(userEntity).getId());
        contentFacade.saveMnemonicContent(mnemonicContent);
    }

    public void saveContextSentenceContent(ContextSentenceContentModel contextSentenceContentModel, UserEntity userEntity) {
        ContextSentenceContent contextSentenceContent = contentSentenceContentModal2ContentSentenceContentMapper
                .toContextSentenceContent(contextSentenceContentModel);
        contextSentenceContent.setCreatorId(userEntityMapper.toUser(userEntity).getId());
        contentFacade.saveContextSentenceContent(contextSentenceContent);
    }

    public List<ContentModel> findAllContents(String role, Long userId) {
        var contentsList = contentFacade.findAllContents();
        if (role.equals("ROLE_USER")) {
            contentsList = contentsList.stream().filter(s -> s.getCreatorId().equals(userId)).toList();
        }
        return abstractContent2ContentModelMapper.toContentModelList(contentsList);
    }

    public ContentModel getContentById(Long id, String role, Long userId) {
        var abstractContent = contentFacade.findAbstractContentById(id);
        if (role.equals("ROLE_USER")) {
            abstractContent = Objects.equals(abstractContent.getCreatorId(), userId) ? abstractContent : null;
        }
        return abstractContent2ContentModelMapper.toContentModel(abstractContent);
    }

    public void deleteContent(Long id) {
        contentFacade.deleteContent(id);
    }
}