package cz.cvut.fel.ear.lingo.content.mapper;

import cz.cvut.fel.ear.lingo.content.domain.AudioContent;
import cz.cvut.fel.ear.lingo.content.model.AudioContentModel;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AudioContentModal2AudioContentMapper {

    public AudioContent toAudioContent(AudioContentModel audioContentModel) {
        if (audioContentModel == null)
            return null;
        AudioContent audioContent = new AudioContent(audioContentModel.getId(), audioContentModel.getCreatorId());
        audioContent.setURI(audioContentModel.getURI());
        return audioContent;
    }
}
